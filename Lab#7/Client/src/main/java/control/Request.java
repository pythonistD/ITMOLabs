package control;

import control.commands.Command;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class Request implements Serializable {
    private static final long serialVersionUID = 30L;
    private String commandName;
    private String message;
    private Serializable objectArgument;
    private final Deque<Command> commandsDeque = new ArrayDeque<>();

    public Request(Serializable objectArgument){
        this.objectArgument = objectArgument;
    }
    public Request(String message,Serializable objectArgument){
        this.message = message;
        this.objectArgument = objectArgument;
    }

    public  Request(){}

    public void addCommandToRequest(Command command){
        this.commandsDeque.add(command);
    }


}
