public class BootServer {
    public static void main(String[] args) {
        Server server = new Server(8888,100000);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
