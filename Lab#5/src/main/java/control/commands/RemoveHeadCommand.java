package control.commands;

import control.Utility;
import model.Dragon;

public class RemoveHeadCommand extends Command {
    /**
     * Запуск команды remove head
     */
    @Override
    public void execute() {
        System.out.print(Dragon.getDragonsCollection().getFirst().toString());
        Dragon.getDragonsCollection().removeFirst();
        Utility.reDefIds(Dragon.getDragonsCollection());
        System.out.println("Удаление прошло успешно");

    }
}
