package src;

public class TextMessage extends Message {
    private String content;

    public TextMessage(User sender, User receiver, String content) {
        super(sender, receiver);
        this.content = content;
    }

    @Override public String getContent() { return content; }
    @Override public void setContent(String newContent) { this.content = newContent; }
    @Override public String getType() { return "TEXT"; }
}
