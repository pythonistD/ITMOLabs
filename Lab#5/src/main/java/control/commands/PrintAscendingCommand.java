package control.commands;

import model.Dragon;

import java.util.Collections;

public class PrintAscendingCommand extends Command {
    DragonComparator dragonComparator = new DragonComparator();
    SaveCommand save = new SaveCommand();
    ShowCommand show = new ShowCommand();
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception{
           Collections.sort(Dragon.getDragonsCollection(),dragonComparator);
           System.out.println("Список успешно отсортирован");
           save.execute();
           show.execute();


    }


}
