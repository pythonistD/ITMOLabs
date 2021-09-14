package control.commands;

import MyExceptions.CommandException;
import control.DataReader;
import model.Dragon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ListIterator;

public class SaveCommand extends Command {
    /**
     * Запуск команды save
     *
     * @throws CommandException выбрасывается, если возникла ошибка во время сохранения данных
     */
    @Override
    public void execute() throws CommandException {
        ListIterator<Dragon> dragonListIterator = Dragon.getDragonsCollection().listIterator();
        Writer out = openFile(DataReader.getInputfileCollection());
        Dragon dragonNext;
        while (dragonListIterator.hasNext()) {
            dragonNext = dragonListIterator.next();
            dragonNext.setEndDate(LocalDateTime.now());
            writeDragonToTheFile(out, dragonNext.toString());
        }
        System.out.println("Изменения успешно сохранены");
        closeWritingToTheFile(out);
    }

    private Writer openFile(String path) throws CommandException {
        try {
            return new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(path), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw CommandException.createExceptionChain(e, "ошибка во время выполнения команды save, файл не найден");
        }
    }

    private void writeDragonToTheFile(Writer out, String dragon) throws CommandException {
        try {
            out.write(dragon);
        } catch (IOException e) {
            throw CommandException.createExceptionChain(e, "ошибка во время выполнения команды save, проблемы с записью в файл");
        }
    }

    private void closeWritingToTheFile(Writer out) throws CommandException {
        try {
            out.close();
        } catch (IOException e) {
            throw CommandException.createExceptionChain(e, "ошибка во время выполнения команды save, проблемы с закрытием потока");
        }
    }
}
