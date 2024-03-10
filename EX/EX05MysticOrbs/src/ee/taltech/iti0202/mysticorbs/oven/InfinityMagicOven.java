package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class InfinityMagicOven extends MagicOven {
    private int orbCounter;

    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.orbCounter = 0;
    }

    /**
     * Returns whether the oven is fixable.
     * @return true if it is fixable.
     */
    public boolean isFixable() {
        return false;
    }

    @Override
    public boolean isBroken() {
        return false;
    }
}
