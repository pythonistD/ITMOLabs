package server;

import MyExceptions.CommandException;
import control.Request;
import control.Response;
import control.commands.Command;
import database.Connections;
import database.User;
import database.UserHandler;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandleRequest implements Runnable {
    //Connections map
    private static final ConcurrentLinkedDeque<Connections> connections = new ConcurrentLinkedDeque<>();
    private User user;
    private Command command;
    private final Request clientRequest;
    private final Connections connection;
    private final ExecutorService sendResp = Executors.newCachedThreadPool();

    public HandleRequest(Request clientRequest, Connections connections) {
        this.clientRequest = clientRequest;
        this.connection = connections;
    }

    public Response createResponse() throws CommandException {
        Response response = new Response();
        if (!isInConnectionsMap(connection)) {
            response = loginOrSingUp(connection.getUser());
            connections.add(connection);
        }
        if(isInConnectionsMap(connection)){
            //TODO Парсинг такой идёт запроса клиента.Прописать отдельный парсер для этого дела?
            User user = clientRequest.getUser();
            Command command = clientRequest.getCommand();
            command.execute();
            response = command.getResponse();
        }
        return response;
    }


    public Response loginOrSingUp(User user) {
        Response response = null;
        try {
            if (user.getTarget().equals("login")) {
                if (!UserHandler.userExist(user.getName())) {
                    System.out.println("Пользователь успешно добавлен");
                    response = new Response("Пользователь успешно добавлен");
                    response.setFlag(true);
                }
            }
            if (user.getTarget().equals("sing up")) {
                response = UserHandler.addUser(user.getName(), user.getPass());
            }
        } catch (SQLException e) {
            response = new Response(e.getMessage());
        }
        return response;
    }

    private boolean isInConnectionsMap(Connections connection) {
        return connections.contains(connection);
    }


    @Override
    public void run() {
        try {
            Response response = createResponse();
            sendResp.execute(new SendResponse(connection,response));
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
}
