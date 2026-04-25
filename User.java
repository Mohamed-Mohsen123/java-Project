package src;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Message> inbox;

    public User(String name) {
        this.name = name;
        this.inbox = new ArrayList<>();
    }

    public String getName() { return name; }

    public void receiveMessage(Message msg) {
        inbox.add(msg);
    }

    public List<Message> getInbox() {
        return new ArrayList<>(inbox); // defensive copy for viewing
    }

    // Returns actual reference for editing
    public Message getMessageAt(int index) {
        if (index >= 0 && index < inbox.size()) return inbox.get(index);
        return null;
    }

    public boolean deleteMessage(int index) {
        if (index >= 0 && index < inbox.size()) {
            inbox.remove(index);
            return true;
        }
        return false;
    }

    public int inboxSize() { return inbox.size(); }

    @Override
    public String toString() { return name; }
}
