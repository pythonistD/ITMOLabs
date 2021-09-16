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
     *
     * @return Reader, который позволяет читать данные из исходного файла построчно
     * @throws FileNotFoundException файл по указанному пути не найден
     * @throws UnsupportedEncodingException кодировка файла не CSV
     */
    public static BufferedReader getCollectionData() throws FileNotFoundException, UnsupportedEncodingException {
        Validator.checkFileExist(inputfileCollection);
        Validator.checkFileExtension(inputfileCollection);
        return readFile = new BufferedReader(new InputStreamReader(new FileInputStream(inputfileCollection), StandardCharsets.UTF_8));
    }

    public static BufferedReader getData(String file) throws FileNotFoundException {
        return readFile = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
    }

    public static BufferedReader getTreat() {
        return DataReader.consoleInput;
    }

    public static void setInputfileCollection(String inputfileCollection) {
        DataReader.inputfileCollection = inputfileCollection;
    }

    public static String getInputfileCollection() {
        return inputfileCollection;
    }

    public String getConsoleData() throws IOException {
        String line;
            line = consoleInput.readLine();
        return line;
    }
}
