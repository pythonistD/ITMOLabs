package control.commands;

import MyExceptions.CommandException;
import control.Utility;
import model.Dragon;

import java.util.Collections;

public class PrintAscendingCommand extends Command {
    DragonComparator dragonComparator = new DragonComparator();
    ShowCommand show = new ShowCommand();

    /**
     * Запуск команды print ascending
     */
    @Override
    public void execute() throws CommandException {
        Dragon.getDragonsCollection().sort(dragonComparator);
        Utility.reDefIds(Dragon.getDragonsCollection());
        System.out.println("Список успешно отсортирован");
        show.execute();


    }


}
