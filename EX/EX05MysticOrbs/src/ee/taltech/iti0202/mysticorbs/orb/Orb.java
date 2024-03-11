package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {
    private final String name;

    public int getCurrentCharge() {
        return currentCharge;
    }

    public void setCurrentCharge(int currentCharge) {
        this.currentCharge = currentCharge;
    }

    private int currentCharge;

    public String getName() {
        return name;
    }

    /**
     * Orb constructor.
     * Assigns the creator name and sets the current charge.
     * @param creator - oven name.
     */
    public Orb(String creator) {
        this.name = creator;
        this.currentCharge = 0;
    }

    /**
     * Charge the orb by the multiplication of the resource length and amount.
     * @param resource - resource that is charged with.
     * @param amount - amount of resource used.
     */
    public void charge(String resource, int amount) {
        if (!resource.equalsIgnoreCase("dust") && !resource.trim().isEmpty()
            && amount > 0) {
            currentCharge += amount * resource.length();
        }
    }

    /**
     * Get the current charge of the Orb.
     * @return currentCharge.
     */
    public int getEnergy() {
        return currentCharge;
    }

    /**
     * Write the orb as a String.
     * @return String.
     */
    public String toString() {
        return "Orb by " + name;
    }
}
