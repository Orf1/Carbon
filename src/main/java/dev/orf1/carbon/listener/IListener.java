package dev.orf1.carbon.listener;

import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

public interface IListener {

    void onConnection(String data, String message, ObjectOutputStream output) throws UnknownHostException;

}
