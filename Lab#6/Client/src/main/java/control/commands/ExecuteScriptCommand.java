package control.commands;

import control.*;

import java.io.BufferedReader;
import java.io.Serializable;

public class ExecuteScriptCommand extends Command{
    private static final long serialVersionUID = 14L;
    private Information information = new Information();
    private Request request = new Request();

    /**
     * Выполняет запуск скрипта
     * @throws Exception
     */
    public void execute() throws Exception {
        String command;
        Information commandsInf;
        Validator validator = new Validator();
        Command commandObj;
        CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
        information = InfDeliverer.infDeliver();
        BufferedReader bufferedReader = DataReader.getData(information.getSecField());
        Utility.createAvailableCommandsMap();
        while (true){
            commandsInf = new Information();
            command = bufferedReader.readLine();
            if (command == null) {
                break;
            }
            try {
                commandsInf.takeInformation(command);
                validator.checkLine(commandsInf);
                if (commandsInf.getCommand().equals("execute_script")) {
                    throw new IllegalArgumentException("Рекурсивный вызов в script запрещён");
                }
            }catch (IllegalArgumentException e){
               System.out.println(e.getMessage());
               break;
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("выполнение script прервано");
                break;
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
