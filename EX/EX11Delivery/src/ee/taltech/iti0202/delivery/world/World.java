package ee.taltech.iti0202.delivery.world;

import ee.taltech.iti0202.delivery.action.Action;
import ee.taltech.iti0202.delivery.courier.Courier;
import ee.taltech.iti0202.delivery.location.Location;
import ee.taltech.iti0202.delivery.strategy.Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class World {
    private Map<String, Location> locationMap = new HashMap<>();
    private Map<String, Courier> courierMap = new HashMap<>();
    public World() {
        
    }

    /**
     * Add a location with the given parameters.
     * @param name - name of the location.
     * @param otherLocations - other locations.
     * @param distances - distances to the locations.
     * @return - location if it exists.
     */
    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        // making sure that the location is eligible to add.
        // The location already exists.
        if (locationMap.containsKey(name)) {
            return Optional.empty();
        } else {
            // if the otherLocations does not contain all locations in the locationMap.
            for (String locationName : locationMap.keySet()) {
                if (!otherLocations.contains(locationName)) {
                    return Optional.empty();
                }
            }
            // if all given locations do not have a distance linked to them.
            if (distances.size() < locationMap.size()) {
                return Optional.empty();
            }
        }
        // otherwise add a location.
        Location locationToAdd = new Location(name);
        for (int i = 0; i < otherLocations.size(); i++) {
            locationToAdd.addDistance(otherLocations.get(i), distances.get(i));
        }
        return Optional.of(locationToAdd);
    }

    /**
     * Add a Courier to the world.
     * @param name - name of the courier.
     * @param to - location of the courier.
     * @return - optional of courier.
     */
    public Optional<Courier> addCourier(String name, String to) {
        if (courierMap.containsValue(name) || !locationMap.containsKey(to)) {
            return Optional.empty();
        } else {
            Courier newCourier = new Courier(name, locationMap.get(to));
            courierMap.put(name, newCourier);
            return Optional.of(newCourier);
        }
    }

    /**
     * Give a strategy to a Courier.
     * @param name - name of the courier.
     * @param strategy - strategy to give to the courier.
     * @return - true if the strategy was given.
     */
    public boolean giveStrategy(String name, Strategy strategy) {
        if (courierMap.containsKey(name)) {
            return false;
        }
        courierMap.get(name).setStrategy(strategy);
        return true;
    }

    public void tick() {
        for (Courier courier : courierMap.values()) {
            if (courier.getLocation().isPresent()) {
                // courier at location
                Action action = courier.getStrategy().getAction();
                // deposit
                for (Packet packet : courier.get)
                // take

                // setTarget
                courier.setTarget();
            } else {
                courier.move();
            }
        }
    }
}
