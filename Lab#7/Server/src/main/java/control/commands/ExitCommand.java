package control.commands;

import control.Response;
import control.Server;

public class ExitCommand extends Command {
    private static final long serialVersionUID = 10L;
    private Response response;

    /**
     * Запуск команды
     */
    @Override
    public void execute() {
        System.exit(0);
        response = new Response("exit", "Работа успешно завершена");
        System.out.println("Работа успешно завершена");
    }

    public Response getResponse() {
        return this.response;
    }
}
