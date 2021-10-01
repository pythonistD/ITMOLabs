package control;

import MyExceptions.ServerReceiveException;
import database.Connections;
import server.ReceiveRequest;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private int port;
    private static DatagramSocket socket;
    private int timeout;

    private boolean running;
    //Threads
    private Thread receive, process, send;
    //Thread pools
    private ExecutorService receiveThreadsPool = Executors.newFixedThreadPool(10);
    //List of current connections
    private ArrayList<Connections> connectionsList = new ArrayList<>();


    public Server(int port, int timeout) {
        this.port = port;
        this.timeout = timeout;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("Ошибка иницилизации");
        }
    }
//    public void startListening(){
//        try {
//            socket.bind(new InetSocketAddress(port));
//        } catch (SocketException e) {
//            e.printStackTrace();
//            System.out.println("Не удалось подключиться к порту " + port);
//            System.exit(0);
//        }
//    }

    private void init() throws SocketException {
        this.socket = new DatagramSocket();
    }



    @Override
    public void run() {
        running = true;
        System.out.println("Server started on port " + port);
        byte[] buffReceived = new byte[100000];
        DatagramPacket packetReceived = new DatagramPacket(buffReceived, buffReceived.length);
        while (running) {
            try {
                receiveClientRequest(socket,packetReceived);
                receiveThreadsPool.execute(new ReceiveRequest(packetReceived));
            } catch (ServerReceiveException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized DatagramSocket getSocket() {
        return Server.socket;
    }

    public void receiveClientRequest(DatagramSocket socket, DatagramPacket packetReceived) throws ServerReceiveException {
        try {
            socket.receive(packetReceived);
            System.out.println(packetReceived);
        } catch (IOException e) {
            throw new ServerReceiveException("Что-то пошло не так, данные не отправлены клиенту");
        }
        System.out.println("Данные успешно отправлены");
    }
}
