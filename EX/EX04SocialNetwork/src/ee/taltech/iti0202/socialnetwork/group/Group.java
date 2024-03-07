package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.SocialNetwork;
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
        owner.addGroup(this);
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
        if(participants.contains(user)){
            this.owner = user;
        }
        if(!participants.contains(user)) {
            user.addGroup(this);
        }
    }

    public void addUser(User user) {
        if (!participants.contains(user) && !bannedUsers.contains(user)) {
            participants.add(user);
            user.addGroup(this);
        }
        if(participants.size() == 1) {
            setOwner(participants.get(0));
        }
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void publishMessage(Message message) {
        if(participants.contains(message.getAuthor())) {
            messages.add(message);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }
    
    public void removeUser(User user) {
        // Eradicate all traces of the user
        // Get rid of the messages
        for(Message message : user.getMessages()) {
            if(message.getAuthor().equals(user)) {
                messages.remove(message);
            }
        }
        // Make the owner null first (if the removable user is the owner).
        if(owner == user) {
            this.owner = null;
        }
        participants.remove(user);
        // If there are viable candidates in the group and the owner is null, assign new owner.
        if(!participants.isEmpty() && owner == null) {
            setOwner(participants.get(0));
        }
    }
    
    public void banUser(User user) {
        bannedUsers.add(user);
        if(participants.contains(user)) {
            removeUser(user);
        }
    }

    public boolean isGroupEmpty() {
        return participants.isEmpty();
    }
    
    public Set<User> getBannedUsers() {
        return bannedUsers;
    }
}