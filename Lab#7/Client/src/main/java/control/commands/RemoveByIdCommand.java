package control.commands;

import control.InfDeliverer;
import control.Information;
import model.Dragon;

import java.util.ListIterator;

public class RemoveByIdCommand extends Command{
    private static final long serialVersionUID = 22L;
    private Information information = new Information();
    /**
     * Запуск комманды
     */
    public void execute(){
        information = InfDeliverer.infDeliver();
    }
}
