package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {

    /**
     * SpaceOrb constructor.
     * The currentCharge is initialized as 100.
     * @param creator - Oven that created the Orb.
     */
    public SpaceOrb(String creator) {
        super(creator);
        setCurrentCharge(100);
    }

    /**
     * Charge the Orb.
     * Does nothing.
     * @param resource - resource that is charged with.
     * @param amount - amount of resource used.
     */
    @Override
    public void charge(String resource, int amount) {
        // Do nothing. This kind of Orb can not be charged.
    }

    /**
     * Write the Orb as String.
     * @return String.
     */
    @Override
    public String toString() {
        return "SpaceOrb by " + getName();
    }

    /**
     * Absorb another Orb.
     * If the Orb has more charge than the Orb specified in the parameter, steal its charge.
     * @param orb - Orb to swallow.
     * @return - true if the absorbtion commenced as planned.
     */
    public boolean absorb(Orb orb) {
        if (getCurrentCharge() > orb.getCurrentCharge()) {
            this.setCurrentCharge(this.getCurrentCharge() + orb.getCurrentCharge());
            orb.setCurrentCharge(0);
            return true;
        }
        return false;
    }
}
