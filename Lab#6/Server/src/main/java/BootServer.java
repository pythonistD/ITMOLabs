public class BootServer {
    public static void main(String[] args) {
        Server server = new Server(8888,10000);
        server.run();

    }
}
