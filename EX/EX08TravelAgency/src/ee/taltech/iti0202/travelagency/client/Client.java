package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.ArrayList;

public class Client {
    private final Integer age;
    private final String email;
    private final String idCode;
    private final String name;
    private int budget;
    private clientType type;
    private ArrayList<TravelPackage> travelPackages;

    public Client(String idCode, String name, String email, Integer age, int budget) {
        this.idCode = idCode;
        this.name = name;
        this.email = email;
        this.age = age;
        this.travelPackages = new ArrayList<>();
        this.budget = budget;
        this.type = clientType.BASIC;
    }

    public enum clientType {
        BASIC, SILVER, GOLD
    }

    public void updateType () {
        if (travelPackages.size() > 4) {
            type = clientType.GOLD;
        } else if (travelPackages.size() > 2) {
            type = clientType.SILVER;
        }
    }

    public void updateBudget(int newBudget) {
        budget = newBudget;
    }

    public void buyTravelPackage(TravelPackage travelPackageToBuy) {
        if (budget > travelPackageToBuy.getPrice()) {

        }

    }
}
