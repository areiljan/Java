package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class World {
    private Map<String, Location> locationMap = new HashMap<>();
    private Map<String, Courier> courierMap = new HashMap<>();

    /**
     * World constructor.
     */
    public World() {
        // nil.
    }

    /**
     * Get a location from the string.
     *
     * @return Location.
     */
    public Location getLocation(String locationName) {
        return locationMap.get(locationName);
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
        if (locationMap.containsKey(name)
                || otherLocations.size() < locationMap.size()
                || distances.size() < locationMap.size()) {
            return Optional.empty();
        }

        // otherwise add a location.
        Location locationToAdd = new Location(name);
        for (int i = 0; i < locationMap.size(); i++) {
            String otherLocationName = otherLocations.get(i);

            if (!locationMap.containsKey(otherLocationName)) {
                return Optional.empty();
            }
            // if the locationMap has the specific location to add.
            if (locationMap.containsKey(otherLocationName)) {
                locationToAdd.addDistance(otherLocationName, distances.get(i));
                // add these distances to all locations
                locationMap.get(otherLocationName).addDistance(name, distances.get(i));
            }
        }

        locationMap.put(locationToAdd.getName(), locationToAdd);
        return Optional.of(locationToAdd);
    }

    /**
     * Add a Courier to the world.
     * @param name - name of the courier.
     * @param to - location of the courier.
     * @return - optional of courier.
     */
    public Optional<Courier> addCourier(String name, String to) {
        if (courierMap.containsKey(name) || !locationMap.containsKey(to)) {
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
        if (!courierMap.containsKey(name)) {
            return false;
        }
        courierMap.get(name).setStrategy(strategy);
        return true;
    }

    /**
     * Tick the world by one day.
     */
    public void tick() {
        for (Courier courier : courierMap.values()) {
            courier.tick();
        }
    }
}
