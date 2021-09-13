package control.commands;

import control.Response;
import control.Utility;
import model.Dragon;

import java.time.format.DateTimeFormatter;

public class InfoCommand extends Command{
    private static final long serialVersionUID = 13L;
    private Response response;
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response =new Response("info","Тип коллекции:Linked list" + "\n"
                + "Дата создания коллекции: " + formatter.format(Dragon.getStartDate()) + "\n"
                + "Дата последнего изменения коллекции: " + formatter.format(Dragon.getEndDate()) + "\n"
                + "Коллицество элементов коллекции: " + Utility.count(Dragon.getDragonsCollection()));
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
