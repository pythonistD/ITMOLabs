package control.commands;

import control.InfDeliverer;
import control.Information;
import control.Utility;
import model.Dragon;

import java.util.ListIterator;

public class RemoveByIdCommand extends Command {
    /**
     * Запуск команды remove by id
     */
    public void execute() {
        Information information = InfDeliverer.infDeliver();
        ListIterator<Dragon> dragonListIterator = Dragon.getDragonsCollection().listIterator();
        long currentId;
        while (dragonListIterator.hasNext()) {
            Dragon dragon = dragonListIterator.next();
            currentId = dragon.getId();
            if (currentId == information.getId()) {
                System.out.print(dragon);
                System.out.println("Удаление прошло успешно");
                Dragon.getDragonsCollection().remove(dragon);
                Utility.reDefIds(Dragon.getDragonsCollection());
                break;
            }
        }
    }
}
