package ee.taltech.iti0202.delivery.courier;

import ee.taltech.iti0202.delivery.location.Location;
import ee.taltech.iti0202.delivery.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Courier {
    private Strategy strategy;
    private List<String> currentPackages; // packages that the courier has right now
    private String name; // courier name
    private Location location; // current location
    private Location target; // target location
    private int distanceToTarget; // distance left to move


    public Courier(String name, Location startingLocation) {
        this.name = name;
        location = startingLocation;
        currentPackages = new ArrayList<>();
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
     * Target location setter.
     * @param location - target location.
     */
    public void setTarget(Location location) {
        location.getDistanceTo(location.getName());
    }

    /**
     * Return currentPackages.
     * @return currentPackages.
     */
    public List<String> currentPackages() {
        return currentPackages;
    }

    /**
     * Move to the target.
     */
    public void move() {
        if (distanceToTarget > 0) {
            distanceToTarget -= 20;
        }

        if (distanceToTarget <= 0) {
            location = target;
        }
    }


}
