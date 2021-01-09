package dev.orf1.carbon.message;

import dev.orf1.carbon.Carbon;
import lombok.Getter;

import java.net.InetAddress;

@Getter
public class MessageUser {

    public String username;
    public InetAddress inetAddress;
    public MessageUserType userType;

    public MessageUser(String username, InetAddress inetAddress, MessageUserType userType) {
        this.username = username;
        this.inetAddress = inetAddress;
        this.userType = userType;

        Carbon.getInstance().getServer().getUserManager().addClientUser(this);
    }

    public void disconnectClient() {
        Carbon.getInstance().getServer().getUserManager().removeClientUser(this);
    }

    public static MessageUser getByUsername(String username) {
        return Carbon.getInstance().getServer().getUserManager().getOnlineClientUsers().stream().filter(messageUser -> username.equals(messageUser.getUsername())).findFirst().orElse(null);
    }
}
