package ee.taltech.iti0202.delivery;

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
            String otherLocationName = otherLocations.get(i);

            if (i <= distances.size()) {
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
            // If the courier has no current action or is at the target location, get a new action
            if (courier.getCurrentAction() == null || courier.getLocation().equals(courier.getTarget())) {
                Action action = courier.getStrategy().getAction();
                if (action != null) {
                    courier.setCurrentAction(action);
                }
            }

            // If courier has a location and a current action
            Location location = courier.getLocation().get();
            if (courier.getCurrentAction() != null && location != null) {
                // Deposit packets
                List<String> depositPackets = courier.getCurrentAction().getDeposit();
                if (depositPackets != null) {
                    for (String packetName : depositPackets) {
                        courier.depositPackage(packetName);
                    }
                }

                // Take packets
                List<String> takePackets = courier.getCurrentAction().getTake();
                if (takePackets != null) {
                    for (String packetName : takePackets) {
                        boolean packetFound = location.getPackets().stream()
                                .filter(packet -> packet != null)  // Filter out null packets
                                .peek(packet -> System.out.println("Checking packet: " + packet.getName()))  // Debugging output
                                .anyMatch(packet -> packet.getName().equals(packetName));  // Match packet name

                        if (packetFound) {
                            courier.takePackage(packetName);
                            System.out.println("Packet " + packetName + " taken.");
                        } else {
                            System.out.println("Packets in location " + location.getPackets() + ". Packet " + packetName + " not found at the location.");
                        }
                    }
                }
            }

            // Move the courier
            courier.move();
        }
    }
}
