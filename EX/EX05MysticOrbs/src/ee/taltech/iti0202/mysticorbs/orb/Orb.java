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

    public Orb(String creator) {
        this.name = creator;
        this.currentCharge = 0;
        // Found no other way to do this.
        }

    public void charge(String resource, int amount) {
        if(!resource.equalsIgnoreCase("dust") && !resource.trim().isEmpty()) {
            currentCharge += amount * resource.length();
        }
    }

    public int getEnergy() {
        return currentCharge;
    }

    public String toString() {
        return "Orb by " + name;
    }
}
