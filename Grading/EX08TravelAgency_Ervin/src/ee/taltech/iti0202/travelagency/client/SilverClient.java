package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.packages.TravelPackage;

import java.util.List;

public class SilverClient extends Client {
    private boolean readyForUpgrade = false;

    private static final double DISCOUNT_RATE = 0.05; // 5% discount

    /**
     * @param id Id
     * @param name Name
     * @param email Email
     * @param age Age
     * @param budget Budget
     */
    public SilverClient(int id, String name, String email, int age, double budget) {
        super(id, name, email, age, budget);
    }

    /**
     * @param travelPackage TravelPackage being bought.
     * @return Boolean.
     */
    @Override
    public boolean purchasePackage(TravelPackage travelPackage) {
        double discountedPrice = calculateDiscount(travelPackage);
        if (discountedPrice <= this.budget) {
            this.budget -= discountedPrice;
            addPackage(travelPackage);
            checkAndUpgradeMembership();
            return true;
        }
        return false;
    }

    private void checkAndUpgradeMembership() {
        if (this.getPurchasedPackages().size() >= 5) {
            this.setReadyForUpgrade(true);
        }
    }


    /**
     * @param readyForUpgrade Boolean representing if a client is ready for a membership upgrade.
     */
    public void setReadyForUpgrade(boolean readyForUpgrade) {
        this.readyForUpgrade = readyForUpgrade;
    }


    public boolean isReadyForUpgrade() {
        return readyForUpgrade;
    }

    @Override
    public double calculateDiscount(TravelPackage travelPackage) {
        return travelPackage.getDuration() > 5 ? travelPackage.getPrice() * (1 - DISCOUNT_RATE)
                : travelPackage.getPrice();
    }

    /**
     * @param purchasedPackages PurchasedPackages that need to be brought over to the new membership instance.
     */
    public void setPurchasedPackages(List<TravelPackage> purchasedPackages) {
        for (TravelPackage p : purchasedPackages) {
            addPackage(p);
        }
    }
}
