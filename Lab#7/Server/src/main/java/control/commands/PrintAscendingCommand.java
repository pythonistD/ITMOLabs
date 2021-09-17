package control.commands;

import MyExceptions.CommandException;
import control.Response;
import control.Utility;
import model.Dragon;

public class PrintAscendingCommand extends Command {
    private static final long serialVersionUID = 24L;
    private Response response;

    /**
     * Запуск комманды
     *
     * @throws CommandException может вылететь во время исполнения команды save
     */
    @Override
    public void execute() throws CommandException {
        DragonComparator dragonComparator = new DragonComparator();
        ShowCommand show = new ShowCommand();
        Dragon.getDragonsCollection().sort(dragonComparator);
        Utility.reDefIds(Dragon.getDragonsCollection());
        response = new Response("printAscending", "Список успешно отсортирован");
        show.execute();
        response.addNewResponse(show.getResponse());
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
