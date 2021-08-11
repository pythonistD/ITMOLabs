package control.commands;

import model.Dragon;

import java.time.LocalDateTime;
import java.util.Collections;

public class AddIfMaxCommand extends Command {
    AddCommand addCommand = new AddCommand();
    DragonComparator dragonComparator = new DragonComparator();
    /**
     * Запуск команды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        Dragon dragonMax = findDragonMax();
        Dragon dragonNew = addCommand.createDragon();
        if (dragonComparator.compare(dragonMax, dragonNew) < 0) {
            dragonNew.inctCounter();
            dragonNew.setEndDate(LocalDateTime.now());
            Dragon.getDragonsCollection().add(dragonNew);
            System.out.println("Дракон максимален, поэтому успешно добавлен");
        }

    }

    /**
     * Поиск макимального Дракона
     * @return
     */

    protected Dragon findDragonMax() {
        return Collections.max(Dragon.getDragonsCollection(), dragonComparator);
    }

}
