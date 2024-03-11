package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;


public class InfinityMagicOven extends MagicOven {

    /**
     * InfinityMagicOven constructor.
     * @param name - name of the Oven.
     * @param resourceStorage - resourceStorage.
     */
    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    /**
     * Returns whether the Oven is fixable.
     * @return true if it is fixable. false if broken and unfixable.
     */
    public boolean isFixable() {
        return true;
    }

    /**
     * Returns whether the Oven is broken.
     * @return true if broken.
     */
    @Override
    public boolean isBroken() {
        return false;
    }
}
