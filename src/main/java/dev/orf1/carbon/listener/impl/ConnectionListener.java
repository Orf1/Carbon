package dev.orf1.carbon.listener.impl;

import dev.orf1.carbon.listener.IListener;
import dev.orf1.carbon.message.MessageUser;
import dev.orf1.carbon.message.MessageUserType;
import dev.orf1.carbon.util.Logger;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionListener implements IListener {

    @Override
    public void onConnection(String data, String message, ObjectOutputStream output) throws UnknownHostException {
        Logger.connectionLog(data, message);
        String[] dataArguments = data.split("%");
        if (MessageUser.getByUsername(dataArguments[5]) == null) {
            if (dataArguments[0].equalsIgnoreCase("CLIENT")) {
                new MessageUser(dataArguments[5], InetAddress.getByName(dataArguments[1]), MessageUserType.CLIENT);
            }
            if (dataArguments[0].equalsIgnoreCase("SERVER")) {
                new MessageUser(dataArguments[5], InetAddress.getByName(dataArguments[1]), MessageUserType.SERVER);
            }
        }
    }
}
