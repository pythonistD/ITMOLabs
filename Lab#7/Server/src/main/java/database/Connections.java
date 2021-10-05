package database;

import control.Server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connections that = (Connections) o;
        return port == that.port && clientAddress.equals(that.clientAddress) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, clientAddress, user);
    }
}
