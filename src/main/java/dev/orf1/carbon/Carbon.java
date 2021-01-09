package dev.orf1.carbon;

import dev.orf1.carbon.listener.impl.ConnectionListener;

import java.io.IOException;

public class Carbon {

    private static Carbon instance;
    private Server server;
    private Client client;
    private final boolean isServer;

    public Carbon(boolean isServer) throws IOException {
        instance = this;
        this.isServer = isServer;
        if (isServer) {
            server = new Server(42791);
            server.registerListener(new ConnectionListener());
            server.start();
        } else {
            client = new Client("192.168.1.1", 42791);
        }
    }

    public Client getClient() {
        return client;
    }

    public Server getServer() {
        return server;
    }

    public boolean isServer() {
        return isServer;
    }

    public static Carbon get() {
        return instance;
    }

    public static void main(String[] args) {
        try {
            new Carbon(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
