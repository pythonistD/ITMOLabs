import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class Server {
    private int port;
    private int timeout;
    private SocketAddress socketAddress;
    private DatagramSocket socket;
    private boolean processingStatus;
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
    public void run(){
        socketAddress = new InetSocketAddress(port);
        DatagramPacket packetClient = null;
        Object clientObject;
        openSocket();
        processingStatus=true;
        while (processingStatus){
            try {
                packetClient = getClientRequest();
                getClientAddress(packetClient);
                clientObject = deSerialize(packetClient);
                objectToString(clientObject);
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
    Client's request
     */

    private DatagramPacket getClientRequest() throws IOException {
            socket.receive(packetReceived);
            packetSend = new DatagramPacket(buffSend, buffSend.length, packetReceived.getAddress(), packetReceived.getPort());
        return packetSend;
    }
    /*
    Deserialize Data
     */
    private Object deSerialize(DatagramPacket packet){
        Object object = null;
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            try(ObjectInputStream ois = new ObjectInputStream(bais)) {
                object =ois.readObject();
                System.out.println(object);
            } catch (ClassNotFoundException e) {
                System.out.println("Класс не найден");
            } catch (IOException e) {
                System.out.println("Ошибка десериализации");
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

    private void objectToString(Object o){
        try{
            System.out.println(o);
        }catch (NullPointerException e){

        }
    }

}
