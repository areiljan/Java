package ee.taltech.iti0202.travelagency;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    public static final double DISCOUNT_FIVE = 0.05;
    public static final double DISCOUNT_TEN = 0.1;
    private final String id;
    private final String name;
    private final String email;
    private final int age;
    private CustomerStatus status;
    private double budget;
    private final List<TravelPackage> purchasedTrips;

    /**
     * Constructor for customer
     */
    Customer(String id, String name, String email, int age, double budget) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.status = CustomerStatus.REGULAR;
        this.budget = budget;
        this.purchasedTrips = new ArrayList<>();
    }

    /**
     * Get id
     * @return id
     */
    public String getId() {
        return id;
    }
    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Get status
     * @return satus
     */
    public CustomerStatus getStatus() {
        return status;
    }

    /**
     * Get budget
     * @return budget
     */
    public double getBudget() {
        return budget;
    }

    /**
     * List of purchased trips
     * @return list
     */
    public List<TravelPackage> getPurchasedTrips() {
        return purchasedTrips != null ? new ArrayList<>(purchasedTrips) : new ArrayList<>();
    }
    /**
     * Set status
     * @param status for status
     */
    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    /**
     * Purchase package
     * @param travelPackage for package
     */
    public void purchaseTravelPackage(TravelPackage travelPackage) {
        if (travelPackage.getPrice() <= budget) {
            purchasedTrips.add(travelPackage);
            budget -= travelPackage.getPrice();
            updateStatus();
        } else {
            System.out.println("Not enough budget to purchase this package.");
        }
    }

    /**
     * Update status
     */
    private void updateStatus() {
        if (purchasedTrips.size() >= 5) {
            setStatus(CustomerStatus.GOLD);
        } else if (purchasedTrips.size() >= 3) {
            setStatus(CustomerStatus.SILVER);
        }
    }

    /**
     * Calculate discount
     * @return discount
     */
    public double calculateDiscount(TravelPackage travelPackage) {
        double discount = 0.0;
        if (status == CustomerStatus.SILVER && travelPackage.getDuration() > 5) {
            discount = DISCOUNT_FIVE;
        } else if (status == CustomerStatus.GOLD && travelPackage.getDuration() > 5) {
            discount = DISCOUNT_TEN;
        }
        return discount;
    }
}
