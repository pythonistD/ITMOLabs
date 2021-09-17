package control.commands;

import control.Response;
import model.Dragon;

public class PrintUniqueHeadCommand extends Command {
    private static final long serialVersionUID = 25L;
    private Response response;

    /**
     * Запуск команды
     */
    @Override
    public void execute() {
        for (Dragon dragon : Dragon.getDragonsCollection()) {
            response = new Response("printUniqueHead", "name: " + dragon.getName() + " toothCount:" + dragon.getHead().getToothCount());
        }

    }

    @Override
    public Response getResponse() {
        return response;
    }
}
