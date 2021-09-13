package control.commands;

import control.Utility;

/**
 * Фабрика команд
 */
public class CommandFactoryImpl implements control.interfaces.CommandFactory {
    /**
     * Выбирает нужную комманду из списка команд
     *
     * @param commandLine - название команды
     * @return - объект, представляющий введённую команду
     */
    public Command chooseCommand(String commandLine) {
        return Utility.getAvailableCommandsMap().get(commandLine);
    }
}
