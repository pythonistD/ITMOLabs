package control.commands;

import control.Response;
import model.Dragon;

import java.util.ListIterator;

public class ShowCommand extends Command {
    private static final long serialVersionUID = 2L;
    private Response response;

    /**
     * Запуск команды
     */
    @Override
    public void execute() {
        response = new Response("show");
        StringBuilder field = new StringBuilder();
        ListIterator<Dragon> itr = Dragon.getDragonsCollection().listIterator();
        while (itr.hasNext()) {
            field.append(itr.next().toString());
        }
        response.setCommandStringArgument(field.toString());
    }

    @Override
    public Response getResponse() {
        return this.response;
    }
}
