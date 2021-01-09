package dev.orf1.carbon;

import dev.orf1.carbon.listener.IListener;
import dev.orf1.carbon.manager.UserManager;
import dev.orf1.carbon.util.Logger;
import lombok.Getter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Server {

    private final Set<IListener> listeners = new HashSet<>();

    private ServerSocket serverSocket;
    private UserManager userManager;
    private Thread server;

    private boolean receivingConnections = false;
    private boolean active = false;

    private final int port;


    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        this.receivingConnections = true;
        this.serverSocket = new ServerSocket(port);
        this.userManager = new UserManager();

        this.server = new Thread(() -> {
            while (receivingConnections) {
                this.active = true;
                try {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                    String raw = input.readUTF();
                    String data = raw.split("<:>")[0];
                    String message = raw.split("<:>")[1];

                    this.listeners.forEach(iListener -> iListener.onConnection(data, message, output));

                    input.close();
                    output.close();
                } catch (IOException exception) {
                    Logger.errorLog(exception.getMessage(), "Receiving connections");
                }
                this.active = false;
            }
        });
    }

    public void stop() throws IOException, InterruptedException {
        this. receivingConnections = false;
        if (this.active) {
            Thread.sleep(1000);
            if (this.active) {
                this.server.stop();
            }
        }
        this.serverSocket.close();
    }

    public void terminate() throws IOException, InterruptedException {
        this.listeners.clear();
        stop();
    }

    public void registerListener(IListener listener) {
        this.listeners.add(listener);
    }

    public void unregisterListener(IListener listener) {
        this.listeners.remove(listener);
    }
}
