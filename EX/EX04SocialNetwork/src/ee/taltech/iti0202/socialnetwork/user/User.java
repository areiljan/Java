package ee.taltech.iti0202.socialnetwork.user;

import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String name;
    private final Integer age;
    private Set<Group> groups = new HashSet<>();

    /**
     * Constructs a new User object with the given name and no age.
     *
     * @param name The name of the user.
     */
    public User(String name) {
        this.name = name;
        this.age = null;
    }

    /**
     * Constructs a new User object with the given name and age.
     *
     * @param name The name of the user.
     * @param age  The age of the user.
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Adds a group to the user's groups.
     *
     * @param group The group to add.
     */
    public void addGroup(Group group) {
        groups.add(group);
    }

    /**
     * Removes a group from the user's groups.
     *
     * @param group The group to remove.
     */
    public void removeGroup(Group group) {
        groups.remove(group);
    }

    /**
     * Gets the groups that the user belongs to.
     *
     * @return The set of groups that the user belongs to.
     */
    public Set<Group> getGroups() {
        return groups;
    }

    /**
     * Gets the messages from all groups that the user belongs to.
     *
     * @return The set of messages from all groups that the user belongs to.
     */
    public Set<Message> getMessages() {
        Set<Message> messagesInUsersGroups = new HashSet<>();
        for (Group group : groups) {
            for (Message message : group.getMessages()) {
                messagesInUsersGroups.add(message);
            }
        }
        return messagesInUsersGroups;
    }
}
