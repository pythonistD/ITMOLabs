package control.commands;

import control.Application;

public class ExitCommand extends Command{
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() {
        Application.setTreat(false);
        System.out.println("Работа успешно завершена");
    }
}
