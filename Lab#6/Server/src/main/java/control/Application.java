package control;

import MyExceptions.IncorrectIdException;
import control.commands.CommandFactoryImpl;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;

/**
 * Главный класс, который отвечает за консольный интерактивный режим
 */
public class Application {
    private static boolean loop = true;
    private DataReader dataReader = new DataReader();
    private CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
    private Validator validator = new Validator();
    DataWriter dataWriter = new DataWriter();
    private Information information;
    private InfDeliverer infDeliverer;

    public void consoleMod() throws Exception {
        try {
            Utility.createAvailableCommandsMap();
            DataReader.getCollectionData();
            dataWriter.writeCollectionData(DataReader.getCollectionData());
            String line;
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
                commandFactoryImpl.chooseCommand(information.getCommand()).execute();
            }
        } catch (FileNotFoundException | UnsupportedEncodingException | IncorrectIdException noFile) {
            System.out.print(noFile.getMessage());
        }
    }

    /**
     * Отвечает за остановку цикла(программы)
     * @param loop
     */
    public static void setTreat(boolean loop) {
        Application.loop = loop;
    }


}
