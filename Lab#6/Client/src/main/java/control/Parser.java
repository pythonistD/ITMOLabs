package control;

import MyExceptions.IncorrectIdException;
import model.Coordinates;
import model.Dragon;
import model.DragonHead;
import model.DragonType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Содержит методы для извелчения информации
 */
public class Parser {

    public static String getExtension(String fullFileName){
        // Подключил библиотеку, чтобы автоматически искала расширение файла org.apache.commons.io.FilenameUtils;
        return FilenameUtils.getExtension(fullFileName);
    }

    public static void csvparser(String currentline) throws IOException, IllegalArgumentException, IncorrectIdException {
        CSVParser parser = CSVParser.parse(currentline, CSVFormat.EXCEL);
        for (CSVRecord csvRecord : parser) {
             long id = Long.parseLong(csvRecord.get(0));
             ValidateFields.checkId(id);
            String name =ValidateFields.checkName(csvRecord.get(1));
            Long age = ValidateFields.checkAge(csvRecord.get(2));
            double wingspan = ValidateFields.checkWingSpan(csvRecord.get(3));
            boolean speaking = ValidateFields.checkSpeaking(csvRecord.get(4));
            double x = ValidateFields.checkX(csvRecord.get(5));
            double y = ValidateFields.checkY(csvRecord.get(6));
            Coordinates coordinates = new Coordinates(x, y);
            double tooth = ValidateFields.checkTooth(csvRecord.get(7));
            DragonHead dragonHead = new DragonHead(tooth);
            Dragon dragon = new Dragon(id,name, age, wingspan, speaking, coordinates, dragonHead, DragonType.valueOf(csvRecord.get(8)));
            dragon.setStartDate(LocalDateTime.now());
            dragon.setEndDate(LocalDateTime.now());
            Dragon.getDragonsCollection().add(dragon);
        }
    }

    public static String[] parseInputLine(String command) throws NullPointerException{
        String[] partsOfCommand;
        partsOfCommand = command.split(" ");
        return partsOfCommand;
    }

    public String deleteBrackets(String field) {
        String data = field.replaceAll("[{}]", "");
        return data;
    }



}
