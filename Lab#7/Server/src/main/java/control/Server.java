package control;

import MyExceptions.ServerReceiveException;
import database.Connections;
import server.HandleRequest;
import server.ReceiveRequest;
import server.SendResponse;

import java.io.IOException;
import java.net.*;
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
    private final ExecutorService handleThreadsPool = Executors.newCachedThreadPool();
    private final ExecutorService sendResp = Executors.newCachedThreadPool();

    //List of current connections
    private static ConcurrentLinkedDeque<Connections> connections = new ConcurrentLinkedDeque<>();



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
                InetSocketAddress clientsAddress = getClientsAddress(packetReceived);
                Future<Request> future1 = receiveThreadsPool.submit(new ReceiveRequest(packet));
                Request request = future1.get();
                Connections connection = new Connections(clientsAddress, request.getUser());
                Future<Response> future2 = handleThreadsPool.submit(new HandleRequest(request,connection));
                Response response = future2.get();
                Future<Boolean> future3 = sendResp.submit(new SendResponse(connection,response));
                Boolean isSuccess = future3.get();
                if(!isSuccess){
                    System.out.println("Something went wrong");
                }
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
    public InetSocketAddress getClientsAddress(DatagramPacket packet) {
        InetSocketAddress address = null;
        try {
            InetAddress clientIp = packet.getAddress();
            System.out.println(clientIp);
            int clientPort = packet.getPort();
            System.out.println(clientPort);
            address = new InetSocketAddress(clientIp,clientPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
    public static boolean isInConnectionsMap(Connections connection) {
        if(connections.isEmpty()){
            return false;
        }
        return connections.contains(connection);
    }

    public synchronized static ConcurrentLinkedDeque<Connections> getConnections() {
        return connections;
    }

    public synchronized static void setConnections(ConcurrentLinkedDeque<Connections> connections) {
        Server.connections = connections;
    }
}
