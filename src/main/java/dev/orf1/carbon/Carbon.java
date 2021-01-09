package dev.orf1.carbon;

import dev.orf1.carbon.listener.IListener;
import dev.orf1.carbon.listener.impl.ConnectionListener;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Carbon {

    public Carbon() throws IOException {
        Server server = new Server(42791);
        server.registerListener(new ConnectionListener());
        server.start();
    }

    public static void main(String[] args) {
        try {
            new Carbon();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
