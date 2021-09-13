package control.commands;

import MyExceptions.CommandException;
import control.Utility;
import model.Dragon;

import java.util.Collections;

public class PrintAscendingCommand extends Command {
    DragonComparator dragonComparator = new DragonComparator();
    SaveCommand save = new SaveCommand();
    ShowCommand show = new ShowCommand();

    /**
     * Запуск команды print ascending
     */
    @Override
    public void execute() throws CommandException {
        Collections.sort(Dragon.getDragonsCollection(), dragonComparator);
        Utility.reDefIds(Dragon.getDragonsCollection());
        System.out.println("Список успешно отсортирован");
        show.execute();


    }


}
