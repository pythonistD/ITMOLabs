import control.ConsoleMod;
import control.Response;
import control.commands.Command;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Client {
    private String hostName;
    private int port;
    private int reconnectionTime;
    private int counfOfReconnAttempts;
    private boolean processingStatus;
    private SocketAddress serverAddress;
    private DatagramChannel channel;
    private ByteBuffer bufferedDataReceive = ByteBuffer.allocate(100000);
    private ByteBuffer bufferedDataSend;

    private Response serverResponse;

    private ConsoleMod consoleMod = new ConsoleMod();
    public Client(String hostName, int port, int reconnectionTime, int counfOfReconnAttempts){
        this.hostName = hostName;
        this.port = port;
        this.reconnectionTime = reconnectionTime;
        this.counfOfReconnAttempts = counfOfReconnAttempts;
    }

    /*
    Start Client
     */
    public void  run() throws Exception {
        Command command = new Command();
        processingStatus = true;
        serverAddress = new InetSocketAddress(hostName,port);
        connectToServer();
        while (processingStatus){
            try {
                command = consoleMod.getDataFromKeyboard();
                command.execute();
                bufferedDataSend = serialize(command);
                channel.send(bufferedDataSend,serverAddress);
                bufferedDataSend.clear();
                serverAddress = channel.receive(bufferedDataReceive);
                serverResponse = deSerialize(bufferedDataReceive.array());
                bufferedDataReceive.clear();
                serverResponse.viewResponse();
            }catch (IOException e){
                System.out.println("Ошибка. Данные не отправлены");
            }catch (NullPointerException e1){
                e1.printStackTrace();
            }

        }
    }
    /*
    Connect to server
     */
    private void connectToServer(){
        try {
            channel = DatagramChannel.open();
            channel.socket().bind(null);
        } catch (IOException e) {
            System.out.println("Нет соединения с сервером");
        }
        System.out.println("Соединение с сервером установлено");
    }
    /*
    Send data
     */
    private ByteBuffer serialize(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
            oos.flush();
            oos.writeObject(o);
        }
        // wrap and send data
        byte[] buff = baos.toByteArray();
        return ByteBuffer.wrap(buff);
    }
        private Response deSerialize(byte[] buffer){
        Response object = null;
        try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
            object = (Response) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        } catch (IOException e) {
            e.printStackTrace();

        }
        return object;
    }

}
