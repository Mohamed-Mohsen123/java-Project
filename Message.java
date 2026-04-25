package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Message {
    private User sender;
    private User receiver;
    private LocalDateTime timestamp;

    public Message(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = LocalDateTime.now();
    }

    public User getSender()   { return sender; }
    public User getReceiver() { return receiver; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public abstract String getContent();
    public abstract void setContent(String newContent);
    public abstract String getType();

    @Override
    public String toString() {
        String time = timestamp.format(DateTimeFormatter.ofPattern("HH:mm"));
        return "[" + time + "] [" + getType() + "] From " + sender.getName() + ": " + getContent();
    }
}
