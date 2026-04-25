package src;

import java.util.ArrayList;
import java.util.List;

public class ChatSystem {
    private List<User> users;

    public ChatSystem() {
        this.users = new ArrayList<>();
    }

    public boolean createUser(String name, boolean isAdmin) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) return false;
        }
        users.add(isAdmin ? new Admin(name) : new User(name));
        return true;
    }

    public User findUser(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) return u;
        }
        return null;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void sendMessage(User sender, User receiver, String content) {
        Message msg = new TextMessage(sender, receiver, content);
        receiver.receiveMessage(msg);
    }

    public void broadcastMessage(Admin admin, String content) {
        for (User u : users) {
            if (!u.equals(admin)) {
                Message msg = new BroadcastMessage(admin, u, content);
                u.receiveMessage(msg);
            }
        }
    }

    public boolean deleteMessage(User user, int index) {
        return user.deleteMessage(index);
    }

    public boolean editMessage(User user, int index, String newContent) {
        Message msg = user.getMessageAt(index);
        if (msg != null) {
            msg.setContent("[edited] " + newContent);
            return true;
        }
        return false;
    }
}
