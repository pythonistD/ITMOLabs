package control;

import java.util.Scanner;

public class BootServer {
    public static void main(String[] args) {
        //Отлавливает команду save из консоли сервера
        Scanner scanner = new Scanner(System.in);
        SaveData saveData = new SaveData(scanner);
        Thread threadToCatchSaveCommandFromTerminal = new Thread(() -> {
            while (true) {
                saveData.saveCollection();
            }
        });
        threadToCatchSaveCommandFromTerminal.start();
        //Запуск сервера
        Server server = new Server(8080, 10000000);
//        server.startListening();
        server.run();





    }
}

