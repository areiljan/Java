    package ee.taltech.iti0202.socialnetwork;

    import ee.taltech.iti0202.socialnetwork.feed.Feed;
    import ee.taltech.iti0202.socialnetwork.group.Group;
    import ee.taltech.iti0202.socialnetwork.user.User;

    import java.util.HashSet;
    import java.util.Iterator;
    import java.util.List;
    import java.util.Set;

    public class SocialNetwork {
        private Set<Group> groups = new HashSet<>();

        /**
         * Registers a group in the social network.
         *
         * @param group The group to register.
         */
        public void registerGroup(Group group) {
            groups.add(group);
        }

        /**
         * Gets the set of all groups in the social network.
         *
         * @return The set of all groups in the social network.
         */
        public Set<Group> getGroups() {
            return groups;
        }

        /**
         * Gets the feed for a specific user in the social network.
         *
         * @param user The user for whom to get the feed.
         * @return The feed for the specified user.
         */
        public Feed getFeedForUser(User user) {
            return new Feed(user, user.getMessages());
        }

        /**
         * Bans a user from the social network.
         *
         * @param user The user to ban.
         */
        public void banUser(User user) {
            for (Group group : groups) {
                group.banUser(user);
                user.removeGroup(group);
            }
        }
    }
