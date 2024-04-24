package ee.taltech.iti0202.delivery.courier;

import ee.taltech.iti0202.delivery.location.Location;
import ee.taltech.iti0202.delivery.strategy.Strategy;

import java.util.Optional;

public class Courier {
    private Strategy strategy;
    private Location location; // current location
    private Location target; // target location
    private int distanceToTarget; // distance left to move

    public Courier() {
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public void setTarget(Location location) {
        location.getDistanceTo(location.getName());
    }

    public void move() {
        // move
    }


}
