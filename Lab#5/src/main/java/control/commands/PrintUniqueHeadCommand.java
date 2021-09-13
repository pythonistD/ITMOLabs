package control.commands;

import model.Dragon;

public class PrintUniqueHeadCommand extends Command{
    /**
     * Запуск команды print unique head
     */
    @Override
    public void execute(){
        for (Dragon dragon : Dragon.getDragonsCollection()) {
            System.out.println(dragon.getName() + " toothCount:" + dragon.getHead().getToothCount());
        }

    }
}
