package database;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Connections {
    private int port;
    private InetAddress clientAddress;
    private User user;

    public Connections(InetSocketAddress address, User user) {
        this.port = address.getPort();
        this.clientAddress = address.getAddress() ;
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
