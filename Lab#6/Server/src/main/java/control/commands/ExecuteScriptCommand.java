package control.commands;

import control.*;

import java.io.BufferedReader;
import java.io.Serializable;
import java.net.DatagramPacket;

public class ExecuteScriptCommand extends Command{
    private static final long serialVersionUID = 14L;
    private Response response;
//    private Validator validator = new Validator();
//    private CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
    private Information information;
    private transient byte[] buff = new byte[100000];
    private transient DatagramPacket packet;

    /**
     * Выполняет запуск скрипта
     * @throws Exception
     */
    public void execute() throws Exception {
        Server server;
        Command commandObj;
        Validator validator = new Validator();
        CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
        response = new Response("execute_script");
        BufferedReader bufferedReader = DataReader.getData(information.getSecField());
        Information informationFromScript = new Information();
        String command = "1";
        boolean flag = true;
        Utility.createAvailableCommandsMap();
        while (true) {
            command = bufferedReader.readLine();
            if (command == null) {
                System.out.println("Скрипт выполнен успешно");
                break;

            }
            try {
                informationFromScript.takeInformation(command);
                if(informationFromScript.getCommand().equals("execute_script")){
                    throw new IllegalArgumentException();
                }
                validator.checkLine(informationFromScript);
            } catch (Exception e) {
                System.out.println("В скрипте ошибка" + "\n" + "Выполнение сценария прервано");
                break;
            }
            commandObj = commandFactoryImpl.chooseCommand(informationFromScript.getCommand());
            commandObj.execute();
            response = commandObj.getResponse();
            buff = Server.serialization(response);
            packet = Server.createServerResponsePacket(buff);
            Server.sendServerResponse(packet);
        }
    }
    @Override
    public Response getResponse() {
        return response;
    }

}
