package control.commands;

import model.Dragon;

import java.time.LocalDateTime;
import java.util.Collections;

public class AddIfMinCommand extends Command {
    private static final long serialVersionUID = 7L;
    AddCommand addCommand = new AddCommand();
    /**
     * Запуск команды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        addCommand.createDragon();
    }

}
