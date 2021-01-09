package dev.orf1.carbon.manager;

import dev.orf1.carbon.message.MessageUser;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class UserManager {

    private final ArrayList<MessageUser> onlineClientUsers = new ArrayList<>();

    public void removeClientUser(MessageUser messageUser) {
        this.onlineClientUsers.remove(messageUser);
    }

    public void addClientUser(MessageUser messageUser) {
        if (!(this.onlineClientUsers.contains(messageUser))) {
            this.onlineClientUsers.add(messageUser);
        }
    }

    public boolean isOnline(String username) {
        return MessageUser.getByUsername(username) != null;
    }
}
