package control.commands;

import MyExceptions.CommandException;
import MyExceptions.IncorrectIdException;
import control.Response;
import model.Dragon;

import java.time.LocalDateTime;
import java.util.Collections;

public class AddIfMaxCommand extends Command {
    private static final long serialVersionUID = 6L;
    private Response response;
    private AddCommand addCommand;
    private DragonComparator dragonComparator;
    /**
     * Запуск команды
     * @throws CommandException
     */
    @Override
    public void execute() throws CommandException {
        try {
            Dragon dragonMax = findDragonMax();
            Dragon dragonNew = addCommand.createDragon();
            if (dragonComparator.compare(dragonMax, dragonNew) < 0) {
                dragonNew.inctCounter();
                dragonNew.setEndDate(LocalDateTime.now());
                Dragon.getDragonsCollection().add(dragonNew);
                response = new Response("addIfMax", "Дракон максимален, поэтому успешно добавлен");
            }
        }catch (IncorrectIdException e){
            CommandException commandException = new CommandException("ошибка при выполнении команды addIfMax");
            commandException.initCause(e);
            throw commandException;
        }

    }

    /**
     * Поиск макимального Дракона
     * @return
     */

    protected Dragon findDragonMax() {
        return Collections.max(Dragon.getDragonsCollection(), dragonComparator);
    }
    @Override
    public Response getResponse() {
        return response;
    }

}
