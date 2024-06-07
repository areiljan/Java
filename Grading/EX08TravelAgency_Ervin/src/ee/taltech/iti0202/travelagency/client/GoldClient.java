package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.packages.TravelPackage;

import java.util.List;

public class GoldClient extends Client {

    private static final double DISCOUNT_RATE = 0.1; // 10% discount

    /**
     * @param id Id
     * @param name Name
     * @param email Email
     * @param age Age
     * @param budget Budget
     */
    public GoldClient(int id, String name, String email, int age, double budget) {
        super(id, name, email, age, budget);
    }

    @Override
    public boolean purchasePackage(TravelPackage travelPackage) {
        double discountedPrice = calculateDiscount(travelPackage);
        if (discountedPrice <= this.budget) {
            this.budget -= discountedPrice;
            addPackage(travelPackage);
            return true;
        }
        return false;
    }

    /**
     * @param travelPackage TravelPackage
     * @return Discount.
     */
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
