package control;

import java.util.ListIterator;

/**
 * Забирает информацию из строки, введёной пользователем
 */
public class Information {
    Validator validator = new Validator();
    Parser parser = new Parser();
    String modleType;
    String command;
    private boolean isSimeple=false;
    private boolean isHard=false;
    private long id;
    private String secField;

    public boolean takeInformation(String line) throws Exception {
        String[] parsedLine = parser.parseInputLine(line);
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
            modleType = parser.deleteBrackets(parsedLine[2]);
        }
        return true;

    }
    public boolean isSimple(String command){
        ListIterator<String> itr = Utility.avalibleSimpleCommands().listIterator();
        while (itr.hasNext()){
            if(command.equals(itr.next())){
                isSimeple=true;
                break;
            }
        }
        return isSimeple;
    }
    public boolean isHard(String command){
        ListIterator<String> itr = Utility.avalibleHardCommands().listIterator();
        while (itr.hasNext()){
            if(command.equals(itr.next())){
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

    public String getModleType() {
        return modleType;
    }

    public String getSecField() {
        return secField;
    }
    public boolean getIsSimple(){
        return isSimeple;
    }


}
