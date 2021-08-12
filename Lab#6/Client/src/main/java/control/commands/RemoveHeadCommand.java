package control.commands;

import model.Dragon;

public class RemoveHeadCommand extends Command {
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() {
        System.out.print(Dragon.getDragonsCollection().getFirst().toString());
        Dragon.getDragonsCollection().removeFirst().toString();
        System.out.println("Удаление прошло успешно");

    }
}
