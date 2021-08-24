public class BootClient {
    public static void main(String[] args) {
        Client client = new Client("localhost", 8888, 10000, 3);
        try {
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
