package control.commands;

import MyExceptions.CommandException;
import control.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExecuteScriptCommand extends Command {
    private final Validator validator = new Validator();
    private final CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();

    /**
     * Выполняет запуск скрипта
     * @throws CommandException
     */
    public void execute() throws CommandException {
        String command;
        Information information = InfDeliverer.infDeliver();
        Information scriptsCommandsInf;
        BufferedReader bufferedReader;
        try {
            bufferedReader = DataReader.getData(information.getSecField());
        }catch (FileNotFoundException e){
            throw CommandException.createExceptionChain(e,"Ошибка в выполнении скрипта, файл не найден");
        }
        while (true) {
            try {
                command = bufferedReader.readLine();
                if (command == null) {
                    System.out.println("Скрипт выполнен успешно");
                    break;
                }
                scriptsCommandsInf = new Information();
                scriptsCommandsInf.takeInformation(command);
                if (scriptsCommandsInf.getCommand().equals("execute_script")) {
                    throw new IllegalArgumentException("Рекурсивный вызов в script запрещён");
                }
                validator.checkLine(information);
                commandFactoryImpl.chooseCommand(scriptsCommandsInf.getCommand()).execute();
            }catch (IncorrectInputException | IllegalArgumentException | IOException e){
                throw  CommandException.createExceptionChain(e,"ошибка во время исполнения команды execute_script");
            }
        }

    }
}