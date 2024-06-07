package ee.taltech.iti0202.travelagency.travelpackageorder;

import ee.taltech.iti0202.travelagency.ApplicationLogger;
import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Travel package order.
 */
public class TravelPackageOrder {
    Client client;
    TravelPackage travelPackage;
    Logger logger = ApplicationLogger.getLogger(TravelPackageOrder.class.getName());

    /**
     * Instantiates a new Travel package order.
     *
     * @param client        the client
     * @param travelPackage the travel package
     */
    public TravelPackageOrder(Client client, TravelPackage travelPackage) {
        this.client = client;
        this.travelPackage = travelPackage;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        logger.log(Level.INFO, "Getting travel package order client");
        return client;
    }

    /**
     * Gets travel package.
     *
     * @return the travel package
     */
    public TravelPackage getTravelPackage() {
        logger.log(Level.INFO, "Getting travel package order package");
        return travelPackage;
    }
}
