package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {
    private String name;
    private User owner;
    private ArrayList<User> participants = new ArrayList<>();
    private Set<User> bannedUsers = new HashSet<>();
    private ArrayList<Message> messages = new ArrayList<>();

    /**
     * Constructs a new Group object with the given name and owner.
     *
     * @param name  The name of the group.
     * @param owner The owner of the group.
     */
    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        participants.add(owner);
        owner.addGroup(this);
    }

    /**
     * Gets the name of the group.
     *
     * @return The name of the group.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the group.
     *
     * @param name The new name of the group.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the owner of the group.
     *
     * @return The owner of the group.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the group.
     *
     * @param user The new owner of the group.
     */
    public void setOwner(User user) {
        if (participants.contains(user)){
            this.owner = user;
        }
        if (!participants.contains(user)) {
            user.addGroup(this);
        }
    }

    /**
     * Adds a user to the group.
     *
     * @param user The user to add to the group.
     */
    public void addUser(User user) {
        if (!participants.contains(user) && !bannedUsers.contains(user)) {
            participants.add(user);
            user.addGroup(this);
        }
        if (participants.size() == 1) {
            setOwner(participants.get(0));
        }
    }

    /**
     * Gets the list of participants in the group.
     *
     * @return The list of participants in the group.
     */
    public List<User> getParticipants() {
        return participants;
    }

    /**
     * Publishes a message in the group.
     *
     * @param message The message to publish in the group.
     */
    public void publishMessage(Message message) {
        if (participants.contains(message.getAuthor())) {
            messages.add(message);
        }
    }

    /**
     * Gets the list of messages in the group.
     *
     * @return The list of messages in the group.
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Removes a user from the group.
     *
     * @param user The user to remove from the group.
     */
    public void removeUser(User user) {
        // Eradicate all traces of the user
        // Get rid of the messages
        for (Message message : user.getMessages()) {
            if (message.getAuthor().equals(user)) {
                messages.remove(message);
            }
        }
        // Make the owner null first (if the removable user is the owner).
        if (owner == user) {
            this.owner = null;
        }
        participants.remove(user);
        user.removeGroup(this);
        // If there are viable candidates in the group and the owner is null, assign new owner.
        if (!participants.isEmpty() && owner == null) {
            setOwner(participants.get(0));
        }
    }

    /**
     * Bans a user from the group.
     *
     * @param user The user to ban from the group.
     */
    public void banUser(User user) {
        bannedUsers.add(user);
        if (participants.contains(user)) {
            removeUser(user);
        }
    }

    /**
     * Checks if the group is empty.
     *
     * @return True if the group is empty, false otherwise.
     */
    public boolean isGroupEmpty() {
        return participants.isEmpty();
    }

    /**
     * Gets the set of users banned from the group.
     *
     * @return The set of users banned from the group.
     */
    public Set<User> getBannedUsers() {
        return bannedUsers;
    }
}
