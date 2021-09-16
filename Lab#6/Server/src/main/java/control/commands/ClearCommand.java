package control.commands;

import control.Response;
import model.Dragon;

import java.time.LocalDateTime;

/**
 * Запуск команды
 */
public class ClearCommand extends Command {
    private static final long serialVersionUID = 20L;
    private Response response;
    public void execute(){
        Dragon.setEndDate(LocalDateTime.now());
        Dragon.getDragonsCollection().clear();
        response = new Response("clear","Список успешно отчищен");
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
