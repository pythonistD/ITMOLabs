package control.commands;

import control.Response;
import model.Dragon;

import java.util.ListIterator;

public class ShowCommand extends Command {
    private static final long serialVersionUID = 2L;
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        ListIterator<Dragon> itr = Dragon.getDragonsCollection().listIterator();
        while (itr.hasNext()) {
            System.out.print(itr.next().toString());
        }
    }
}
