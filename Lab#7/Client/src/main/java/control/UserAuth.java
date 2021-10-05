package control;

import MyExceptions.ClientReceiveResponseException;
import MyExceptions.ClientSendRequestException;
import database.User;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;

import static control.Client.serverTimeOut;

public class UserAuth {

    public static User userAuth() throws ClientSendRequestException, ClientReceiveResponseException {
        ByteBuffer buffer = ByteBuffer.allocate(100000);
        User user;
        while (true) {
            user = logOrSingUp();
            Request request = new Request(user);
            Client.sendClientRequest(Client.serialize(request), Client.getServerAddress());
            Thread waitingThread = serverTimeOut();
            Client.receiveServerResponse(buffer);
            waitingThread.interrupt();
            Response response = Client.deSerialize(buffer.array());
            System.out.println(response);
            buffer.clear();
            response.viewResponse();
            if(response.isFlag()){
                return user;
            }
        }
    }


    public static User logOrSingUp() {
        String inData;
        BufferedReader reader = DataReader.getTreat();
        User user;
        while (true) {
            System.out.println("Введите:login или sing up");
            try {
                inData = reader.readLine();
                if (inData.equals("login")) {
                    user = createUser();
                    user.setTarget("login");
                    break;
                } else if (inData.equals("sing up")) {
                    user = createUser();
                    user.setTarget("sing up");
                    break;
                } else {
                    System.out.println("Данные введены неверно " + "\n" + "Попробуйте ввести ещё раз");
                }
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }catch (NullPointerException e){
                System.out.println("Данные введены неверно " + "\n" + "Попробуйте ввести ещё раз");
            }
        }
        return user;
    }

    public static User createUser() {
        System.out.println("Введите имя пользователя");
        String name = ValidateFields.checkNameInteractive("Введите имя пользователя");
        System.out.println("Введите пароль");
        String pass = ValidateFields.checkPassInteractive("Введите пароль");
        return new User(name, pass);
    }


}
