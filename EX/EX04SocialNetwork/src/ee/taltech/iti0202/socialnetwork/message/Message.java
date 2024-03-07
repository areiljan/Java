package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {
    private final String title;
    private final String message;
    private final User author;

    /**
     * Constructs a new Message object with the given title, message, and author.
     *
     * @param title   The title of the message.
     * @param message The content of the message.
     * @param author  The author of the message.
     */
    public Message(String title, String message, User author) {
        this.title = title;
        this.message = message;
        this.author = author;
    }

    /**
     * Gets the title of the message.
     *
     * @return The title of the message.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the content of the message.
     *
     * @return The content of the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the author of the message.
     *
     * @return The author of the message.
     */
    public User getAuthor() {
        return author;
    }
}
