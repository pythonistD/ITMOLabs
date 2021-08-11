package control.commands;


import control.Utility;
import control.ValidateFields;
import model.Coordinates;
import model.Dragon;
import model.DragonHead;
import model.DragonType;

import java.time.LocalDateTime;
import java.util.ListIterator;

public class AddCommand extends Command {
    /**
     * Запуск команды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        try {
            Dragon dragon = createDragon();
            Dragon.getDragonsCollection().add(dragon);
            System.out.println("Дракон успешно добавлен");
        } catch (Exception e) {
            System.out.println("Введённые данные не корректны");
        }
    }

    /**
     * Интерактивный режим создания нового элемента коллекции(Dragon)
     * @return
     * @throws Exception
     */

    public Dragon createDragon() throws Exception {
        ListIterator<String> promptsiterator = Utility.promptsListtocreate().listIterator();
        String promptToChange = promptsiterator.next();
        long id = Dragon.getDragonsCollection().getLast().getId() + 1;
        System.out.println(promptToChange);
        String name = ValidateFields.checkNameInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        Long age = ValidateFields.checkAgeInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        Double wingspan = ValidateFields.checkWingSpanInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        Boolean speaking = ValidateFields.checkSpeakingInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        Double x = ValidateFields.checkXInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        Double y = ValidateFields.checkYInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        Double tooth = ValidateFields.checkToothCountInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        DragonType dragonType = ValidateFields.checkDragonTypeInteractive(promptToChange);
        Dragon dragon =
                new Dragon(id,name, age, wingspan, speaking, new Coordinates(x, y), new DragonHead(tooth), dragonType);
        dragon.setEndDate(LocalDateTime.now());
        return dragon;
    }
}
