package control.commands;

import control.Response;
import model.Dragon;

public class RemoveHeadCommand extends Command {
    private static final long serialVersionUID = 21L;
    private Response response;
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() {
        response = new Response("removeHead",Dragon.getDragonsCollection().getFirst().toString() + "Удаление прошло успешно");
        Dragon.getDragonsCollection().removeFirst();

    }

    @Override
    public Response getResponse() {
        return response;
    }
}
