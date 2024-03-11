package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;

public class ResourceStorage {
    private HashMap<String, Integer> resourceAmounts = new HashMap<String, Integer>();
    public boolean isEmpty() {
        for(int value : resourceAmounts.values()) {
            if(value != 0) {
                return false;
            }
        }
        return true;
    }

    public void addResource(String resource, int amount) {
        if (!resource.trim().isEmpty() && amount > 0) {
            resource = resource.toLowerCase();
            int currentAmount = resourceAmounts.getOrDefault(resource, 0);
            resourceAmounts.put(resource, currentAmount + amount);
        }
    }

    public int getResourceAmount(String resource) {
        Integer amount = resourceAmounts.get(resource.toLowerCase());
        if (amount == null) {
            return 0;
        } else {
            return amount;
        }
    }

    public boolean hasEnoughResource(String resource, int amount) {
        return amount > 0 && getResourceAmount(resource) >= amount;
    }

    public boolean takeResource(String resource, int amount) {
        if(resourceAmounts.containsKey(resource) && hasEnoughResource(resource, amount)) {
            int currentvalue = resourceAmounts.get(resource);
            int newValue = currentvalue - amount;
            resourceAmounts.put(resource, newValue);
            return true;
        }
        return false;
    }
}
