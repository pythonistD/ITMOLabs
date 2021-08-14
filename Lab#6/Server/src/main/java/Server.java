import control.Response;
import control.commands.Command;

import java.io.*;
import java.net.*;

public class Server {
    private int port;
    private int timeout;
    private SocketAddress socketAddress;
    private DatagramSocket socket;
    private boolean processingStatus;
    private Response response;
    private int clientCounter;

    private byte buffReceived[] = new byte[576];
    private byte buffSend[] = new byte[576];

    private DatagramPacket packetReceived =new DatagramPacket(buffReceived, buffReceived.length);
    private DatagramPacket packetSend = new DatagramPacket(buffSend, buffSend.length);
    public Server(int port, int timeout){
        this.port = port;
        this.timeout = timeout;
    }
    /*
    *Server operations
     */
    public void run() throws Exception {
        socketAddress = new InetSocketAddress(port);
        DatagramPacket packetClient;
        Command clientObject;
        openSocket();
        processingStatus=true;
        while (processingStatus){
            try {
                socket.receive(packetReceived);
                getClientAddress(packetReceived);
                clientObject = deSerialize(packetReceived);
                clientObject.execute();
                response=clientObject.getResponse();
                buffSend = serialization(response);
                packetSend = createServerResponsePacket(buffSend);
                sendServerResponse(packetSend);
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
    /*
    Deserialize Data
     */
    private Command deSerialize(DatagramPacket packet){
        Command object = null;
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            try(ObjectInputStream ois = new ObjectInputStream(bais)) {
                object = (Command) ois.readObject();
                System.out.println(object);
            } catch (ClassNotFoundException e) {
                System.out.println("Класс не найден");
            } catch (IOException e) {
                System.out.println("Ошибка десериализации");
                e.printStackTrace();
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
        System.out.println("Клиент " + clientCounter);
        System.out.println("Хост Клиента: " + packet.getAddress());
        System.out.println("Порт Клиента: " + packet.getPort());

    }

    private DatagramPacket createServerResponsePacket(byte[] buffToSend){
       return packetSend = new DatagramPacket(buffToSend,buffToSend.length,packetReceived.getAddress(),packetReceived.getPort());
    }
    private byte[] serialization(Object responseObject) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.flush();
        oos.writeObject(responseObject);
        oos.close();
        return baos.toByteArray();
    }

    private void sendServerResponse(DatagramPacket serverResponsePacket){
        try {
            socket.send(serverResponsePacket);
        } catch (IOException e) {
            System.out.println("Упс, ошибочка, данные не отправлены на Клиент");
        }
        System.out.println("Данные успешно отправлены");
    }
}
