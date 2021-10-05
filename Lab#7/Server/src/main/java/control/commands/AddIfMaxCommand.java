package control.commands;

import MyExceptions.CommandException;
import MyExceptions.IncorrectIdException;
import control.Response;
import database.DataBase;
import model.Dragon;

import java.sql.SQLException;
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
    public void execute(){
        Dragon dragonMax = findDragonMax();
        Dragon dragonNew = addCommand.createDragon();
        if (dragonComparator.compare(dragonMax, dragonNew) < 0) {
            dragonNew.inctCounter();
            dragonNew.setEndDate(LocalDateTime.now());
            Dragon.getDragonsCollection().add(dragonNew);
            try {
                DataBase.addDragonToDataBase(dragonNew);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response = new Response("addIfMax", "Дракон максимален, поэтому успешно добавлен");
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
