package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {
    /**
     * Constructor.
     * Take the parameters from the superclass.
     * @param creator - who created the Orb.
     */
    public MagicOrb(String creator) {
        super(creator);
    }

    /**
     * Charge the orb by the multiplicator of the amount of resource entered.
     * @param resource
     * @param amount
     */
    @Override
    public void charge(String resource, int amount) {
        if (!resource.equalsIgnoreCase("dust") && !resource.trim().isEmpty() && amount > 0) {
            setCurrentCharge(getCurrentCharge() + amount * resource.length() * 2);
        }
    }

    /**
     * Write the orb as a String.
     * @return String.
     */
    @Override
    public String toString() {
        return "MagicOrb by " + getName();
    }
}
