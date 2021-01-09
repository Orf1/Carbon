package dev.orf1.carbon;

import dev.orf1.carbon.listener.IListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {

    private final Set<IListener> listeners = new HashSet<>();
    private final int port;
    private boolean receivingConnections = false;
    private boolean active = false;
    private ServerSocket serverSocket;
    private Thread server;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        receivingConnections = true;
        serverSocket = new ServerSocket(port);
        server = new Thread(() -> {
            while (receivingConnections) {
                active = true;
                try {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    String raw = input.readUTF();
                    String data = raw.split("<:>")[0];
                    String message = raw.split("<:>")[1];
                    for (IListener listener : listeners) {
                        listener.onConnection(data, message, output);
                    }
                    input.close();
                    output.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                active = false;
            }
        });
    }

    public void stop() throws IOException, InterruptedException {
        receivingConnections = false;
        if (active) {
            Thread.sleep(1000);
            if (active) {
                server.stop();
            }
        }
        serverSocket.close();
    }

    public void terminate() throws IOException, InterruptedException {
        listeners.clear();
        stop();
    }

    public void registerListener(IListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(IListener listener) {
        listeners.remove(listener);
    }
}
