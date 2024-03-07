package ee.taltech.iti0202.socialnetwork.admin;

import ee.taltech.iti0202.socialnetwork.SocialNetwork;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.user.User;

public class Admin extends User {
    /**
     * Constructs a new Admin object with the given name.
     *
     * @param name The name of the admin.
     */
    public Admin(String name) {
        super(name);
    }

    /**
     * Constructs a new Admin object with the given name and age.
     *
     * @param name The name of the admin.
     * @param age  The age of the admin.
     */
    public Admin(String name, Integer age) {
        super(name, age);
    }

    /**
     * Bans a user from the social network.
     *
     * @param user          The user to ban.
     * @param socialNetwork The social network from which to ban the user.
     */
    public void banUserFromSocialNetwork(User user, SocialNetwork socialNetwork) {
        if (!(user instanceof Admin)) {
            socialNetwork.banUser(user);
        }
    }

    /**
     * Gets the name of the admin.
     *
     * @return The name of the admin.
     */
    @Override
    public String getName() {
        return "(Admin) " + super.getName();
    }
}
