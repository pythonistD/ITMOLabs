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
                information.takeInformation(command);
                if (information.getCommand().equals("execute_script")) {
                    throw new IllegalArgumentException("Рекурсивный вызов в script запрещён");
                }
                validator.checkLine(information);
                commandFactoryImpl.chooseCommand(information.getCommand()).execute();
            }catch (IncorrectInputException | IllegalArgumentException | IOException e){
                throw  CommandException.createExceptionChain(e,"ошибка во время исполнения команды execute_script");
            }
        }

    }
}