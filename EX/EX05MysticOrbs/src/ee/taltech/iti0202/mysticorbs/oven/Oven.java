package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Oven implements Comparable<Oven> {
    protected final String name;
    protected final ResourceStorage resourceStorage;
    protected int orbLimit;
    protected Integer createdOrbs;
    protected Integer fixCount;

    /**
     * Construct the oven.
     * @param name - name of the oven.
     * @param resourceStorage - name of the ResourceStorage.
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.createdOrbs = 0;
        this.fixCount = 0;
        this.orbLimit = 15;
    }

    public ResourceStorage resourceStorage() {
        return resourceStorage;
    }

    /**
     * Return two ovens on a number of parameters.
     * The logic of comparison:
     * A broken oven is always weaker
     * SpaceOven > InfinityMagicOven > MagicOven > Oven
     * More Orbs > Less Orbs
     * Name lexicographic order
     * If names are same, then the ovens are equal.
     * @param o
     * @return 1 if this is bigger than o and backwards. 0 if equal.
     */
    public int compareTo(Oven o) {
        // A broken oven is always weaker
        if(this.isBroken() && !o.isBroken()) {
            return -1;
        } else if (!this.isBroken() && o.isBroken()) {
            return 1;
        }
        // SpaceOven > InfinityMagicOven > MagicOven > Oven
        if (this instanceof SpaceOven && !(o instanceof SpaceOven)) {
            return 1; // SpaceOven is greater
        } else if (!(this instanceof SpaceOven) && o instanceof SpaceOven) {
            return -1; // SpaceOven is greater
        } else if (this instanceof InfinityMagicOven && !(o instanceof InfinityMagicOven)) {
            return 1; // InfinityMagicOven is greater
        } else if (!(this instanceof InfinityMagicOven) && o instanceof InfinityMagicOven) {
            return -1; // InfinityMagicOven is greater
        } else if (this instanceof MagicOven && !(o instanceof MagicOven)) {
            return 1; // MagicOven is greater
        } else if (!(this instanceof MagicOven) && o instanceof MagicOven) {
            return -1; // MagicOven is greater
        }
        // More Orbs > Less Orbs
        int orbComparison = Integer.compare(o.getCreatedOrbsAmount(), this.getCreatedOrbsAmount());
        if (orbComparison != 0) {
            return orbComparison; // More orbs come before less orbs
        }
        // Name lexicographic order
        int nameComparison = this.getName().compareTo(o.getName());
        if (nameComparison != 0) {
            return nameComparison; // Name lexicographic order
        }
        // If names are same, then the ovens are equal
        return 0;
    }

    /**
     * Getter for name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the oven is fixable.
     * @return true if it is fixable.
     */
    public boolean isFixable() {
        return !this.isBroken();
    }

    /**
     * Getter for the ResourceStorage.
     *
     * @return resourceStorage
     */
    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    /**
     * Getter for the CreatedOrbsAmount.
     * @return Amount of created orbs.
     */
    public int getCreatedOrbsAmount() {
        return createdOrbs;
    }

    /**
     * Increment the created orbs by one.
     */
    public void incrementOrbs() {
        createdOrbs++;
    }

    /**
     * Is the oven broken.
     * @return true if broken.
     */
    public boolean isBroken() {
        return createdOrbs >= orbLimit;
    }

    /**
     * Create a regular orb.
     * Uses 1 pearl and 1 silver.
     * @return the Optional Orb.
     */
    public Optional<Orb> craftOrb() {
        if(!this.isBroken() && resourceStorage.hasEnoughResource("pearl", 1) && resourceStorage.hasEnoughResource("silver", 1)) {
            incrementOrbs();
            Orb createdOrb = new Orb(name);
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            return Optional.of(createdOrb);
        } else {
            return Optional.empty();
        }
    }

    public void fix() throws CannotFixException {
        // Do nothing.
    }
}
