package control;
import model.ModelTypes;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Валидация данных
 */
public class Validator {
    private static long id;
    private static String filename;
    private final Parser parser = new Parser();
    private final Utility utility = new Utility();
    private String Command;

    // Проверка корректности команд

    public static long getId() {
        return id;
    }

    public static String getFilename() {
        return filename;
    }

    public static void checkFileExtension(String fullFileName) throws UnsupportedEncodingException {
        String extension =
                Parser.getExtension(fullFileName);
        if (!extension.equals("csv")) {
            throw new UnsupportedEncodingException("Неправильная кодировка файла\n +Поддерживаются файлы кодировки CSV");
        }
    }

    public static void checkFileExist(String filePath) throws FileNotFoundException {
        if (filePath.equals(null)) {
            throw new FileNotFoundException("Не правильно указан путь до CSV файла или не ввели его в аргументе коммандной строки");
        }
    }

    public void isRight(boolean flag) throws IncorrectInputException {
        if (!flag) {
            throw new IncorrectInputException("Команда введена некоректно");
        }
    }

    public boolean isString(String field) {
        try {
            Long.parseLong(field);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
    public boolean checkCommand(String command) {
        for (String s : utility.avalibleCommandList()) {
            if (s.equals(command)) {
                return true;
            }
        }
        return false;
    }

    public void checkLine(Information inf) throws Exception {
        if(!checkCommand(inf.getCommand())){
            throw new IncorrectInputException("wrong command");
        }
        if(inf.getIsSimple()){
            if(inf.getSecField() != null){
                throw new IncorrectInputException("To long command");
            }
        }
        if(inf.getIsHard()){
            if(inf.getCommand().equals("add") | inf.getCommand().equals("add_if_max") | inf.getCommand().equals("add_if_min")){
                checkModelType(inf.getSecField());
            }

        }
    }

    public void checkModelType(String modeltype) throws IncorrectInputException, NullPointerException {
        if(!modeltype.equals("{Dragon}")){
            throw new IncorrectInputException("wrong model type");
        }

    }

    public String getCommand() {
        return Command;
    }



}
