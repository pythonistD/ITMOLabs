package control;

import control.commands.Command;

import java.io.Serializable;
import java.util.Deque;

public class Request implements Serializable {
    private static final long serialVersionUID = 30L;
    private String commandName;
    private String commandStringArgument;
    private Command commandObjectArgument;
    private Deque<Command> commandsDeque;

    public boolean isHardRequest(){
        if(commandsDeque.size() == 0){
            return false;
        }
        return true;
    }

    public Deque<Command> getCommandsDeque() {
        return commandsDeque;
    }

    public Command getCommandObjectArgument() {
        return commandObjectArgument;
    }

}
