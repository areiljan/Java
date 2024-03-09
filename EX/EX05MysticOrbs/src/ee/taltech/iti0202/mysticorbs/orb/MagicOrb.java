package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {
    public MagicOrb(String creator) {
        super(creator);
    }

    @Override
    public void charge(String resource, int amount) {
        if(!resource.toLowerCase().equals("dust") || !resource.trim().isEmpty()) {
            setCurrentCharge(getCurrentCharge() + amount * resource.length() * 2);
        }
    }

    @Override
    public String toString() {
        return "MagicOrb by " + getName();
    }
}
