package control.commands;

import MyExceptions.CommandException;
import control.*;
import java.util.Iterator;

public class ExecuteScriptCommand extends Command{
    private static final long serialVersionUID = 14L;
    private Request request;
    private Response response;
    private Information information;

    /**
     * Выполняет запуск скрипта
     * @throws Exception
     */
    public void execute() throws CommandException{
        response = new Response();
        Command command;
        Iterator<Command> commandItr = request.getCommandsDeque().iterator();
        while (commandItr.hasNext()){
                command = commandItr.next();
                command.execute();
                response.addNewResponse(command.getResponse());
        }
    }
    @Override
    public Response getResponse() {
        return this.response;
    }

}
