package control;

import MyExceptions.CommandException;
import MyExceptions.ServerReceiveException;
import MyExceptions.ServerSendResponseException;
import com.sun.xml.internal.ws.encoding.soap.DeserializationException;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import control.commands.Command;

import java.io.*;
import java.net.*;

public class Server {
    private static DatagramSocket socket;
    private static boolean processingStatus;
    private static byte[] buffReceived = new byte[100000];
    private static DatagramPacket packetReceived = new DatagramPacket(buffReceived, buffReceived.length);
    private int port;
    private int timeout;
    private SocketAddress socketAddress;
    private Response response;
    private int clientCounter = 0;
    private InetAddress clientIp;
    private int clientPort;
    private byte buffSend[] = new byte[10000];
    private DatagramPacket packetSend;

    public Server(int port, int timeout) {
        this.port = port;
        this.timeout = timeout;
    }

    public static void receiveClientRequest(DatagramPacket packetReceived) throws ServerReceiveException {
        try {
            socket.receive(packetReceived);
        } catch (IOException e) {
            throw new ServerReceiveException("Что-то пошло не так, данные не отправлены клиенту");
        }
        System.out.println("Данные успешно отправлены");
    }

    public static DatagramPacket createServerResponsePacket(byte[] buffToSend) {
        DatagramPacket packetSend = new DatagramPacket(buffToSend, buffToSend.length, packetReceived.getAddress(), packetReceived.getPort());
        return packetSend;
    }

    public static byte[] serialization(Object responseObject) throws SerializationException {
        byte[] byteArr;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.flush();
            oos.writeObject(responseObject);
            byteArr = baos.toByteArray();
        } catch (NotSerializableException e) {
            throw new SerializationException("Ошибка сериализации, один из объектов не реализует интефейс Serializable");
        } catch (InvalidClassException e) {
            throw new SerializationException("Ошибка сериализации, id десериализируемых объектов не соответствуют");
        } catch (IOException e) {
            throw new SerializationException("Ошибка сериализации");
        }
        return byteArr;
    }

    public static void sendServerResponse(DatagramPacket serverResponsePacket) throws ServerSendResponseException {
        try {
            socket.send(serverResponsePacket);
        } catch (IOException e) {
            throw new ServerSendResponseException("Что-то пошло не так, данные не отправлены клиенту");
        }
        System.out.println("Данные успешно отправлены");
    }

    public static boolean isProcessingStatus() {
        return processingStatus;
    }

    public static void setProcessingStatus(boolean processingStatus) {
        Server.processingStatus = processingStatus;
    }

    /*
     *control.Server operations
     */
    public void run() {
        socketAddress = new InetSocketAddress(port);
        Request clientRequest;
        Command command;
        openSocket();
        processingStatus = true;
        while (processingStatus) {
            try {
                receiveClientRequest(packetReceived);
                getClientAddress(packetReceived);
                clientRequest = deSerialize(packetReceived);
                command = clientRequest.getCommandObjectArgument();
                try {
                    command.execute();
                } catch (CommandException comEx) {
                    System.out.println(comEx.getCause());
                    response = new Response(comEx.getCause().getMessage());
                    packetSend = createPacket(response);
                    sendServerResponse(packetSend);
                    continue;
                }
                response = command.getResponse();
                packetSend = createPacket(response);
                sendServerResponse(packetSend);
            } catch (SerializationException | DeserializationException e) {
                System.out.println(e.getMessage());
            } catch (ServerReceiveException | ServerSendResponseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private DatagramPacket createPacket(Response response) {
        byte[] buff;
        DatagramPacket packet;
        buff = serialization(response);
        packet = createServerResponsePacket(buff);
        return packet;
    }


    /*
    Open socket
     */
    private void openSocket() {
        System.out.println("Запуск сервера...");
        try {
            socket = new DatagramSocket(port);
            socket.setSoTimeout(timeout);
            System.out.println("Сервер запущен успешно");
        } catch (SocketException e) {
            System.out.println("Произошла ошибка при подключении к порту:" + port);
        }
    }

    /*
    Deserialize Data
     */
    private Request deSerialize(DatagramPacket packet) throws DeserializationException {
        Request object = null;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()))) {
            object = (Request) ois.readObject();
            System.out.println(object);
        } catch (InvalidClassException e) {
            throw new DeserializationException("Ошибка десериализации, id десериализируемых объектов не соответствуют");
        } catch (ClassNotFoundException e) {
            throw new DeserializationException("Ошибка десериализации,класс не найден");
        } catch (IOException e) {
            throw new DeserializationException("Ошибка десериализации");
        }
        return object;
    }

    /*
    Stop the server
     */
    private void stop() {
        System.out.println("Остановка сервера");
    }

    /*
    Who dropped the packet?
     */
    private void getClientAddress(DatagramPacket packet) {
        if (!isInfFromTheSameClient(clientIp, clientPort)) {
            clientIp = packet.getAddress();
            clientPort = packet.getPort();
            System.out.println("Клиент " + clientCounter);
            System.out.println("Хост Клиента: " + clientIp);
            System.out.println("Порт Клиента: " + clientPort);
        }

    }

    private boolean isInfFromTheSameClient(InetAddress ip, int port) {

        try {
            if (clientIp.equals(ip) && clientPort == port) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }
}
