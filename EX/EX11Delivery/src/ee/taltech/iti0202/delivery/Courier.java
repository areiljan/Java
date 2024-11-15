package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Courier {
    private Strategy strategy;
    private HashMap<String, Packet> currentPackages; // packages that the courier has right now
    private String name; // courier name
    private Location location; // current location

    private Location target; // target location
    private int distanceToTarget; // distance left to move
    private Action currentAction; // current Action

    /**
     * Courier constructor.
     * @param name - name of the courier.
     * @param startingLocation - where does the courier spawn.
     */
    public Courier(String name, Location startingLocation) {
        this.name = name;
        location = startingLocation;
        currentPackages = new HashMap<>();
    }

    /**
     * Target getter.
     * @return - target.
     */
    public Location getTarget() {
        return target;
    }

    /**
     * Name getter.
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * Current action getter.
     * @return - current action.
     */
    public Action getCurrentAction() {
        return currentAction;
    }

    /**
     * Current action setter.
     * @param currentAction - the action to set as current action.
     */
    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
        if (currentAction != null) {
            target = currentAction.getGoTo();
            if (!target.equals(location)) {
                distanceToTarget = location.getDistanceTo(target.getName());
            }
        }
    }

    /**
     * How will the object look like souted.
     * @return - string.
     */
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

    /**
     * Get packages on hand.
     * @return - hashmap of packages.
     */
    public HashMap<String, Packet> getCurrentPackages() {
        return currentPackages;
    }


    /**
     * Drop a packet at the location.
     * @param packetName - packet with this name to drop.
     */
    public void depositPackage(String packetName) {
        Packet depositPackage = currentPackages.get(packetName);
        location.addPacket(depositPackage);
        currentPackages.remove(depositPackage);
    }

    /**
     * Take a packet from the location.
     * @param packetName - packet to take.
     */
    public void takePackage(String packetName) {
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
            distanceToTarget--;
        }

        if (distanceToTarget <= 0) {
            location = target;
            currentAction = null;
        }
    }

    /**
     * Make the world do stuff for one amount of time.
     */
    public void tick() {
        // If courier has a location and a current action
        if (getLocation().isPresent()) {
            // If the courier has no current action or is at the target location, get a new action
            if (currentAction == null) {
                setCurrentAction(getStrategy().getAction());
                distanceToTarget = location.getDistanceTo(target.getName());
            }

            // Deposit packets
            List<String> depositPackets = currentAction.getDeposit();
            for (String packetName : depositPackets) {
                depositPackage(packetName);
            }

            // Take packets
            List<String> takePackets = getCurrentAction().getTake();
            for (String packetName : takePackets) {
                takePackage(packetName);
            }
            location = null;
        }

        // Move the courier
        move();
    }
}
