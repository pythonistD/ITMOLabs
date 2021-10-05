package control.commands;

import control.Client;
import control.Response;
import database.User;
import model.Dragon;

import java.util.ListIterator;

public class ShowCommand extends Command {
    private static final long serialVersionUID = 2L;
    private User user = Client.getUser();
    /**
     * Запуск комманды
     */
    @Override
    public void execute(){
        ListIterator<Dragon> itr = Dragon.getDragonsCollection().listIterator();
        while (itr.hasNext()) {
            System.out.print(itr.next().toString());
        }
    }
}
