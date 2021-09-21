package control.commands;

import MyExceptions.CommandException;
import MyExceptions.IncorrectIdException;
import control.Information;
import control.Response;
import model.Dragon;

import java.time.LocalDateTime;

public class UpdateCommand extends Command {
    private static final long serialVersionUID = 8L;
    private Response response;
    private AddCommand addCommand;
    private Information information;

    /**
     * Запуск команды
     *
     * @throws CommandException ошибка во время исполнения команды update
     */
    @Override
    public void execute() throws CommandException {
//        ListIterator<Dragon> dragonListIterator = Dragon.getDragonsCollection().listIterator();
//        Dragon dragon = new Dragon();
//        boolean flag = false;
//        while (dragonListIterator.hasNext()) {
//            dragon = dragonListIterator.next();
//            if (dragon.getId() == information.getId()) {
//                flag = true;
//                break;
//            }
//        }
        Dragon dragon;
        dragon = getById(information.getId());
        if(dragon == null){
            throw CommandException.createExceptionChain(new NullPointerException("Нет такого id"),"ошибка во время исполнения команды update");
        }
        response = new Response("update", dragon.toString() + "\n" + "Дракон успешно изменён");
        changeDragon(dragon, information.getId());
//        if (flag) {
//            response = new Response("update", dragon.toString() + "\n" + "Дракон успешно изменён");
//            changeDragon(dragon, information.getId());
//        } else {
//            System.out.println("Нет такого Id");
//        }

    }

    private void changeDragon(Dragon dragon, long id) throws CommandException {
        int index = Dragon.getDragonsCollection().indexOf(dragon);
        Dragon.getDragonsCollection().remove(dragon);
        Dragon updatedDragon;
        try {
            updatedDragon = addCommand.createDragon();
            updatedDragon.setId(id);
            updatedDragon.setEndDate(LocalDateTime.now());
            Dragon.getDragonsCollection().add(index, updatedDragon);
        } catch (IncorrectIdException e) {
            throw CommandException.createExceptionChain(e, "ошибка во время обновления дракона");
        }
    }

    @Override
    public Response getResponse() {
        return response;
    }
}