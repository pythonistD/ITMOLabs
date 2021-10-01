package control;

import control.commands.Command;
import database.User;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class Request implements Serializable {
    private static final long serialVersionUID = 30L;
    private String commandName;
    private String message;
    private Command command;
    private Deque<Command> commandsDeque = new ArrayDeque<>();
    private User user;

    public Request(Command command){
        this.command = command;
    }

    public Request(User user) {
        this.user = user;
    }

    public  Request(){}

    public void addCommandToRequest(Command command){
        this.commandsDeque.add(command);
    }

    public User getUser() {
        return user;
    }
}
