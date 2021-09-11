package control;

import MyExceptions.ServerReceiveException;
import MyExceptions.ServerSendResponseException;
import com.sun.xml.internal.ws.encoding.soap.DeserializationException;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import control.commands.Command;

import java.io.*;
import java.net.*;

public class Server {
    private int port;
    private int timeout;
    private SocketAddress socketAddress;
    private static DatagramSocket socket;
    private static boolean processingStatus;
    private Response response;
    private int clientCounter=0;

    private InetAddress clientIp;
    private int clientPort;

    private static byte[] buffReceived = new byte[100000];
    private byte buffSend[] = new byte[10000];

    private static DatagramPacket packetReceived =new DatagramPacket(buffReceived, buffReceived.length);
    private DatagramPacket packetSend;
    public Server(int port, int timeout){
        this.port = port;
        this.timeout = timeout;
    }
    /*
    *control.Server operations
     */
    public void run() throws Exception {
        socketAddress = new InetSocketAddress(port);
        Request clientRequest;
        Command command;
        openSocket();
        processingStatus=true;
        while (processingStatus){
            try {
                receiveClientRequest(packetReceived);
                getClientAddress(packetReceived);
                clientRequest = deSerialize(packetReceived);
                command = clientRequest.getCommandObjectArgument();
                command.execute();
                response = command.getResponse();
                buffSend = serialization(response);
                packetSend = createServerResponsePacket(buffSend);
                sendServerResponse(packetSend);
            }catch (SerializationException | DeserializationException e){
                System.out.println(e.getMessage());
            }catch (ServerReceiveException | ServerSendResponseException e){
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Ошибочка вышла... Соединение не установлено :(");
                break;
            }

        //stop();
        }
    }
    /*
    Open socket
     */
    private void openSocket(){
        System.out.println("Запуск сервера...");
        try{
            socket = new DatagramSocket(port);
            socket.setSoTimeout(timeout);
            System.out.println("Сервер запущен успешно");
        } catch (SocketException e) {
            System.out.println("Произошла ошибка при подключении к порту:" + port);
        }
    }
    public static void receiveClientRequest(DatagramPacket packetReceived) throws ServerSendResponseException {
        try {
            socket.receive(packetReceived);
        } catch (IOException e) {
            throw new ServerSendResponseException("Что-то пошло не так, данные не отправлены клиенту");
        }
        System.out.println("Данные успешно отправлены");
    }
    /*
    Deserialize Data
     */
    private Request deSerialize(DatagramPacket packet)throws DeserializationException{
        Request object = null;
            try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()))) {
                object = (Request) ois.readObject();
                System.out.println(object);
            }catch (InvalidClassException e){
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
    private void stop(){
        System.out.println("Остановка сервера");
    }
    /*
    Who dropped the packet?
     */
    private void getClientAddress(DatagramPacket packet){
        if(!isInfFromTheSameClient(clientIp,clientPort)){
            clientIp = packet.getAddress();
            clientPort = packet.getPort();
            System.out.println("Клиент " + clientCounter);
            System.out.println("Хост Клиента: " + clientIp);
            System.out.println("Порт Клиента: " + clientPort);
        }

    }

    public static DatagramPacket createServerResponsePacket(byte[] buffToSend){
        DatagramPacket packetSend = new DatagramPacket(buffToSend,buffToSend.length,packetReceived.getAddress(),packetReceived.getPort());
        return packetSend;
    }
    public static byte[] serialization(Object responseObject) throws SerializationException {
        byte[] byteArr;
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.flush();
            oos.writeObject(responseObject);
            byteArr = baos.toByteArray();
        }catch (NotSerializableException e){
            throw new SerializationException("Ошибка сериализации, один из объектов не реализует интефейс Serializable");
        }catch (InvalidClassException e){
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
    private boolean isInfFromTheSameClient(InetAddress ip, int port){

        try {
            if(clientIp.equals(ip) && clientPort == port) {
                return true;
            }
        }catch (NullPointerException e){
            return false;
        }

        return false;
    }

    public static boolean isProcessingStatus() {
        return processingStatus;
    }

    public static void setProcessingStatus(boolean processingStatus) {
        Server.processingStatus = processingStatus;
    }
}
