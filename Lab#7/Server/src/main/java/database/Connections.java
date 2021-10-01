package database;

import java.net.InetAddress;

public class Connections {
    private int port;
    private InetAddress clientAddress;
    private User user;

    public Connections(int port, InetAddress clientAddress, User user) {
        this.port = port;
        this.clientAddress = clientAddress;
        this.user = user;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public User getUser() {
        return user;
    }
}
