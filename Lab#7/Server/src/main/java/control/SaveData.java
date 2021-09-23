package control;

import MyExceptions.CommandException;
import control.commands.SaveCommand;

import java.util.Scanner;

public class SaveData {
    private Scanner scanner;

    public SaveData(Scanner scanner){
        this.scanner=scanner;
    }

    public void saveCollection(){
        if(scanner.nextLine().equals("save")){
            SaveCommand saveCommand = new SaveCommand();
            try {
                saveCommand.execute();
            } catch (CommandException e) {
                e.printStackTrace();
            }
        }
    }

}
