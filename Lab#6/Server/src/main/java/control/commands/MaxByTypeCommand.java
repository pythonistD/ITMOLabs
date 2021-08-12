package control.commands;

import model.Dragon;

import java.util.ListIterator;

public class MaxByTypeCommand extends Command{
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception{
        ListIterator<Dragon> itr = Dragon.getDragonsCollection().listIterator();
        Dragon dragon = new Dragon();
        Dragon dragon1=itr.next();
        while (itr.hasNext()){
            dragon = itr.next();
            if(dragon1.getType().ordinal() > dragon.getType().ordinal()){
                dragon1 = dragon;
            }
        }
        System.out.println(dragon.toString());
    }
}
