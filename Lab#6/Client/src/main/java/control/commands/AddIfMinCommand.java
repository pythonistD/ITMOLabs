package control.commands;

import model.Dragon;

import java.time.LocalDateTime;
import java.util.Collections;

public class AddIfMinCommand extends Command {
    private static final long serialVersionUID = 7L;
    private final AddCommand addCommand = new AddCommand();
    private final DragonComparator dragonComparator = new DragonComparator();
    /**
     * Запуск команды
     */
    @Override
    public void execute(){
        addCommand.createDragon();
    }

}
