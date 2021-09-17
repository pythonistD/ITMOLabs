package control;

import control.commands.Command;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class Request implements Serializable {
    private static final long serialVersionUID = 30L;
    private String commandName;
    private String commandStringArgument;
    private Serializable commandObjectArgument;
    private final Deque<Command> commandsDeque = new ArrayDeque<>();

    public Request(String commandName, Serializable commandObjectArgument){
        this.commandName=commandName;
        this.commandObjectArgument=commandObjectArgument;
    }
    public Request(Serializable commandObjectArgument){
        this.commandObjectArgument=commandObjectArgument;
    }
    public  Request(){}

    public void addCommandToRequest(Command command){
        this.commandsDeque.add(command);
    }


}
