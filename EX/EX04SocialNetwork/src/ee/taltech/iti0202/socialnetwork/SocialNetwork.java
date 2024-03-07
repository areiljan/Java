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
            for(Group group : groups) {
                if (!group.isGroupEmpty()) {
                    List<User> participants = group.getParticipants();
                    Set<User> usersToRemove = new HashSet<>();

                    // Find users to remove
                    for (User userInGroup : participants) {
                        if (userInGroup.equals(user)) {
                            usersToRemove.add(userInGroup);
                        }
                    }

                    // Remove users
                    for (User userToRemove : usersToRemove) {
                        group.banUser(user);
                    }
                }
            }
        }
    }