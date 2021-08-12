package control;

import control.commands.*;
import model.Dragon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Различные полезные методы
 */
public class Utility {
    private static final ArrayList<String> promtstochange = new ArrayList<>();
    private static final ArrayList<String> promptstocreate = new ArrayList<>();
    private static final HashMap<String, Command> availableCommandsMap = new HashMap<>();

    public static ArrayList<String> promptsListtocreate() {
        promptstocreate.add("Введите имя дракона");
        promptstocreate.add("Сколько лет дракону");
        promptstocreate.add("Размах крыла");
        promptstocreate.add("Говорящий?\n" + "TRUE|FALSE");
        promptstocreate.add("Координата Х");
        promptstocreate.add("Координата У");
        promptstocreate.add("Количество зубов");
        promptstocreate.add("Тип дракона\n" + "Доступные типы:\n" + "FIRE\n" + "WATER\n" + "UNDERGROUND");
        promptstocreate.add("Дракон успешно добавлен");
        return promptstocreate;
    }

    public static int count(LinkedList<Dragon> elements) {
        return elements.size();
    }

    public ArrayList<String> avalibleCommandList() {
        ArrayList<String> avalibleCommand = new ArrayList<>();
        avalibleCommand.add("help");
        avalibleCommand.add("info");
        avalibleCommand.add("show");
        avalibleCommand.add("add");
        avalibleCommand.add("update");
        avalibleCommand.add("remove_by_id");
        avalibleCommand.add("clear");
        avalibleCommand.add("save");
        avalibleCommand.add("execute_script");
        avalibleCommand.add("exit");
        avalibleCommand.add("remove_head");
        avalibleCommand.add("add_if_max");
        avalibleCommand.add("add_if_min");
        avalibleCommand.add("max_by_type");
        avalibleCommand.add("print_ascending");
        avalibleCommand.add("print_unique_head");
        return avalibleCommand;
    }
    public static ArrayList<String> avalibleSimpleCommands(){
        ArrayList<String> avalibleSimpleCommands = new ArrayList<String>();
        avalibleSimpleCommands.add("help");
        avalibleSimpleCommands.add("info");
        avalibleSimpleCommands.add("clear");
        avalibleSimpleCommands.add("exit");
        avalibleSimpleCommands.add("save");
        avalibleSimpleCommands.add("remove_head");
        avalibleSimpleCommands.add("max_by_type");
        avalibleSimpleCommands.add("print_ascending");
        avalibleSimpleCommands.add("print_unique_head");
        return avalibleSimpleCommands;
    }
    public static ArrayList<String> avalibleHardCommands(){
        ArrayList<String> avalibleHardCommands = new ArrayList<String>();
        avalibleHardCommands.add("add");
        avalibleHardCommands.add("remove_by_id");
        avalibleHardCommands.add("execute_script");
        avalibleHardCommands.add("add_if_max");
        avalibleHardCommands.add("add_if_min");
        return avalibleHardCommands;
    }

    public static HashMap<String, Command> createAvailableCommandsMap() {
        availableCommandsMap.put("help", new HelpCommand());
        availableCommandsMap.put("info", new InfoCommand());
        availableCommandsMap.put("show", new ShowCommand());
        availableCommandsMap.put("add", new AddCommand());
        availableCommandsMap.put("update", new UpdateCommand());
        availableCommandsMap.put("remove_by_id", new RemoveByIdCommand());
        availableCommandsMap.put("clear", new ClearCommand());
        availableCommandsMap.put("save", new SaveCommand());
        availableCommandsMap.put("execute_script", new ExecuteScriptCommand());
        availableCommandsMap.put("exit", new ExitCommand());
        availableCommandsMap.put("remove_head", new RemoveHeadCommand());
        availableCommandsMap.put("add_if_max", new AddIfMaxCommand());
        availableCommandsMap.put("add_if_min", new AddIfMinCommand());
        availableCommandsMap.put("max_by_type", new MaxByTypeCommand());
        availableCommandsMap.put("print_ascending", new PrintAscendingCommand());
        availableCommandsMap.put("print_unique_head", new PrintUniqueHeadCommand());
        return availableCommandsMap;
    }

    public static HashMap<String, Command> getAvailableCommandsMap() {
        return availableCommandsMap;
    }
}
