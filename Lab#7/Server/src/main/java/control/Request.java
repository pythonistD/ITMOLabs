package control;

import control.commands.Command;
import database.User;

import java.io.Serializable;
import java.util.Deque;

public class Request implements Serializable {
    private static final long serialVersionUID = 30L;
    private String commandName;
    private String message;
    private Command command;
    private Deque<Command> commandsDeque;
    private User user;

    public Request(String message,Command command){
        this.message = message;
        this.command = command;
    }

    public Deque<Command> getCommandsDeque() {
        return commandsDeque;
    }

    public Command getCommand() {
        return command;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
