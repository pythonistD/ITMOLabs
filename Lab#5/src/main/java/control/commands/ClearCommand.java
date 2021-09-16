package control.commands;

import model.Dragon;

import java.time.LocalDateTime;

/**
 * Запуск команды
 */
public class ClearCommand extends Command {
    public void execute() {
        Dragon.setEndDate(LocalDateTime.now());
        Dragon.getDragonsCollection().clear();
        System.out.println("Список успешно отчищен");
    }
}
