package dev.orf1.carbon;

import dev.orf1.carbon.listener.impl.ConnectionListener;
import dev.orf1.carbon.util.Logger;
import lombok.Getter;

import java.io.IOException;

@Getter
public class Carbon {

    @Getter
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

    public static void main(String[] args) {
        try {
            new Carbon(true);
        } catch (Exception exception) {
            Logger.errorLog(exception.getMessage(), "Initializing the application");
        }
    }
}
