package control;

import java.util.Scanner;

public class BootServer {
    public static void main(String[] args) {
        DataReader.setInputfileCollection(args[0]);
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.readCollection();
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
