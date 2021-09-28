package control;

import MyExceptions.ClientReceiveResponseException;
import MyExceptions.ClientSendRequestException;
import MyExceptions.CommandException;
import com.sun.xml.internal.ws.encoding.soap.DeserializationException;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import control.commands.Command;
import database.User;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Client {
    private static boolean processingStatus;
    private static SocketAddress serverAddress;
    private static DatagramChannel channel;
    private final String hostName;
    private final int port;
    private final ByteBuffer bufferedDataReceive = ByteBuffer.allocate(100000);
    private final Console console = new Console();
    private Thread waitingThread;
    private Response serverResponse;
    private static User user;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public static boolean isProcessingStatus() {
        return processingStatus;
    }
    /*
    Connect to server
     */

    public static void setProcessingStatus(boolean processingStatus) {
        Client.processingStatus = processingStatus;
    }

    /*
    Send data
     */
    public static ByteBuffer serialize(Serializable o) throws SerializationException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.flush();
            oos.writeObject(o);
        } catch (NotSerializableException e) {
            throw new SerializationException("Ошибка сериализации, один из объектов не реализует интефейс Serializable");
        } catch (InvalidClassException e) {
            throw new SerializationException("Ошибка сериализации, id десериализируемых объектов не соответствуют");
        } catch (IOException e) {
            throw new SerializationException("Ошибка сериализации");
        }
        // wrap and send data
        byte[] buff = baos.toByteArray();
        return ByteBuffer.wrap(buff);
    }

    public static void sendClientRequest(ByteBuffer bufferedDataSend, SocketAddress serverAddress) throws ClientSendRequestException {
        try {
            channel.send(bufferedDataSend, serverAddress);
        } catch (IOException e) {
            throw new ClientSendRequestException("Что-то пошло не так, данные не отправлены серверу");
        }
    }

    public static void receiveServerResponse(ByteBuffer bufferedDataReceive) throws ClientReceiveResponseException {
        try {
            serverAddress = channel.receive(bufferedDataReceive);
        } catch (IOException e) {
            throw new ClientReceiveResponseException("Что-то пошло не так, данные не получены от сервера");
        }
    }

    public static Response deSerialize(byte[] buffer) throws DeserializationException {
        Response object;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
            object = (Response) ois.readObject();
        } catch (InvalidClassException e) {
            throw new DeserializationException("Ошибка десериализации, id десериализируемых объектов не соответствуют");
        } catch (ClassNotFoundException e) {
            throw new DeserializationException("Ошибка десериализации,класс не найден");
        } catch (IOException e) {
            throw new DeserializationException("Ошибка десериализации");
        }
        return object;
    }

    public static Thread serverTimeOut() {
        Runnable task = () -> {
            int time = 1000;
            try {
                Thread.sleep(time);
                System.out.println("Нет ответа от сервера, завершение работы");
                System.exit(0);
            } catch (InterruptedException e) {
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        return thread;
    }

    public static SocketAddress getServerAddress() {
        return serverAddress;
    }

    /*
    Start control.Client
     */
    public void run() {
        Command command;
        Request request;
        Thread waitingThread;
        processingStatus = true;
        serverAddress = new InetSocketAddress(hostName, port);
        connectToServer();
        try {
            user = UserAuth.userAuth();
            while (processingStatus) {
                command = console.getDataFromKeyboard();
                request = new Request(command);
                ByteBuffer bufferedDataSend = serialize(request);
                sendClientRequest(bufferedDataSend, serverAddress);
                waitingThread = serverTimeOut();
                bufferedDataSend.clear();
                receiveServerResponse(bufferedDataReceive);
                waitingThread.interrupt();
                serverResponse = deSerialize(bufferedDataReceive.array());
                if (serverResponse.isHardResponse()) {
                    serverResponse.extractResponses();
                    bufferedDataReceive.clear();
                    continue;
                }
                bufferedDataReceive.clear();
                serverResponse.viewResponse();
            }
        } catch (CommandException e) {
            System.out.println(e);
            System.exit(0);
        } catch (SerializationException | DeserializationException | ClientSendRequestException | ClientReceiveResponseException serEx) {
            System.out.println(serEx.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer() {
        try {
            channel = DatagramChannel.open();
            channel.socket().bind(null);
        } catch (IOException e) {
            System.out.println("Нет соединения с сервером");
        }
        System.out.println("Client запущен, ожидание команд");
    }

    public static User getUser() {
        return user;
    }
}
