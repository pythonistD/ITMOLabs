package control.commands;

import MyExceptions.CommandException;
import MyExceptions.IncorrectIdException;
import control.Response;
import database.DataBase;
import model.Dragon;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;

public class AddIfMinCommand extends Command {
    private static final long serialVersionUID = 7L;
    private Response response;
    private AddCommand addCommand;
    private DragonComparator dragonComparator;

    /**
     * Запуск команды
     *
     * @throws CommandException
     */
    @Override
    public void execute() throws CommandException {
        Dragon dragonMin = findDragonMin();
        Dragon dragonNew = addCommand.createDragon();
        if (dragonComparator.compare(dragonMin, dragonNew) > 0) {
            dragonNew.inctCounter();
            dragonNew.setEndDate(LocalDateTime.now());
            Dragon.getDragonsCollection().add(dragonNew);
            try {
                DataBase.addDragonToDataBase(dragonNew);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response = new Response("addIfMin", "Дракон минимален, поэтому успешно добавлен");
        }

    }

    /**
     * Поиск минимального Дракона
     *
     * @return
     */
    protected Dragon findDragonMin() {
        return Collections.min(Dragon.getDragonsCollection(), dragonComparator);
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
