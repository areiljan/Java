package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {
    public static final int ORB_LIMIT = 25;
    public static final int LIQUID_SILVER_DEMAND = 40;
    public static final int AMOUNT = 15;
    private int orbLimit;
    private int liquidSilverDemand;
    private int starEssenceDemand;
    private int fixCount;

    /**
     * SpaceOven constructor.
     * Initializes the liquidSilverDemand, starEssenceDemand, fixCount and the orbLimit.
     * @param name
     * @param resourceStorage
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.liquidSilverDemand = LIQUID_SILVER_DEMAND;
        this.starEssenceDemand = 10;
        this.fixCount = 0;
        this.orbLimit = ORB_LIMIT;
    }

    /**
     * Returns whether the oven is fixable.
     * @return true if it is fixable. false if broken and unfixable.
     */
    public boolean isFixable() {
        return true;
    }

    /**
     * Fix the Oven.
     * The Oven can either be fixed with liquid silver or if that is missing,
     * with star essence.
     * @throws CannotFixException - if the Oven is not broken, fixed maximum number of times,
     * or resources are missing.
     */
    @Override
    public void fix() throws CannotFixException {
        if (!this.isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (fixCount > 5) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!resourceStorage.hasEnoughResource("liquid silver", liquidSilverDemand)) {
            if (!resourceStorage.hasEnoughResource("star essence", starEssenceDemand)) {
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            }
        } else {
            if (resourceStorage().hasEnoughResource("liquid silver", liquidSilverDemand)) {
                resourceStorage.takeResource("liquid silver", liquidSilverDemand);
                liquidSilverDemand += liquidSilverDemand;
            } else if (resourceStorage().hasEnoughResource("star essence", starEssenceDemand)) {
                resourceStorage.takeResource("star essence", liquidSilverDemand);
            }
            orbLimit += ORB_LIMIT;
            liquidSilverDemand += liquidSilverDemand;
            starEssenceDemand += starEssenceDemand;
            fixCount++;
        }
    }

    /**
     * FixCount getter.
     * @return fixCount.
     */
    @Override
    public int getTimesFixed() {
        return fixCount;
    }

    /**
     * Check the Oven for broken status.
     * The Oven is broken if the createdOrbs outnumber the orbLimit.
     * If the fixCount reaches 5, the Oven will always be unbroken.
     * @return true if the machine is broken.
     */
    @Override
    public boolean isBroken() {
        if (fixCount >= 5) {
            return false;
        }
        return createdOrbs >= orbLimit;
    }

    /**
     * Craft an Orb.
     * This Oven can craft SpaceOrbs if it has the resources and is not broken.
     * If the resources are missing or the machine is broken, it will craft regular Orbs.
     * @return Optional <Orb>.
     */
    @Override
    public Optional<Orb> craftOrb() {
        if (!this.isBroken() && resourceStorage.hasEnoughResource("meteorite stone", 1)
                && resourceStorage.hasEnoughResource("star fragment", AMOUNT)) {
            incrementOrbs();
            resourceStorage.takeResource("meteorite stone", 1);
            resourceStorage.takeResource("star fragment", AMOUNT);
            return Optional.of(new SpaceOrb(name));
        } else if (resourceStorage.hasEnoughResource("pearl", 1)
                && resourceStorage.hasEnoughResource("silver", 1)) {
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            return Optional.of(new Orb(name));
        } else {
            return Optional.empty();
        }
    }

}
