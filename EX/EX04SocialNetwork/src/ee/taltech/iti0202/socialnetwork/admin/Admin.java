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
        if(!this.equals(user)) {
            socialNetwork.banUser(user);
        }
    }

    @Override
    public String getName() {
        return "(Admin) " + name;
    }
}
