package control;

import MyExceptions.CommandException;
import MyExceptions.IncorrectIdException;
import control.commands.CommandFactoryImpl;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Главный класс, который отвечает за консольный интерактивный режим
 */
public class Application {
    private static boolean loop = true;
    private final DataReader dataReader = new DataReader();
    private final CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
    private final Validator validator = new Validator();
    DataWriter dataWriter = new DataWriter();

    /**
     * Отвечает за остановку цикла(программы)
     *
     * @param loop если значение false, то остановка цикла
     */
    public static void setTreat(boolean loop) {
        Application.loop = loop;
    }

    public void consoleMod() {
        String line;
        try {
            Utility.createAvailableCommandsMap();
            DataReader.getCollectionData();
            dataWriter.writeCollectionData(DataReader.getCollectionData());
        } catch (IOException | IncorrectIdException e) {
            System.err.println(e);
        }
        while (loop) {
            Information information = new Information();
            try {
                line = dataReader.getConsoleData();
                information.takeInformation(line);
                InfDeliverer.setInf(information);
                validator.checkLine(information);
                commandFactoryImpl.chooseCommand(information.getCommand()).execute();
            } catch (CommandException e) {
                System.err.println(e);
                System.err.println(e.getCause());
            } catch (IllegalArgumentException badArgument) {
                System.out.print(badArgument.getMessage());
            } catch (NoSuchElementException | IllegalStateException e) {
                System.exit(0);
            } catch (Exception e) {
                System.out.println("Комманда введена неверно" + "\n" + "Попробуйте ввести ещё раз" + "\n" + "Чтобы получить список доступных команд напишите help");
            }

        }
    }

}
