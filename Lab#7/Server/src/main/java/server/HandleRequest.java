package server;

import MyExceptions.CommandException;
import control.Request;
import control.Response;
import control.commands.Command;
import database.Connections;
import database.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandleRequest implements Callable<Response> {
    //Connections map
    private static final ExecutorService sendResp = Executors.newCachedThreadPool();
    private final Request clientRequest;
    private final Connections connection;


    public HandleRequest(Request clientRequest, Connections connections) {
        this.clientRequest = clientRequest;
        this.connection = connections;
    }

    public Response createResponse() throws CommandException {
        Response response = new Response();
        User user = clientRequest.getUser();
        Command command = clientRequest.getCommand();
        try {
            if (command != null) {
                command.execute();
                response = command.getResponse();
            }
        } catch (CommandException e) {
            e.printStackTrace();
            System.out.println("Something went wrong with command execution");
        }

        System.out.println(response);
        return response;
    }


    @Override
    public Response call() {
        Response response = null;
        try {
            response = createResponse();
        } catch (CommandException e) {
            e.printStackTrace();
        }

        return response;
    }
}
