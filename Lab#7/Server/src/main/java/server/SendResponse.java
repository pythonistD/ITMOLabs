package server;

import MyExceptions.ServerSendResponseException;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import control.Response;
import control.Server;
import database.Connections;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Callable;

public class SendResponse implements Callable<Boolean> {
    private Connections connections;
    private DatagramSocket socket;
    private Response response;

    public SendResponse(Connections connections, Response response) {
        this.connections = connections;
        this.socket = Server.getSocket();
        this.response = response;
    }


//    @Override
//    public void run() {
//        sendResp(response,connections);
//    }

    public void sendResp(Response response,Connections connections){
        try {
           DatagramPacket packetSend = createPacket(response,connections);
            sendServerResponse(packetSend);
        } catch (ServerSendResponseException e) {
            System.out.println(e.getMessage());
        }
    }

    public DatagramPacket createPacket(Response response,Connections connections) {
        byte[] buff;
        DatagramPacket packet;
        buff = serialization(response);
        packet = createServerResponsePacket(buff,connections);
        return packet;
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
    public DatagramPacket createServerResponsePacket(byte[] buffToSend,Connections connections) {
        return new DatagramPacket(buffToSend, buffToSend.length,connections.getClientAddress(),connections.getPort());
    }

    public void sendServerResponse(DatagramPacket serverResponsePacket) throws ServerSendResponseException {
        try {
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getState());
            System.out.println(serverResponsePacket);
            this.socket.send(serverResponsePacket);
        } catch (IOException e) {
            throw new ServerSendResponseException("Что-то пошло не так, данные не отправлены клиенту");
        }
        System.out.println("Данные успешно отправлены");
    }

    @Override
    public Boolean call() throws Exception {
        sendResp(response,connections);
        return true;
    }
}
