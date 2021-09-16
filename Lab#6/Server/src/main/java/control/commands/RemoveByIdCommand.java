package control.commands;

import control.InfDeliverer;
import control.Information;
import control.Response;
import control.Utility;
import model.Dragon;

import java.util.ListIterator;

public class RemoveByIdCommand extends Command{
    private static final long serialVersionUID = 22L;
    private Response response;
    private Information information;
    /**
     * Запуск команды
     */
    public void execute(){
        Dragon dragon = getById(information.getId());
        response = new Response("removeById",dragon.toString() + "Удаление прошло успешно");
        Dragon.getDragonsCollection().remove(dragon);
        Utility.reDefIds(Dragon.getDragonsCollection());
    }
    public Dragon getById(long id) {
        return Dragon.getDragonsCollection().stream().filter(dragon -> dragon.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
