package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {
    private int orbLimit;
    private int freezingPowderDemand;
    private int fixCount;
    private int clayDemand;
    private int orbCounter;

    /**
     * MagicOrb constructor.
     * Initialize the orbCounter, clayDemand, freezingPowderDemand, fixCount and orbLimit.
     * @param creator - Oven name.
     * @param resourceStorage - resourceStorage.
     */
    public MagicOven(String creator, ResourceStorage resourceStorage) {
        super(creator, resourceStorage);
        this.orbCounter = 0;
        this.clayDemand = 25;
        this.freezingPowderDemand = 100;
        this.fixCount = 0;
        this.orbLimit = 5;
    }

    /**
     * Returns whether the oven is fixable.
     * @return true if it is fixable.
     */
    public boolean isFixable() {
        return fixCount < 10 && this.isBroken();
    }

    /**
     * Fix the oven.
     * Oven is fixable if the fixCount is not 10 and the
     * @throws CannotFixException
     */
    @Override
    public void fix() throws CannotFixException {
        if (!this.isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (fixCount == 10) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!resourceStorage.hasEnoughResource("freezing powder", freezingPowderDemand) || !resourceStorage.hasEnoughResource("clay", clayDemand)) {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        } else {
            resourceStorage.takeResource("freezing powder", freezingPowderDemand);
            resourceStorage.takeResource("clay", clayDemand);
            orbLimit += 5;
            fixCount++;
            freezingPowderDemand += freezingPowderDemand;
            clayDemand += clayDemand;
        }
    }

    /**
     * FixCount getter.
     * @return fixCount
     */
    @Override
    public int getTimesFixed() {
        return fixCount;
    }

    /**
     * Create an Orb.
     * A magicOven will create a magicOrb every second time if it has the necessary components.
     * @return
     */
    @Override
    public Optional<Orb> craftOrb() {
        if (!this.isBroken() && resourceStorage.hasEnoughResource("gold", 1) && resourceStorage.hasEnoughResource("dust", 3)) {
            incrementOrbs();
            orbCounter++;
            resourceStorage.takeResource("gold", 1);
            resourceStorage.takeResource("dust", 3);
            if (orbCounter % 2 == 0) {
                Orb createdOrb = new MagicOrb(name);
                createdOrb.charge("gold", 1);
                createdOrb.charge("dust", 3);
                return Optional.of(createdOrb);
            } else {
                Orb createdOrb = new Orb(name);
                createdOrb.charge("gold", 1);
                createdOrb.charge("dust", 3);
                return Optional.of(createdOrb);
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Check the Oven for broken status.
     * The Oven is broken if the createdOrbs outnumber the orbLimit.
     * @return true if the machine is broken.
     */
    @Override
    public boolean isBroken() {
        return createdOrbs >= orbLimit;
    }
}
