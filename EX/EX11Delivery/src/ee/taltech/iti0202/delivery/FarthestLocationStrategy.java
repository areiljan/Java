package ee.taltech.iti0202.delivery;

import java.util.List;
import java.util.stream.Collectors;

public class FarthestLocationStrategy implements Strategy {
    private final Courier courier;
    private final World world;
    private List<Action> actions;

    /**
     * Get the farthest location.
     * @param courier - courier.
     * @param world - world.
     */
    public FarthestLocationStrategy(Courier courier, World world) {
        this.courier = courier;
        this.world = world;
    }

    /**
     * Get action to go to the farthest location available.
     * @return - action.
     */
    @Override
    public Action getAction() {
        Location currentLocation = courier.getLocation().get();
        String locationName = currentLocation.getFarthestOrClosestDistance(false);

        Location newLocation = world.getLocation(locationName);
        Action newAction = new Action(newLocation);
        for (Packet packet : courier.getCurrentPackages().values()) {
            if (packet.getTarget().equals(currentLocation)) {
                newAction.addDeposit(packet.getName());
            }
        }
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
