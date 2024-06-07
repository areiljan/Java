package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.ApplicationLogger;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;
import ee.taltech.iti0202.travelagency.travelpackage.UnknownClientStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a client.
 */
public class Client {
    private final String idCode;
    private final String name;
    private final String email;
    private final int age;
    private int budget = 0;
    private final List<TravelPackage> purchasedTravelPackages = new ArrayList<>();
    /**
     * The Logger.
     */
    Logger logger = ApplicationLogger.getLogger(Client.class.getName());


    /**
     * Creates a new Client with id code, name, email and age.
     *
     * @param idCode the id code
     * @param name   the name
     * @param email  the e mail
     * @param age    the age
     */
    public Client(String idCode, String name, String email, int age) {
        this.idCode = idCode;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    /**
     *   id code of this client.
     *
     * @return the id code
     */
    public String getIdCode() {
        logger.log(Level.INFO, "Getting client ID");
        return idCode;
    }


    /**
     * Returns the name of this client
     *
     * @return the name
     */
    public String getName() {
        logger.log(Level.INFO, "Getting client name");
        return name;
    }

    /**
     * Returns the email of this client.
     *
     * @return the email
     */
    public String getEmail() {
        logger.log(Level.INFO, "Getting client email");
        return email;
    }

    /**
     * Returns the age of this client.
     *
     * @return the age
     */
    public int getAge() {
        logger.log(Level.INFO, "Getting client age");
        return age;
    }

    /**
     * Sets the budget for this object.
     *
     * @param budget the budget
     */
    public void setBudget(int budget) {
        logger.log(Level.INFO, "Setting client budget");
        this.budget = budget;
    }

    /**
     * Returns the budget of this client.
     *
     * @return the budget
     */
    public int getBudget() {
        logger.log(Level.INFO, "Getting client budget");
        return budget;
    }

    /**
     * Returns purchased travel packages.
     *
     * @return the purchased travel packages
     */
    public List<TravelPackage> getPurchasedTravelPackages() {
        logger.log(Level.INFO, "Getting client purchased travel packages");
        return purchasedTravelPackages;
    }

    /**
     * Returns the status of this client.
     *
     * @return the status
     */
    public ClientStatus getStatus() {
        logger.log(Level.INFO, "Getting client status");
        if (purchasedTravelPackages.size() >= 5) {
            return ClientStatus.GOLD_CUSTOMER;
        }

        if (purchasedTravelPackages.size() >= 3) {
            return ClientStatus.SILVER_CUSTOMER;
        }

        return ClientStatus.REGULAR_CUSTOMER;
    }

    /**
     * Can buy package boolean.
     *
     * @param travelPackage the travel package
     * @return the boolean
     * @throws CannotBuyTravelPackageException cannot buy travel package exception
     * @throws UnknownClientStatusException    the unknown client status exception
     */
    public boolean canBuyPackage(TravelPackage travelPackage)
            throws CannotBuyTravelPackageException, UnknownClientStatusException {
        logger.log(Level.INFO, "Getting whether the customer makes a purchase");

        if (this.budget >= travelPackage.getPrice(this.getStatus())) {
            return true;
        }

        throw new CannotBuyTravelPackageException("There are no funds to purchase a travel package!");
    }

    /**
     * Adds the package to the client's purchased packages.
     *
     * @param travelPackage the travel package
     */
    public void addPackage(TravelPackage travelPackage) {
        logger.log(Level.INFO, "Adding travel package");
        this.purchasedTravelPackages.add(travelPackage);
    }
}
