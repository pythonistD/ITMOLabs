package control.commands;

import control.InfDeliverer;
import control.Information;
import model.Dragon;

import java.util.ListIterator;

public class RemoveByIdCommand extends Command{
    private Information information = new Information();
    /**
     * Запуск комманды
     * @throws Exception
     */
    public void execute(){
        information = InfDeliverer.infDeliver();
        ListIterator<Dragon > dragonListIterator = Dragon.getDragonsCollection().listIterator();
        long currentId;
        while (dragonListIterator.hasNext()){
            Dragon dragon = dragonListIterator.next();
            currentId = dragon.getId();
            if(currentId == information.getId()){
                System.out.println(dragon.toString());
                System.out.print("Удаление прошло успешно");
                Dragon.getDragonsCollection().remove(dragon);
                break;
            }
        }
    }
}
