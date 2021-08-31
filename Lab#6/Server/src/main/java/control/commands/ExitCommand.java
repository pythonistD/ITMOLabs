package control.commands;

import control.Response;
import control.Server;

public class ExitCommand extends Command{
    private Response response;
    private static final long serialVersionUID = 10L;
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() {
        Server.setProcessingStatus(false);
        response =  new Response("exit","Работа успешно завершена");
        System.out.println("Работа успешно завершена");
    }
    public Response getResponse(){
        return this.response;
    }
}
