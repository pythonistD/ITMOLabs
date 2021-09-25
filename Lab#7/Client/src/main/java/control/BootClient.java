package control;

public class BootClient {
    public static void main(String[] args) {
        Client client = new Client("localhost", 8080);
        try {
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
