package ee.taltech.iti0202.travelagency.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TravelAgency {
    private HashMap<Client, Integer> clients;
    private HashMap<TravelPackage, Integer> travelPackages;
    private static final Logger LOGGER = Logger.getLogger("TravelAgency Logger");


    /**
     * TravelAgency constructor.
     */
    public TravelAgency(String name) {
        this.clients = new HashMap<>();
        this.travelPackages = new HashMap<>();
    }

    /**
     * Get the clients as a list.
     * @return - clients.
     */
    public HashMap<Client, Integer> getClients() {
        return clients;
    }

    /**
     * Get the travelPackages in a hashmap.
     * The value signals the amount of times this package has been bought.
     * @return - hashmap of packages and times bought.
     */
    public HashMap<TravelPackage, Integer> getTravelPackages() {
        return travelPackages;
    }

    /**
     * Make the count of this specific TravelPackage higher.
     */
    public void packageBought(Client buyer, TravelPackage boughtPackage) {
        int updatedPackageValue = travelPackages.get(boughtPackage) + 1;
        travelPackages.put(boughtPackage, updatedPackageValue);

        int updatedClientValue = clients.get(buyer) + 1;
        clients.put(buyer, updatedClientValue);

        LOGGER.info(buyer.getName() + " has bought the Travel Package of " + boughtPackage.getName() + ".");
    }


    /**
     * Add a client to the TravelAgency.
     * @param clientToAdd - the client that needs adding.
     */
    public void addClient(Client clientToAdd) {
        clients.put(clientToAdd, 0);
        LOGGER.info("client " + clientToAdd.getName() + " added to the Travel Agency.");
    }

    /**
     * Add a travel package to the TravelAgency.
     * @param travelPackageToAdd - which package to add.
     */
    public void addTravelPackage(TravelPackage travelPackageToAdd) {
        travelPackages.put(travelPackageToAdd, 0);
        LOGGER.info("travel package " + travelPackageToAdd.getName() + " added to the Travel Agency.");
    }

    /**
     * Get the clients with the most packages bought.
     * @return - a descending list of clients.
     */
    public List<Map.Entry<Client, Integer>> topClients() {
        return clients.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();
    }

    /**
     * Return the most popular TravelPackages bought.
     * @return - a descending list of packages.
     */
    public List<Map.Entry<TravelPackage, Integer>> mostPopularPackages() {
        return travelPackages.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();
    }
}
