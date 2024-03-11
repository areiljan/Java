package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;

public class ResourceStorage {
    private HashMap<String, Integer> resourceAmounts = new HashMap<String, Integer>();

    /**
     * Is the resourceStorage empty.
     * @return true if it is.
     */
    public boolean isEmpty() {
        for (int value : resourceAmounts.values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a resource.
     * If the amount is negative or the resource name is empty, do nothing.
     * @param resource - resource name
     * @param amount - amount of the specified resource to be added.
     */
    public void addResource(String resource, int amount) {
        if (!resource.trim().isEmpty() && amount > 0) {
            resource = resource.toLowerCase();
            int currentAmount = resourceAmounts.getOrDefault(resource, 0);
            resourceAmounts.put(resource, currentAmount + amount);
        }
    }

    /**
     * Get the resource amount.
     * @param resource - Which resource.
     * @return - amount as int.
     */
    public int getResourceAmount(String resource) {
        Integer amount = resourceAmounts.get(resource.toLowerCase());
        if (amount == null) {
            return 0;
        } else {
            return amount;
        }
    }

    /**
     * Does the Storage have the resource.
     * Check the storage for the specified resource amount.
     * @param resource - name.
     * @param amount - how much is needed.
     * @return - true if there is a supply.
     */
    public boolean hasEnoughResource(String resource, int amount) {
        return amount > 0 && getResourceAmount(resource) >= amount;
    }

    /**
     * Take the amount of resource.
     * @param resource - resource name.
     * @param amount - how much to be taken.
     * @return - true if successful.
     */
    public boolean takeResource(String resource, int amount) {
        if (resourceAmounts.containsKey(resource) && hasEnoughResource(resource, amount)) {
            int currentvalue = resourceAmounts.get(resource);
            int newValue = currentvalue - amount;
            resourceAmounts.put(resource, newValue);
            return true;
        }
        return false;
    }
}
