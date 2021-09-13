package control.interfaces;

import control.commands.Command;

public interface CommandFactory {
    Command chooseCommand(String commandLine);
}
