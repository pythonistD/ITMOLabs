package control.commands;

import model.Dragon;

public class PrintUniqueHeadCommand extends Command{
    private static final long serialVersionUID = 25L;
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute(){
        for (Dragon dragon : Dragon.getDragonsCollection()) {
            System.out.println(dragon.getName() + " toothCount:" + dragon.getHead().getToothCount());
        }

    }
}
