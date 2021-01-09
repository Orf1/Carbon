package dev.orf1.carbon.message;

import java.net.InetAddress;

public class MessageUser {

    public String username;
    public InetAddress inetAddress;

    public MessageUser(String username, InetAddress inetAddress) {
        this.username = username;
        this.inetAddress = inetAddress;
    }
}
