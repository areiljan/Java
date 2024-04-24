package ee.taltech.iti0202.delivery.world;

import ee.taltech.iti0202.delivery.courier.Courier;
import ee.taltech.iti0202.delivery.location.Location;

import java.util.HashMap;
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
        if (locationMap.containsKey(name)) {
            return Optional.empty();
        }

    }

    public Optional<Courier> addCourier(String name, String to) {
        
    }

    public void giveStrategy(String name, Strategy strategy) {
        courierMap.get(name).setStrategy(strategy);
    }

    public void tick() {
        for (Courier courier : courierMap.values()) {
            if (courier.getLocation().isPresent()) {
                // courier at location
                Action action = courier.getStrategy().getAction();
                // deposit

                // take

                // setTarget
                courier.setTarget();
            }
        }
    }
}
