package control.commands;

import control.DataReader;
import control.InfDeliverer;
import control.Information;
import control.Validator;

import java.io.BufferedReader;

public class ExecuteScriptCommand extends Command {
    private Validator validator = new Validator();
    private CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
    private Information information = new Information();

    /**
     * Выполняет запуск скрипта
     * @throws Exception
     */
    public void execute() throws Exception {
        information = InfDeliverer.infDeliver();
        BufferedReader bufferedReader = DataReader.getData(information.getSecField());
        String command = "1";
        boolean flag = true;
        while (true) {
            command = bufferedReader.readLine();
            if (command == null) {
                System.out.println("Скрипт выполнен успешно");
                break;

            }
            try {
                information.takeInformation(command);
                if(information.getCommand().equals("execute_script")){
                    throw new IllegalArgumentException();
                }
                validator.checkLine(information);
            } catch (Exception e) {
                System.out.println("В скрипте ошибка" + "\n" + "Выполнение сценария прервано");
                break;
            }
            commandFactoryImpl.chooseCommand(information.getCommand()).execute();

        }
    }

}
