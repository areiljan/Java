package ee.taltech.iti0202.travelagency.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class TravelAgency {
    private ArrayList clients;
    private HashMap<TravelPackage, Integer> travelPackages;

    /**
     * TravelAgency constructor.
     */
    public TravelAgency() {
        this.clients = new ArrayList();
        this.travelPackages = new HashMap<TravelPackage, Integer>();
    }

    /**
     * Add a client to the TravelAgency.
     * @param clientToAdd - the client that needs adding.
     */
    public void addClient(Client clientToAdd) {
        clients.add(clientToAdd);
    }

    /**
     * Get the clients with the most packages bought.
     * @return - a descending list of clients.
     */
    public ArrayList<Client> topClients() {
        //
    }

    /**
     * Return the most popular TravelPackages bought.
     * @return - a descending list of packages.
     */
    public ArrayList<TravelPackage> mostPopularPackages() {

    }
}
