package control;

import database.DataBase;
import database.UserHandler;

import java.sql.SQLException;
import java.util.Scanner;

public class BootServer {
    public static void main(String[] args) {
        DataReader.setInputfileCollection(args[0]);
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.readCollection();

        DataBase dataBase = new DataBase();
        dataBase.showDragon(dataBase.getById(1));
        UserHandler userHandler = new UserHandler();
        try {
            userHandler.addUser("new","qwe");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        SaveData saveData = new SaveData(scanner);
        Thread threadToCatchSaveCommandFromTerminal = new Thread(() -> {
            while (true) {
                saveData.saveCollection();
            }
        });
        threadToCatchSaveCommandFromTerminal.start();
        Server server = new Server(8080, 10000000);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
