package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

import static ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException.Reason.IS_NOT_BROKEN;

public class SpaceOven extends Oven implements Fixable {
    private int orbLimit;
    private int liquidSilverDemand;
    private int starEssenceDemand;
    private int fixCount;

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.liquidSilverDemand = 40;
        this.starEssenceDemand = 10;
        this.fixCount = 0;
        this.orbLimit = 25;
    }

    /**
     * Returns whether the oven is fixable.
     * @return true if it is fixable. false if broken and unfixable.
     */
    public boolean isFixable() {
        return true;
    }

    @Override
    public void fix() throws CannotFixException {
        if(!this.isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (fixCount > 5) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!resourceStorage.hasEnoughResource("liquid silver", liquidSilverDemand)) {
            if (!resourceStorage.hasEnoughResource("star essence", starEssenceDemand)) {
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            }
        } else {
            if(resourceStorage().hasEnoughResource("liquid silver", liquidSilverDemand)) {
                resourceStorage.takeResource("liquid silver", liquidSilverDemand);
                liquidSilverDemand += liquidSilverDemand;
            } else if (resourceStorage().hasEnoughResource("star essence", starEssenceDemand)){
                resourceStorage.takeResource("star essence", liquidSilverDemand);
            }
            orbLimit += 25;
            liquidSilverDemand += liquidSilverDemand;
            starEssenceDemand += starEssenceDemand;
            fixCount++;
        }
    }

    @Override
    public int getTimesFixed() {
        return fixCount;
    }

    @Override
    public boolean isBroken() {
        if(fixCount >= 5) {
            return false;
        }
        return createdOrbs >= orbLimit;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (!this.isBroken() && resourceStorage.hasEnoughResource("meteorite stone", 1) && resourceStorage.hasEnoughResource("star fragment", 15)) {
            incrementOrbs();
            resourceStorage.takeResource("meteorite stone", 1);
            resourceStorage.takeResource("star fragment", 15);
            return Optional.of(new SpaceOrb(name));
        } else if (resourceStorage.hasEnoughResource("pearl", 1) && resourceStorage.hasEnoughResource("silver", 1)) {
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            return Optional.of(new Orb(name));
        } else {
            return Optional.empty();
        }
    }

}
