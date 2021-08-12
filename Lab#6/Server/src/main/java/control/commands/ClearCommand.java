package control.commands;

import model.Dragon;

import java.time.LocalDateTime;

/**
 * Запуск команды
 * @throws Exception
 */
public class ClearCommand extends Command {
    public void execute() throws Exception {
        Dragon.setEndDate(LocalDateTime.now());
        Dragon.getDragonsCollection().clear();
        System.out.println("Список успешно отчищен");
    }
}
