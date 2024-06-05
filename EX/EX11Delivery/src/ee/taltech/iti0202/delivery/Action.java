package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private Location targetLocation;
    private List<String> packagesToTake;
    private List<String> packagesToDeposit;

    /**
     * Action constructor.
     * @param targetLocation -
     */
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
