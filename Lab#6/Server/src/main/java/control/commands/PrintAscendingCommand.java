package control.commands;

import control.Response;
import model.Dragon;

import java.util.Collections;

public class PrintAscendingCommand extends Command {
    private static final long serialVersionUID = 24L;
    private Response response;
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception{
        DragonComparator dragonComparator = new DragonComparator();
        ShowCommand show = new ShowCommand();
           Collections.sort(Dragon.getDragonsCollection(),dragonComparator);
           response = new Response("printAscending", "Список успешно отсортирован");
//           show.execute();
//           response.addNewResponse(show.getResponse());
    }
    @Override
    public Response getResponse() {
        return response;
    }
}
