package ee.taltech.iti0202.delivery.strategy;

import ee.taltech.iti0202.delivery.action.Action;
import ee.taltech.iti0202.delivery.courier.Courier;
import ee.taltech.iti0202.delivery.location.Location;
import ee.taltech.iti0202.delivery.packet.Packet;
import ee.taltech.iti0202.delivery.world.World;

import java.util.List;
import java.util.stream.Collectors;

public class FarthestLocationStrategy implements Strategy {
    private final Courier courier;
    private final World world;
    private List<Action> actions;
    public FarthestLocationStrategy(Courier courier, World world) {
        this.courier = courier;
        this.world = world;
    }

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
