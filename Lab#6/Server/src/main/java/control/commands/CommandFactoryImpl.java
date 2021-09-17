package control.commands;

import control.Utility;

import java.io.Serializable;

/**
 * Фабрика команд
 */
public class CommandFactoryImpl implements control.interfaces.CommandFactory, Serializable {
    private static final long serialVersionUID = 16L;

    /**
     * Выбирает нужную комманду из списка команд
     *
     * @param commandLine
     * @return
     */
    public Command chooseCommand(String commandLine) {
        return Utility.getAvailableCommandsMap().get(commandLine);
    }
}
