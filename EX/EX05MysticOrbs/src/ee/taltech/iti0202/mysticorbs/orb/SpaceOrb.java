package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {

    public SpaceOrb(String creator) {
        super(creator);
        setCurrentCharge(100);
    }

    @Override
    public void charge(String resource, int amount) {
        // Do nothing. This kind of Orb can not be charged.
    }

    @Override
    public String toString() {
        return "SpaceOrb by " + getName();
    }

    public boolean absorb(Orb orb) {
        if(getCurrentCharge() > orb.getCurrentCharge()) {
            this.setCurrentCharge(this.getCurrentCharge() + orb.getCurrentCharge());
            orb.setCurrentCharge(0);
            return true;
        }
        return false;
    }
}
