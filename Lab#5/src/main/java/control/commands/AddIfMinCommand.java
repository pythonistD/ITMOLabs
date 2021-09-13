package control.commands;

import model.Dragon;

import java.time.LocalDateTime;
import java.util.Collections;

public class AddIfMinCommand extends Command {
    AddCommand addCommand = new AddCommand();
    DragonComparator dragonComparator = new DragonComparator();

    /**
     * Запуск команды
     */
    @Override
    public void execute() {
        Dragon dragonMin = findDragonMin();
        Dragon dragonNew = addCommand.createDragon();
        if (dragonComparator.compare(dragonMin, dragonNew) > 0) {
            dragonNew.inctCounter();
            dragonNew.setEndDate(LocalDateTime.now());
            Dragon.getDragonsCollection().add(dragonNew);
            System.out.println("Дракон минимален, поэтому успешно добавлен");
        }

    }

    /**
     * Поиск минимального Дракона
     *
     * @return Dragon
     */
    protected Dragon findDragonMin() {
        return Collections.min(Dragon.getDragonsCollection(), dragonComparator);
    }

}
