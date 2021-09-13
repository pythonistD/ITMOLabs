package control.commands;

import control.InfDeliverer;
import control.Information;
import model.Dragon;

import java.time.LocalDateTime;
import java.util.ListIterator;

public class UpdateCommand extends Command {
    /**
     * Запуск команды update
     */
    @Override
    public void execute(){
        Information information = InfDeliverer.infDeliver();
        ListIterator<Dragon> dragonListIterator = Dragon.getDragonsCollection().listIterator();
        Dragon dragon = new Dragon();
        boolean flag = false;
        while (dragonListIterator.hasNext()) {
            dragon = dragonListIterator.next();
            if (dragon.getId() == information.getId()) {
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println(dragon);
            changeDragon(dragon, information.getId());
            System.out.println("Дракон успешно изменён");
        } else {
            System.out.println("Нет такого Id");
        }

    }

    private void changeDragon(Dragon dragon, long id){
        AddCommand addCommand = new AddCommand();
        Dragon.getDragonsCollection().remove(dragon);
        Dragon updatedDragon = addCommand.createDragon();
        updatedDragon.setId(id);
        updatedDragon.setEndDate(LocalDateTime.now());
        Dragon.getDragonsCollection().add(updatedDragon);
    }

}
