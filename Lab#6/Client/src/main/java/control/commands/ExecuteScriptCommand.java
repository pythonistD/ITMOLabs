package control.commands;

import MyExceptions.CommandException;
import control.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExecuteScriptCommand extends Command {
    private static final long serialVersionUID = 14L;
    private final Request request = new Request();
    private Information information = new Information();

    /**
     * Выполняет запуск скрипта
     *
     * @throws Exception
     */
    public void execute() throws CommandException {
        BufferedReader bufferedReader;
        String command;
        Information commandsInf;
        Validator validator = new Validator();
        Command commandObj;
        CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
        information = InfDeliverer.infDeliver();
        try {
            bufferedReader = DataReader.getData(information.getSecField());
            Utility.createAvailableCommandsMap();
        } catch (FileNotFoundException e) {
            throw new CommandException("Ошибка в выполении скрипта, файл не найден");
        }
        while (true) {
            try {
                commandsInf = new Information();
                command = bufferedReader.readLine();
                if (command == null) {
                    break;
                }
                commandsInf.takeInformation(command);
                validator.checkLine(commandsInf);
                if (commandsInf.getCommand().equals("execute_script")) {
                    throw new IllegalArgumentException("Рекурсивный вызов в script запрещён");
                }
            }catch (IncorrectInputException | IllegalArgumentException | IOException e){
                CommandException commandException = new CommandException("ошибка во время исполнения команды execute_script");
                commandException.initCause(e);
                throw commandException;
            }
            commandObj = commandFactoryImpl.chooseCommand(commandsInf.getCommand());
            commandObj.execute();
            request.addCommandToRequest(commandObj);
        }
    }

    public Request getRequest() {
        return request;
    }
}
