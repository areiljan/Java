    package ee.taltech.iti0202.socialnetwork;

    import ee.taltech.iti0202.socialnetwork.feed.Feed;
    import ee.taltech.iti0202.socialnetwork.group.Group;
    import ee.taltech.iti0202.socialnetwork.user.User;

    import java.util.HashSet;
    import java.util.Set;

    public class SocialNetwork {
        private Set<User> bannedUsers = new HashSet<>();
        private Set<Group> groups = new HashSet<>();

        public void registerGroup(Group group) {
            groups.add(group);
        }

        public Set<Group> getGroups() {
            return groups;
        }

        public Feed getFeedForUser(User user) {
            return new Feed(user, user.getMessages());
        }
        
        public void banUser(User user) {
            bannedUsers.add(user);
        }
    }