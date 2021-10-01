package server;

import com.sun.xml.internal.ws.encoding.soap.DeserializationException;
import control.Request;
import database.Connections;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReceiveRequest implements Runnable {
    private DatagramPacket packetReceived;
    private InetAddress clientIp;
    private int clientPort;
    private int clientCounter = 0;
    //
    private DatagramSocket socket;
    //Thread pool
    private ExecutorService processTheadPool = Executors.newCachedThreadPool();


    public ReceiveRequest(DatagramPacket packet) {
        this.packetReceived = packet;
    }


    public static Request deSerialize(DatagramPacket packet) throws DeserializationException {
        Request object;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()))) {
            object = (Request) ois.readObject();
            System.out.println(object);
        } catch (InvalidClassException e) {
            throw new DeserializationException("Ошибка десериализации, id десериализируемых объектов не соответствуют");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DeserializationException("Ошибка десериализации,класс не найден");
        } catch (IOException e) {
            throw new DeserializationException("Ошибка десериализации");
        }
        return object;
    }

    public void getClientsAddress(DatagramPacket packet) {
        this.clientIp = packet.getAddress();
        this.clientPort = packet.getPort();
    }


    @Override
    public void run() {
        byte[] buffer = new byte[100000];
        DatagramPacket packetReceived = new DatagramPacket(buffer, buffer.length);
        getClientsAddress(packetReceived);
        Request request = deSerialize(packetReceived);
        Connections connection = new Connections(clientPort, clientIp, request.getUser());
        processTheadPool.execute(new HandleRequest(request, connection));
    }
}
