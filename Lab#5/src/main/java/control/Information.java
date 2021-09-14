package control;

/**
 * Забирает информацию из строки, введёной пользователем
 */
public class Information {
    Validator validator = new Validator();
    String modleType;
    String command;
    private boolean isSimeple = false;
    private boolean isHard = false;
    private long id;
    private String secField;

    public boolean takeInformation(String line) {
        String[] parsedLine = null;
        try {
            parsedLine = Parser.parseInputLine(line);
        }catch (NullPointerException e){
            System.exit(0);
        }
        command = parsedLine[0];
        isHard(command);
        isSimple(command);
        if (parsedLine.length == 1) {
            return true;
        }
        if (!validator.isString(parsedLine[1])) {
            id = Long.parseLong(parsedLine[1]);
        }
        if (validator.isString(parsedLine[1])) {
            secField = parsedLine[1];
        }
        if (parsedLine.length == 3) {
            modleType = Parser.deleteBrackets(parsedLine[2]);
        }
        return true;

    }

    public boolean isSimple(String command) {
        for (String s : Utility.avalibleSimpleCommands()) {
            if (command.equals(s)) {
                isSimeple = true;
                break;
            }
        }
        return isSimeple;
    }

    public boolean isHard(String command) {
        for (String s : Utility.avalibleHardCommands()) {
            if (command.equals(s)) {
                return isHard = true;
            }
        }
        return isHard;
    }

    public boolean getIsHard() {
        return isHard;
    }

    public String getCommand() {
        return command;
    }

    public long getId() {
        return id;
    }

    public String getSecField() {
        return secField;
    }

    public boolean getIsSimple() {
        return isSimeple;
    }


}
