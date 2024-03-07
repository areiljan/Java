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
    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        participants.add(owner);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }
    
    public void setOwner(User user) {
        this.owner = user;
    }

    public void addUser(User user) {
        if (!participants.contains(user) && !bannedUsers.contains(user)) {
            participants.add(user);
        }
        if(participants.size() == 1) {
            setOwner(participants.get(0));
        }
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void publishMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
    
    public void removeUser(User user) {
        participants.remove(user);
        if(!participants.isEmpty()) {
            setOwner(participants.get(0));
        }
    }
    
    public void banUser(User user) {
        bannedUsers.add(user);
    }
    
    public Set<User> getBannedUsers() {
        return bannedUsers;
    }
}