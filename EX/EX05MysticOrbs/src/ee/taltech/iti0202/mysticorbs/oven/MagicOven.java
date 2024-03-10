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
        return fixCount <= 10;
    }

    @Override
    public void fix() throws CannotFixException {
        if(!this.isBroken()) {
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

    @Override
    public int getTimesFixed() {
        return fixCount;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if(!this.isBroken() && resourceStorage.hasEnoughResource("gold", 1) && resourceStorage.hasEnoughResource("dust", 3)) {
            incrementOrbs();
            orbCounter++;
            resourceStorage.takeResource("gold", 1);
            resourceStorage.takeResource("dust", 3);
            if(orbCounter % 2 == 0) {
                return Optional.of(new MagicOrb(name));
            } else {
                return Optional.of(new Orb(name));
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean isBroken() {
        return createdOrbs >= orbLimit;
    }
}
