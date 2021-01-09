package dev.orf1.carbon;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Carbon implements IListener{

    public Carbon() throws IOException {
        Server server = new Server(42791);
        server.registerListener(new Carbon());
        server.start();
    }

    @Override
    public void onConnection(String data, String message, ObjectOutputStream output) {
        System.out.println("New connection!\nData: " + data + "\nMessage: " + message);
    }

    public static void main(String[] args) {
        try {
            new Carbon();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
