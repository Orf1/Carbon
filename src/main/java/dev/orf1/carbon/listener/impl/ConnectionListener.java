package dev.orf1.carbon.listener.impl;

import dev.orf1.carbon.listener.IListener;
import dev.orf1.carbon.util.Logger;

import java.io.ObjectOutputStream;

public class ConnectionListener implements IListener {

    @Override
    public void onConnection(String data, String message, ObjectOutputStream output) {
        Logger.connectionLog(data, message);
    }
}
