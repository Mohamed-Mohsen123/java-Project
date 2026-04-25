package src;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static ChatSystem system = new ChatSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║      Java OOP Chat System        ║");
        System.out.println("╚══════════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> createUser();
                case 2 -> sendMessage();
                case 3 -> viewMessages();
                case 4 -> deleteMessage();
                case 5 -> editMessage();
                case 6 -> broadcastMessage();
                case 7 -> { System.out.println("Goodbye!"); running = false; }
                default -> System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n─────────────────────────────────");
        System.out.println("  1. Create User");
        System.out.println("  2. Send Message");
        System.out.println("  3. View Messages");
        System.out.println("  4. Delete a Message");
        System.out.println("  5. Edit a Message");
        System.out.println("  6. Broadcast (Admin only)");
        System.out.println("  7. Exit");
        System.out.println("─────────────────────────────────");
    }

    private static void createUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Name cannot be empty."); return; }

        System.out.print("Is this user an Admin? (y/n): ");
        String ans = scanner.nextLine().trim().toLowerCase();
        boolean isAdmin = ans.equals("y") || ans.equals("yes");

        boolean created = system.createUser(name, isAdmin);
        if (created) {
            System.out.println("✓ User '" + name + (isAdmin ? " (Admin)" : "") + "' created.");
        } else {
            System.out.println("✗ A user with that name already exists.");
        }
    }

    private static void sendMessage() {
        List<User> users = system.getUsers();
        if (users.size() < 2) { System.out.println("Need at least 2 users."); return; }

        User sender = pickUser("Select SENDER");
        if (sender == null) return;

        User receiver = pickUser("Select RECEIVER");
        if (receiver == null) return;

        if (sender.equals(receiver)) { System.out.println("Can't message yourself."); return; }

        System.out.print("Message content: ");
        String content = scanner.nextLine().trim();
        if (content.isEmpty()) { System.out.println("Message cannot be empty."); return; }

        system.sendMessage(sender, receiver, content);
        System.out.println("✓ Message sent from " + sender.getName() + " to " + receiver.getName() + ".");
    }

    private static void viewMessages() {
        User user = pickUser("View inbox of");
        if (user == null) return;

        List<Message> inbox = user.getInbox();
        System.out.println("\n── " + user.getName() + "'s inbox ──");
        if (inbox.isEmpty()) {
            System.out.println("  (no messages)");
        } else {
            for (int i = 0; i < inbox.size(); i++) {
                System.out.println("  [" + i + "] " + inbox.get(i));
            }
        }
    }

    private static void deleteMessage() {
        User user = pickUser("Delete message from whose inbox?");
        if (user == null) return;

        List<Message> inbox = user.getInbox();
        if (inbox.isEmpty()) { System.out.println("Inbox is empty."); return; }

        for (int i = 0; i < inbox.size(); i++) {
            System.out.println("  [" + i + "] " + inbox.get(i));
        }

        int idx = readInt("Enter message index to delete: ");
        boolean deleted = system.deleteMessage(user, idx);
        System.out.println(deleted ? "✓ Message deleted." : "✗ Invalid index.");
    }

    private static void editMessage() {
        User user = pickUser("Edit message in whose inbox?");
        if (user == null) return;

        List<Message> inbox = user.getInbox();
        if (inbox.isEmpty()) { System.out.println("Inbox is empty."); return; }

        for (int i = 0; i < inbox.size(); i++) {
            System.out.println("  [" + i + "] " + inbox.get(i));
        }

        int idx = readInt("Enter message index to edit: ");
        System.out.print("New content: ");
        String newContent = scanner.nextLine().trim();
        boolean edited = system.editMessage(user, idx, newContent);
        System.out.println(edited ? "✓ Message edited." : "✗ Invalid index.");
    }

    private static void broadcastMessage() {
        List<User> users = system.getUsers();
        List<User> admins = users.stream().filter(u -> u instanceof Admin).toList();
        if (admins.isEmpty()) { System.out.println("No admin users exist."); return; }

        System.out.println("Select an Admin:");
        for (int i = 0; i < admins.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + admins.get(i));
        }
        int choice = readInt("Choose: ") - 1;
        if (choice < 0 || choice >= admins.size()) { System.out.println("Invalid."); return; }

        Admin admin = (Admin) admins.get(choice);
        System.out.print("Broadcast message: ");
        String content = scanner.nextLine().trim();
        if (content.isEmpty()) { System.out.println("Content cannot be empty."); return; }

        system.broadcastMessage(admin, content);
        System.out.println("✓ Broadcast sent to all users by " + admin.getName() + ".");
    }

    private static User pickUser(String prompt) {
        List<User> users = system.getUsers();
        if (users.isEmpty()) { System.out.println("No users yet. Create one first."); return null; }
        System.out.println(prompt + ":");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + users.get(i));
        }
        int choice = readInt("Choose: ") - 1;
        if (choice < 0 || choice >= users.size()) { System.out.println("Invalid selection."); return null; }
        return users.get(choice);
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        try {
            String line = scanner.nextLine().trim();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
