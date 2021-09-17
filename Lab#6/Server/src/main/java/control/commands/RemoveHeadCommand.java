package control.commands;

import control.Response;
import control.Utility;
import model.Dragon;

public class RemoveHeadCommand extends Command {
    private static final long serialVersionUID = 21L;
    private Response response;

    /**
     * Запуск команды
     */
    @Override
    public void execute() {
        response = new Response("removeHead", Dragon.getDragonsCollection().getFirst().toString() + "Удаление прошло успешно");
        Dragon.getDragonsCollection().removeFirst();
        Utility.reDefIds(Dragon.getDragonsCollection());
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
