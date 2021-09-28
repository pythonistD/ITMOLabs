package control;

import database.DataBase;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BootServer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SaveData saveData = new SaveData(scanner);
        Thread threadToCatchSaveCommandFromTerminal = new Thread(() -> {
            while (true) {
                saveData.saveCollection();
            }
        });
        threadToCatchSaveCommandFromTerminal.start();
        Server server = new Server(8080, 10000000);
        server.openSocket();
        DataBase.uploadDataFromDataBase(Server.getUser());
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}