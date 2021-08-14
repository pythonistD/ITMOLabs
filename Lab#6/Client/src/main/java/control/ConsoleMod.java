package control;

import MyExceptions.IncorrectIdException;
import control.commands.Command;
import control.commands.CommandFactoryImpl;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;

/**
 * Главный класс, который отвечает за консольный интерактивный режим
 */
public class ConsoleMod {
    private static boolean loop = true;
    private DataReader dataReader = new DataReader();
    private CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
    private Validator validator = new Validator();
    private Information information;
    private InfDeliverer infDeliverer;

    public Command getDataFromKeyboard() {
            Utility.createAvailableCommandsMap();
            String line;
            Command command = new Command();
            while (loop) {
                information = new Information();
                try {
                    line = dataReader.getConsoleData();
                    information.takeInformation(line);
                    infDeliverer = new InfDeliverer(information);
                    validator.checkLine(information);
                } catch (IllegalArgumentException badArgument) {
                    System.out.print(badArgument.getMessage());
                } catch (NoSuchElementException | IllegalStateException e) {
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println("Комманда введена неверно" + "\n" + "Попробуйте ввести ещё раз" + "\n" + "Чтобы получить список доступных команд напишите help");
                    continue;
                }
                command = commandFactoryImpl.chooseCommand(information.getCommand());
                break;
            }
            return command;
    }

    /**
     * Отвечает за остановку цикла(программы)
     * @param loop
     */
    public static void setTreat(boolean loop) {
        ConsoleMod.loop = loop;
    }


}
