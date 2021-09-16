package control.commands;

import MyExceptions.CommandException;
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
    public void execute() throws CommandException {
        DragonComparator dragonComparator = new DragonComparator();
        ShowCommand show = new ShowCommand();
        SaveCommand save = new SaveCommand();
        Collections.sort(Dragon.getDragonsCollection(),dragonComparator);
        response = new Response("printAscending", "Список успешно отсортирован");
        save.execute();
        show.execute();
        response.addNewResponse(show.getResponse());
    }
    @Override
    public Response getResponse() {
        return response;
    }
}
