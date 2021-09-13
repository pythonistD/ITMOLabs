package control.commands;

import model.Dragon;

public class RemoveHeadCommand extends Command {
    /**
     * Запуск команды remove head
     */
    @Override
    public void execute() {
        System.out.print(Dragon.getDragonsCollection().getFirst().toString());
        Dragon.getDragonsCollection().removeFirst();
        System.out.println("Удаление прошло успешно");

    }
}
