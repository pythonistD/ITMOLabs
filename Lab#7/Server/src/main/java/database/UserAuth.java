package database;

import MyExceptions.ServerReceiveException;
import MyExceptions.ServerSendResponseException;
import control.Request;
import control.Response;
import control.Server;

import java.net.DatagramPacket;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;

public class UserAuth {
    private static User user;

    private static User authUser(){
        DatagramPacket packet = null;
        DatagramPacket packetSend = null;
        while (true){
            Response response = new Response();
            try {
                Server.receiveClientRequest(packet);
                Request clientRequest = Server.deSerialize(packet);
                user = (User) clientRequest.getObjectArgument();
                System.out.println(user.getTarget());
                if (user.getTarget().equals("login")) {
//                    response = UserHandler.userExist(user.getName());
                }
                if (user.getTarget().equals("sing up")) {
                    response = UserHandler.addUser(user.getName(), user.getPass());
                }
            }catch (ServerReceiveException | SQLException e){
                response.setCommandStringArgument(e.getMessage());
            }
            try {
                packetSend = Server.createPacket(response);
                Server.sendServerResponse(packetSend);
            }catch (ServerSendResponseException e){
                System.out.println(e.getMessage());
            }

        }
    }
}
