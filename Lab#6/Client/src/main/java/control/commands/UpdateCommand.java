package control.commands;

import control.InfDeliverer;
import control.Information;
import model.Dragon;

import java.time.LocalDateTime;
import java.util.ListIterator;

public class UpdateCommand extends Command {
    private static final long serialVersionUID = 8L;
    private Information information;
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        information = InfDeliverer.infDeliver();
    }
}
