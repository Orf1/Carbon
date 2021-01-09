package dev.orf1.carbon.listener;

import java.io.ObjectOutputStream;

public interface IListener {
    void onConnection(String data, String message, ObjectOutputStream output);
}
