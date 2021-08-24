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
    private static final long serialVersionUID = 5L;
    private long id;
    private String name;
    private Long age;
    private Double wingspan;
    private Boolean speaking;
    private Double x;
    private Double y;
    private Double tooth;
    private DragonType dragonType;

    /**
     * Запуск команды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        try {
            createDragon();
        } catch (Exception e) {
            System.out.println("Введённые данные не корректны");
        }
    }

    /**
     * Интерактивный режим создания нового элемента коллекции(Dragon)
     * @return
     * @throws Exception
     */

    public void createDragon() throws Exception {
        ListIterator<String> promptsiterator = Utility.promptsListtocreate().listIterator();
        String promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        name = ValidateFields.checkNameInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        age = ValidateFields.checkAgeInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        wingspan = ValidateFields.checkWingSpanInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        speaking = ValidateFields.checkSpeakingInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        x = ValidateFields.checkXInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        y = ValidateFields.checkYInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        tooth = ValidateFields.checkToothCountInteractive(promptToChange);
        promptToChange = promptsiterator.next();
        System.out.println(promptToChange);
        dragonType = ValidateFields.checkDragonTypeInteractive(promptToChange);
    }
}
