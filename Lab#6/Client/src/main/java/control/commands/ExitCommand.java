package control.commands;

import control.ConsoleMod;

public class ExitCommand extends Command{
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() {
        ConsoleMod.setTreat(false);
        System.out.println("Работа успешно завершена");
    }
}
