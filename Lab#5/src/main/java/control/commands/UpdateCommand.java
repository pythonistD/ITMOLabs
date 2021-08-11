package control.commands;

import control.InfDeliverer;
import control.Information;
import model.Dragon;

import java.time.LocalDateTime;
import java.util.ListIterator;

public class UpdateCommand extends Command {
    private Information information = new Information();
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        information = InfDeliverer.infDeliver();
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
        if (flag == true) {
            System.out.println(dragon);
            changeDragon(dragon, information.getId());
            System.out.println("Дракон успешно изменён");
        } else {
            System.out.println("Нет такого Id");
        }

    }

    private void changeDragon(Dragon dragon, long id) throws Exception {
        Dragon.getDragonsCollection().remove(dragon);
        AddCommand addCommand = new AddCommand();
        Dragon updatedDragon = addCommand.createDragon();
        updatedDragon.setId(id);
        updatedDragon.setEndDate(LocalDateTime.now());
        Dragon.getDragonsCollection().add(updatedDragon);
    }

}
