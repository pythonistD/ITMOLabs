package control;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Чтение данных
 */

public class DataReader {
    private static final BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedReader readFile;
    private static String inputfileCollection;

    /**
     * Интерфейс класса
     * @return BufferedReader для построчного чтения файла
     * @throws FileNotFoundException файл не найден
     * @throws UnsupportedEncodingException расширение файла не CSV
     */
    public static BufferedReader getCollectionData() throws FileNotFoundException, UnsupportedEncodingException {
        //Проверка правильности пути файла
        Validator.checkFileExist(inputfileCollection);
        // Проверка расширения файла, выкинет ошибку, если расширение не CSV
        Validator.checkFileExtension(inputfileCollection);
            return readFile = new BufferedReader(new InputStreamReader(new FileInputStream(inputfileCollection), StandardCharsets.UTF_8));
    }

    public static BufferedReader getData(String file) throws Exception {
        try {
            readFile = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        }catch (NullPointerException e){
            System.out.println("Нет пути к файлу");
        }
        return readFile;
    }

    public static BufferedReader getTreat() {
        return DataReader.consoleInput;
    }

    public static void setInputfileCollection(String inputfileCollection) {
        DataReader.inputfileCollection = inputfileCollection;
    }

    public String getConsoleData() throws Exception {
        String line;
        line = consoleInput.readLine();
        return line;
    }

    public static String getInputfileCollection() {
        return inputfileCollection;
    }
}
