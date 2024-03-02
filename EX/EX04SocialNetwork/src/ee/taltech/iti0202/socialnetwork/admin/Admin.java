package ee.taltech.iti0202.socialnetwork.admin;

import ee.taltech.iti0202.socialnetwork.SocialNetwork;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.user.User;

public class Admin extends User {
    public Admin(String name) {
        super(name);
    }
    public Admin(String name, Integer age) {
        super(name, age);
    }
    public void banUserFromSocialNetwork(User user, SocialNetwork socialNetwork) {
        for (Group group : socialNetwork.getGroups()) {
            if (group.getParticipants().contains(user)) {
                group.removeUser(user);
            }
        }
        socialNetwork.banUser(user);
    }
    public String getName() {
        return name;
    }
}
