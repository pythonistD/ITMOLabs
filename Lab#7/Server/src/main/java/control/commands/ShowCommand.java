package control.commands;

import control.Response;
import database.User;
import model.Dragon;

import java.util.ListIterator;

public class ShowCommand extends Command {
    private static final long serialVersionUID = 2L;
    private User user;
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
            Dragon dragon = itr.next();
            if(dragon.getOwner().equals(user.getName())) {
                field.append(itr.next().toString());
            }
        }
        response.setCommandStringArgument(field.toString());
    }

    @Override
    public Response getResponse() {
        return this.response;
    }
}
