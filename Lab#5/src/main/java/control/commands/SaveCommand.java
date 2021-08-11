package control.commands;

import model.Dragon;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ListIterator;

public class SaveCommand extends Command {
    /**
     * Запуск комманды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        ListIterator<Dragon> dragonListIterator = Dragon.getDragonsCollection().listIterator();
        Writer out =
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream("CSV\\Dragon.csv"), StandardCharsets.UTF_8));
                Dragon dragonNext;
        while (dragonListIterator.hasNext()) {
            dragonNext = dragonListIterator.next();
            dragonNext.setEndDate(LocalDateTime.now());
            out.write(dragonNext.toString());
        }
        System.out.println("Изменения успешно сохранены");
        out.close();
    }
}
