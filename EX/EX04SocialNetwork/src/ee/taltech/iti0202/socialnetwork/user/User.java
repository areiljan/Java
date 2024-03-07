package ee.taltech.iti0202.socialnetwork.user;

import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.SocialNetwork;
import ee.taltech.iti0202.socialnetwork.message.Message;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final String name;
    private final Integer age;
    private Set<Group> groups = new HashSet<>();

    public User(String name) {
        this.name = name;
        this.age = null;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
    
    public void addGroup(Group group) {
        groups.add(group);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }
    
    public Set<Group> getGroups() {
        return groups;
    }

    public Set<Message> getMessages() {
        Set<Message> messagesInUsersGroups = new HashSet<>();
        for(Group group : groups) {
            for(Message message : group.getMessages()) {
                messagesInUsersGroups.add(message);
            }
        }
        return messagesInUsersGroups;
    }
}