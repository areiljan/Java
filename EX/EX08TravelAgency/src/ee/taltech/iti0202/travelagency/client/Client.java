package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Client {
    private final Integer age;
    private final String email;
    private final String idCode;
    private final String name;
    private Integer budget;
    private clientType type;
    private ArrayList<TravelPackage> purchasedPackages;
    private String phoneNumber;
    private String city;

    /**
     * Client constructor.
     * @param idCode - mandatory parameter idCode.
     * @param name - mandatory parameter name.
     * @param email - mandatory parameter email.
     * @param age - mandatory parameter age.
     * @param budget - if left empty the budget will be infinite.
     * @param phoneNumber - the client can leave a phone number.
     * @param city - the client can specify the city.
     */
    public Client(String idCode, String name, String email, Integer age, Integer budget, String phoneNumber, String city) {
        this.idCode = idCode;
        this.name = name;
        this.email = email;
        this.age = age;
        this.purchasedPackages = new ArrayList<>();
        this.budget = budget;
        this.type = clientType.BASIC;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    /**
     * Budget getter.
     * @return - budget.
     */
    public Integer getBudget() {
        return budget;
    }

    /**
     * ClientType getter.
     * @return - the current type of the client.
     */
    public clientType getType() {
        return type;
    }

    /**
     * Name getter.
     * @return - name of the client.
     */
    public String getName() {
        return name;
    }

    public enum clientType {
        BASIC, SILVER, GOLD
    }

    /**
     * Update the type of the client.
     */
    private void updateType() {
        if (purchasedPackages.size() > 4) {
            type = clientType.GOLD;
        } else if (purchasedPackages.size() > 2) {
            type = clientType.SILVER;
        }
    }

    /**
     * The client can update his/her budget.
     * @param newBudget
     */
    public void updateBudget(int newBudget) {
        budget = newBudget;
    }

    /**
     * The client can buy a travel package from the specified travel agency.
     * @param travelAgency - where to buy the package from.
     * @param travelPackageToBuy - which package.
     */
    public void buyTravelPackage(TravelAgency travelAgency, TravelPackage travelPackageToBuy) throws InsufficientFundsException {
        float priceMultiplier = 1.0F;
        // if the length of the trip is longer than five days and you are a silver client, get 10 percent off.
        if (type == clientType.SILVER
                && ChronoUnit.DAYS.between(travelPackageToBuy.getStartDate(),
                travelPackageToBuy.getEndDate()) > 5) {
            priceMultiplier = 0.9F;
            System.out.println("You will get 10% off of this offer.");
            // if the length of the trip is longer than three days and you are a gold client, get 15 percent off.
        } else if (type == clientType.GOLD
                && ChronoUnit.DAYS.between(travelPackageToBuy.getStartDate(),
                travelPackageToBuy.getEndDate()) > 3) {
            priceMultiplier = 0.85F;
            System.out.println("You will get 15% off of this offer.");
        }

        if ((budget == null) || (budget > travelPackageToBuy.getPrice() * priceMultiplier)) {
            if (!travelAgency.getTravelPackages().containsKey(travelPackageToBuy)) {
                // register the buyer as client when he is not upon buying.
                travelAgency.addClient(this);
            }
            purchasedPackages.add(travelPackageToBuy);
            travelAgency.packageBought(this, travelPackageToBuy);
            updateType();
            budget -= travelPackageToBuy.getPrice();
        } else {
            throw new InsufficientFundsException("Not enough money.");
        }
    }
}
