package control.commands;

import control.Response;
import model.Dragon;

import java.util.ListIterator;

public class MaxByTypeCommand extends Command{
    private static final long serialVersionUID = 20L;
    private Response response;
    /**
     * Запуск команды
     */
    @Override
    public void execute(){
        ListIterator<Dragon> itr = Dragon.getDragonsCollection().listIterator();
        Dragon dragon = new Dragon();
        Dragon dragon1=itr.next();
        while (itr.hasNext()){
            dragon = itr.next();
            if(dragon1.getType().ordinal() > dragon.getType().ordinal()){
                dragon1 = dragon;
            }
        }
        response = new Response("maxByType",dragon.toString());
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
