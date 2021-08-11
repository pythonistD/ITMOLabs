package control.interfaces;

import control.commands.Command;

public interface CommandFactory {
    public Command chooseCommand(String commandLine);
}
