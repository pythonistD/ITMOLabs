package control.commands;

import control.Client;
import control.ConsoleMod;

public class ExitCommand extends Command{
    private static final long serialVersionUID = 10L;
    /**
     * Запуск комманды
     */
    @Override
    public void execute() {
        Client.setProcessingStatus(false);
    }
}
