package control.commands;

import control.Application;

public class ExitCommand extends Command {
    /**
     * Запуск команды exit
     */
    @Override
    public void execute() {
        Application.setTreat(false);
        System.out.println("Работа успешно завершена");
    }
}
