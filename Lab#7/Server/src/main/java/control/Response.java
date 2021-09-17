package control;

import control.commands.Command;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private String commandName;
    private String commandStringArgument;
    private Serializable commandObjectArgument;
    private Deque<Response> responseDeque = new ArrayDeque<>();

    public Response(String commandName, String commandStringArgument, Serializable commandObjectArgument) {
        this.commandName = commandName;
        this.commandStringArgument = commandStringArgument;
        this.commandObjectArgument = commandObjectArgument;
    }

    public Response(String commandName, String commandStringArgument) {
        this.commandName = commandName;
        this.commandStringArgument = commandStringArgument;
    }

    public Response(String commandName) {
        this.commandName = commandName;
    }

    public Response() {
    }

    public void addNewResponse(Response response) {
        this.responseDeque.add(response);
    }

    public void sendResponse(Response response) {

    }

    public void viewResponse() {
        System.out.println(commandName + "\n" + commandStringArgument);
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandStringArgument() {
        return commandStringArgument;
    }

    public void setCommandStringArgument(String commandStringArgument) {
        this.commandStringArgument = commandStringArgument;
    }

    public Serializable getCommandObjectArgument() {
        return commandObjectArgument;
    }

    public void setCommandObjectArgument(Serializable commandObjectArgument) {
        this.commandObjectArgument = commandObjectArgument;
    }

    public Response extractHardRequest(Request req) throws Exception {
        Response response = new Response();
        Command command;
        Iterator<Command> commandIterator = req.getCommandsDeque().iterator();
        while (commandIterator.hasNext()) {
            command = commandIterator.next();
            command.execute();
            response.addNewResponse(command.getResponse());
        }
        return response;
    }

    public Deque<Response> getResponseDeque() {
        return responseDeque;
    }
}
