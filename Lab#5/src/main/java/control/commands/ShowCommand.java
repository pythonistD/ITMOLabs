package control.commands;

import model.Dragon;

import java.util.ListIterator;

public class ShowCommand extends Command {
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
