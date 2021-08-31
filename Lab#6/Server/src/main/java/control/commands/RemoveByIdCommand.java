package control.commands;

import control.InfDeliverer;
import control.Information;
import control.Response;
import model.Dragon;

import java.util.ListIterator;

public class RemoveByIdCommand extends Command{
    private static final long serialVersionUID = 22L;
    private Response response;
    private Information information;
    /**
     * Запуск комманды
     * @throws Exception
     */
    public void execute(){
        ListIterator<Dragon > dragonListIterator = Dragon.getDragonsCollection().listIterator();
        long currentId;
        while (dragonListIterator.hasNext()){
            Dragon dragon = dragonListIterator.next();
            currentId = dragon.getId();
            if(currentId == information.getId()){
                response = new Response("removeById",dragon.toString() + "Удаление прошло успешно");
                Dragon.getDragonsCollection().remove(dragon);
                break;
            }
        }
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
