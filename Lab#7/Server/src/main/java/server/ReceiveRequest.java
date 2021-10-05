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
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReceiveRequest implements Callable<Request> {
    //Thread pool
    private static final ExecutorService processTheadPool = Executors.newCachedThreadPool();
    private DatagramPacket packetReceived;
    private int clientPort;
    private int clientCounter = 0;
    //
    private DatagramSocket socket;


    public ReceiveRequest(DatagramPacket packet) {
        this.packetReceived = packet;
    }


    public static Request deSerialize(DatagramPacket packet) throws DeserializationException {
        Request object;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()))) {
            object = (Request) ois.readObject();
        } catch (InvalidClassException e) {
            throw new DeserializationException("Ошибка десериализации, id десериализируемых объектов не соответствуют");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DeserializationException("Ошибка десериализации,класс не найден");
        } catch (IOException e) {
            e.printStackTrace();
            throw new DeserializationException("Ошибка десериализации");
        }
        return object;
    }

    @Override
    public Request call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getState());
        return deSerialize(packetReceived);
    }
}
