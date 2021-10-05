package control;

import MyExceptions.ServerReceiveException;
import database.Connections;
import server.ReceiveRequest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Server {
    private static DatagramSocket socket;
    private int port;
    private int timeout;

    private boolean running;
    //Threads
    private Thread receive, process, send;
    //Thread pools
    private ThreadPoolExecutor receiveThreadsPool;
    //List of current connections
    private ArrayList<Connections> connectionsList = new ArrayList<>();


    public Server(int port, int timeout) {
        this.port = port;
        this.timeout = timeout;
        try {
            socket = new DatagramSocket(port);
            receiveThreadsPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        } catch (SocketException e) {
            System.out.println("Ошибка иницилизации");
        }
    }

    public static synchronized DatagramSocket getSocket() {
        return Server.socket;
    }

    public void run() {
        running = true;
        System.out.println("Server started on port " + port);
        byte[] buffReceived = new byte[10000000];
        DatagramPacket packetReceived = new DatagramPacket(buffReceived, buffReceived.length);
        ExecutorService receiveThreadPool = Executors.newSingleThreadExecutor();
        Callable<DatagramPacket> receivePacket = () -> {
            try {
                receiveClientRequest(socket, packetReceived);
            } catch (ServerReceiveException e) {
                System.out.println("Something went wrong with receiving");
            }
            return packetReceived;
        };
        while (running) {
            Future<DatagramPacket> futureRes = receiveThreadPool.submit(receivePacket);
            try {
                DatagramPacket packet = futureRes.get();
                Future<Boolean> future = receiveThreadsPool.submit(new ReceiveRequest(packet));
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void receiveClientRequest(DatagramSocket socket, DatagramPacket packetReceived) throws ServerReceiveException {
        try {
            socket.receive(packetReceived);
            System.out.println(packetReceived);
        } catch (IOException e) {
            throw new ServerReceiveException("Что-то пошло не так, данные не получены от клиента");
        }
    }
}
