package ee.taltech.iti0202.delivery;

import java.util.List;
import java.util.stream.Collectors;

public class ClosestLocationStrategy implements Strategy {
    private final Courier courier;
    private final World world;
    private List<Action> actions;

    /**
     * Get the closest location.
     * @param courier - courier.
     * @param world - world.
     */
    public ClosestLocationStrategy(Courier courier, World world) {
        this.courier = courier;
        this.world = world;
    }

    /**
     * Get action to go to the closest location available.
     * @return - action.
     */
    @Override
    public Action getAction() {
        Location currentLocation = courier.getLocation().get();
        String locationName = currentLocation.getFarthestOrClosestDistance(true);

        Location newLocation = world.getLocation(locationName);
        Action newAction = new Action(newLocation);
        for (Packet packet : courier.getCurrentPackages().values()) {
            if (packet.getTarget().equals(currentLocation)) {
                newAction.addDeposit(packet.getName());
            }
        }
        // Only take packets if they have the right target.
        List<Packet> filteredPacketsToTake = currentLocation.getPackets()
                .stream()
                .filter(packet -> packet.getTarget().equals(newLocation))
                .collect(Collectors.toList());
        for (Packet packet : filteredPacketsToTake) {
            newAction.addTake(packet.getName());
        }
        return newAction;
    }
}
