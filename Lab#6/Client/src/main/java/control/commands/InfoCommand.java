package control.commands;

import control.Utility;
import model.Dragon;

import java.time.format.DateTimeFormatter;

public class InfoCommand extends Command{
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Тип коллекции:Linked list" + "\n"
                + "Дата создания коллекции: " + formatter.format(Dragon.getStartDate()) + "\n"
                + "Дата последнего изменения коллекции: " + formatter.format(Dragon.getEndDate()) + "\n"
                + "Коллицество элементов коллекции: " + Utility.count(Dragon.getDragonsCollection()));

    }


}
