package control.commands;

import MyExceptions.CommandException;
import MyExceptions.IncorrectIdException;
import control.InfDeliverer;
import control.Information;
import model.Dragon;

import java.time.LocalDateTime;
import java.util.ListIterator;

public class UpdateCommand extends Command {
    private static final long serialVersionUID = 8L;
    private Information information;
    /**
     * Запуск комманды
     * @throws CommandException
     */
    @Override
    public void execute() throws CommandException {
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

    private void changeDragon(Dragon dragon, long id) throws CommandException {
        Dragon.getDragonsCollection().remove(dragon);
        AddCommand addCommand = new AddCommand();
        Dragon updatedDragon;
        try {
            updatedDragon = addCommand.createDragon();
        updatedDragon.setId(id);
        updatedDragon.setEndDate(LocalDateTime.now());
        Dragon.getDragonsCollection().add(updatedDragon);
        }catch (IncorrectIdException e){
            throw  CommandException.createExceptionChain(e,"ошибка во время обновления дракона");
        }
    }

}
