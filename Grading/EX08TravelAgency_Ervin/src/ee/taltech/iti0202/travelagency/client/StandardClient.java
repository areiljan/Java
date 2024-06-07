package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.packages.TravelPackage;

public class StandardClient extends Client {
    private boolean readyForUpgrade = false;

    /**
     * @param id Id
     * @param name Name
     * @param email Email
     * @param age Age
     * @param budget Budget
     */
    public StandardClient(int id, String name, String email, int age, double budget) {
        super(id, name, email, age, budget);
    }

    @Override
    public boolean purchasePackage(TravelPackage travelPackage) {
        if (travelPackage.getPrice() <= this.budget) {
            this.budget -= travelPackage.getPrice();
            addPackage(travelPackage);
            checkAndUpgradeMembership();
            return true;
        }
        return false;
    }

    private void checkAndUpgradeMembership() {
        if (this.getPurchasedPackages().size() >= 3) {
            this.setReadyForUpgrade(true);
        }
    }

    public void setReadyForUpgrade(boolean readyForUpgrade) {
        this.readyForUpgrade = readyForUpgrade;
    }


    public boolean isReadyForUpgrade() {
        return readyForUpgrade;
    }

    @Override
    public double calculateDiscount(TravelPackage travelPackage) {
        return travelPackage.getPrice();
    }
}
