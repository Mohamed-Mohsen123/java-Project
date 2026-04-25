package src;

public class Admin extends User {
    private static final String ADMIN_TAG = "[ADMIN]";

    public Admin(String name) {
        super(name);
    }

    // Admin override: prefixes the content with [ADMIN]
    public Message createMessage(User receiver, String content) {
        return new TextMessage(this, receiver, ADMIN_TAG + " " + content);
    }

    @Override
    public String toString() {
        return getName() + " (Admin)";
    }
}
