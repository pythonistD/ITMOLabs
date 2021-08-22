import control.CollectionManager;

public class BootServer {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.readCollection();
        Server server = new Server(8888,10000000);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
