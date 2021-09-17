package control.commands;

import MyExceptions.CommandException;
import control.InfDeliverer;
import control.Information;
import model.Dragon;

import java.time.LocalDateTime;
import java.util.ListIterator;

public class UpdateCommand extends Command {
    private static final long serialVersionUID = 8L;
    private Information information;
    private AddCommand addCommand;
    /**
     * Запуск команды
     */
    @Override
    public void execute() throws CommandException{
        information = InfDeliverer.infDeliver();
        addCommand = new AddCommand();
        addCommand.execute();
    }
}
