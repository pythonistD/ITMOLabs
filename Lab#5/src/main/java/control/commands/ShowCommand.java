package control.commands;

import model.Dragon;

public class ShowCommand extends Command {
    /**
     * Запуск команды show
     */
    @Override
    public void execute() {
        for (Dragon dragon : Dragon.getDragonsCollection()) {
            System.out.print(dragon.toString());
        }
    }
}
