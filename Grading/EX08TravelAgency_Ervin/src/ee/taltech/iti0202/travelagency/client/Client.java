package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.packages.TravelPackage;

import java.util.ArrayList;
import java.util.List;

public abstract class Client {
    protected int id;
    protected String name;
    protected String email;
    protected int age;
    protected double budget;
    protected List<TravelPackage> purchasedPackages = new ArrayList<>();

    /**
     * @param id Id
     * @param name Name
     * @param email Email
     * @param age Age
     * @param budget Budget
     */
    public Client(int id, String name, String email, int age, double budget) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.budget = budget;
    }

    /**
     * @param travelPackage TravelPackage being bought.
     * @return Boolean.
     */
    public abstract boolean purchasePackage(TravelPackage travelPackage);

    /**
     * @param travelPackage TravelPackage to be added.
     */
    public void addPackage(TravelPackage travelPackage) {
        if (!purchasedPackages.contains(travelPackage)) {
            purchasedPackages.add(travelPackage);
        }
    }

    /**
     * @return PurchasedPackages
     */
    public List<TravelPackage> getPurchasedPackages() {
        return purchasedPackages;
    }

    /**
     * @param travelPackage TravelPackage
     * @return Discount
     */
    public abstract double calculateDiscount(TravelPackage travelPackage);

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public double getBudget() {
        return budget;
    }
}
