package control.commands;

import MyExceptions.CommandException;
import control.Response;
import control.Utility;
import model.Dragon;

import java.util.Collections;

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
        ShowCommand show = new ShowCommand();
        Collections.sort(Dragon.getDragonsCollection(),(d1, d2) -> (int) (d1.getAge() - d2.getAge()));
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
