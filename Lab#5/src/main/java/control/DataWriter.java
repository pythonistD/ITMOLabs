package control;

import MyExceptions.IncorrectIdException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Запись данных из файла в коллекцию
 */
public class DataWriter {

    public void writeCollectionData(BufferedReader collectionData) throws IOException, IllegalArgumentException, IncorrectIdException {
        String currentline;
        while ((currentline = collectionData.readLine()) != null) {
            Parser.csvparser(currentline);
        }
        collectionData.close();
    }
}
