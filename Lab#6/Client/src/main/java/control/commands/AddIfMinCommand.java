package control.commands;

import model.Dragon;

import java.time.LocalDateTime;
import java.util.Collections;

public class AddIfMinCommand extends Command {
    private static final long serialVersionUID = 7L;
    private AddCommand addCommand = new AddCommand();
    private DragonComparator dragonComparator = new DragonComparator();
    /**
     * Запуск команды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        addCommand.createDragon();
    }

}
