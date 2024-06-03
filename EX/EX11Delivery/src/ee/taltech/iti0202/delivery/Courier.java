package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.Optional;

public class Courier {
    private Strategy strategy;
    private HashMap<String, Packet> currentPackages; // packages that the courier has right now
    private String name; // courier name
    private Location location; // current location

    public Location getTarget() {
        return target;
    }

    private Location target; // target location
    private int distanceToTarget; // distance left to move
    private Action currentAction; // current Action


    public String getName() {
        return name;
    }

    public Courier(String name, Location startingLocation) {
        this.name = name;
        location = startingLocation;
        currentPackages = new HashMap<>();
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
        if (currentAction != null) {
            target = currentAction.getGoTo();
            if (!target.equals(location)) {
                distanceToTarget = location.getDistanceTo(target.getName());
            }
        }
    }

    @Override
    public String toString() {
        return this.name + " PACKETS " + currentPackages.size();
    }

    /**
     * strategy getter.
     * @return - current strategy.
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * strategy setter.
     * @param strategy - new strategy.
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * location getter.
     * @return - current Location.
     */
    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public HashMap<String, Packet> getCurrentPackages() {
        return currentPackages;
    }


    /**
     * Drop a packet at the location.
     * @param packetName - packet with this name to drop.
     */
    public void depositPackage (String packetName) {
        Packet depositPackage = currentPackages.get(packetName);
        location.addPacket(depositPackage);
        currentPackages.remove(depositPackage);
    }

    /**
     * Take a packet from the location.
     * @param packetName - packet to take.
     */
    public void takePackage (String packetName) {
        Optional<Packet> optionalPacket = location.getPacket(packetName);
        if (optionalPacket.isPresent()) {
            Packet packet = optionalPacket.get();
            currentPackages.put(packetName, packet);
        }
    }

    /**
     * Move to the target.
     */
    public void move() {
        if (distanceToTarget > 0) {
            distanceToTarget -= 20;
            location = null;
        }

        if (distanceToTarget <= 0) {
            location = target;
        }

        System.out.println("distance left: " + distanceToTarget);
    }
}