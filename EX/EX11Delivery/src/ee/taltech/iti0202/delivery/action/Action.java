package ee.taltech.iti0202.delivery.action;

import ee.taltech.iti0202.delivery.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private Location targetLocation;
    private List<String> packagesToTake;
    private List<String> packagesToDeposit;
    public Action(Location targetLocation) {
        this.targetLocation = targetLocation;
        packagesToTake = new ArrayList<>();
        packagesToDeposit = new ArrayList<>();
    }

    /**
     * Deposits getter.
     * @return - List of deposits.
     */
    public List<String> getDeposit() {
        return packagesToDeposit;
    }

    /**
     * packagesToTake getter.
     * @param - list of packages to take.
     */
    public List<String> getTake() {
        return packagesToTake;
    }

    /**
     * Add packages to take from the location.
     * @param name - name of the package.
     */
    public void addTake(String name) {
        packagesToTake.add(name);
    }

    /**
     * Add packages to deposit.
     * @param name - name of the package.
     */
    public void addDeposit(String name) {
        packagesToDeposit.add(name);
    }

    /**
     * LocationToGo
     * @return targetLocation
     */
    public Location getGoTo() {
        return targetLocation;
    }
}
