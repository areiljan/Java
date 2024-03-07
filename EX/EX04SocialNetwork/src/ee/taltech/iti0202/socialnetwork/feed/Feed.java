package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.Set;

/**
 * Represents a feed containing messages for a specific user.
 */
public class Feed {
    private final User user;
    private final Set<Message> messages;

    /**
     * Constructs a new Feed object with the given user and set of messages.
     *
     * @param user     The user for whom the feed is created.
     * @param messages The set of messages in the feed.
     */
    public Feed(User user, Set<Message> messages) {
        this.user = user;
        this.messages = messages;
    }

    /**
     * Gets the user associated with this feed.
     *
     * @return The user associated with this feed.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the set of messages in this feed.
     *
     * @return The set of messages in this feed.
     */
    public Set<Message> getMessages() {
        return messages;
    }
}
