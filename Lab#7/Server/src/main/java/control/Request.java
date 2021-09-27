package control;

import control.commands.Command;

import java.io.Serializable;
import java.util.Deque;

public class Request implements Serializable {
    private static final long serialVersionUID = 30L;
    private String commandName;
    private String message;
    private Serializable objectArgument;
    private Deque<Command> commandsDeque;

    public Request(String message,Serializable objectArgument){
        this.message = message;
        this.objectArgument = objectArgument;
    }

    public Deque<Command> getCommandsDeque() {
        return commandsDeque;
    }

    public Serializable getObjectArgument() {
        return objectArgument;
    }

    public String getMessage() {
        return message;
    }
}
