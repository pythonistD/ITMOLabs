package control;

import database.DragonDataBase;

public class BootServer {
    public static void main(String[] args) {
//        DataReader.setInputfileCollection(args[0]);
//        CollectionManager collectionManager = new CollectionManager();
//        collectionManager.readCollection();
//        Server server = new Server(8888, 10000000);
//        try {
//            server.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        DragonDataBase dragonDataBase = new DragonDataBase();
        dragonDataBase.connectToDataBase();

    }
}
