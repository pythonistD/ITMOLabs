import control.ConsoleMod;

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
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ByteBuffer bufferedData;
    private Object serverResponse;
    private ConsoleMod consoleMod = new ConsoleMod();


    private byte buffReceived[] = new byte[576];
    private byte buffSend[] = new byte[576];


    public Client(String hostName, int port, int reconnectionTime, int counfOfReconnAttempts){
        this.hostName = hostName;
        this.port = port;
        this.reconnectionTime = reconnectionTime;
        this.counfOfReconnAttempts = counfOfReconnAttempts;
    }

    /*
    Start Client
     */
    public void  run() {
        processingStatus = true;
        serverAddress = new InetSocketAddress(hostName,port);
        connectToServer();

        while (processingStatus){
            try {
                sendData(consoleMod.getDataFromKeyboard());
            }catch (IOException e){
                System.out.println("Ошибка. Данные не отправлены");
            }

        }
    }
    /*
    Connect to server
     */
    private void connectToServer(){
        try {
            channel = DatagramChannel.open();
            channel.connect(serverAddress);
        } catch (IOException e) {
            System.out.println("Нет соединения с сервером");
        }
    }
    /*
    Send data
     */
    public void sendData(Object o) throws IOException {
//        try {
            // serialization
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            // wrap and send data
            byte[] buff = baos.toByteArray();
            bufferedData = ByteBuffer.wrap(buff);
            channel.send(bufferedData,serverAddress);
            bufferedData.clear();
//        } catch (IOException e) {
//            System.out.println("Что то пошло не так:(");
//        }
    }
    /*
    Server response
     */
    private void getServerResponse(){
        try {
            SocketAddress responseAddress =
                    channel.receive(bufferedData);
            byte[] data = bufferedData.array();
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            serverResponse = ois;

        } catch (IOException e) {
            System.out.println("Ошибка в процессе полуения данных");
        }

    }
}
