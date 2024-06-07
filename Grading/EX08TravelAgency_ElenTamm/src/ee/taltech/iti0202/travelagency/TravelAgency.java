package ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.client.CannotBuyTravelPackageException;
import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;
import ee.taltech.iti0202.travelagency.travelpackage.UnknownClientStatusException;
import ee.taltech.iti0202.travelagency.travelpackageorder.TravelPackageOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Represents a travel agency.
 */
public class TravelAgency {
    private final List<TravelPackageOrder> orders = new ArrayList<>();

    Logger logger = ApplicationLogger.getLogger(TravelAgency.class.getName());

    /**
     * Gets orders.
     *
     * @return the list of orders
     */
    public List<TravelPackageOrder> getOrders() {
        logger.log(Level.INFO, "Getting travel agency orders");
        return orders;
    }

    /**
     * Buy travel packages boolean.
     * @param client the client
     * @param travelPackage the travel package
     * @return the boolean
     * @throws CannotBuyTravelPackageException if the client does not have enough money
     * @throws UnknownClientStatusException
     */
    public boolean buyTravelPackage(Client client, TravelPackage travelPackage)
            throws CannotBuyTravelPackageException, UnknownClientStatusException {
        logger.log(
                Level.INFO,
                String.format("Client %s is buying package %s", client.getName(), travelPackage.getName())
        );

        client.canBuyPackage(travelPackage);
        TravelPackageOrder travelPackageOrder = new TravelPackageOrder(client, travelPackage);
        orders.add(travelPackageOrder);
        client.addPackage(travelPackage);

        return true;
    }

    /**
     * Returns top3 packages of this travel agency.
     *
     * @return the top3 packages
     */
    public List<TravelPackage> getTop3Packages() {
        logger.log(Level.INFO, "Fetching top 3 packages");
        List<TravelPackage> topPackages = this.orders.stream()
                .collect(
                        Collectors.groupingBy(TravelPackageOrder::getTravelPackage, Collectors.counting())
                )
                .entrySet().stream()
                .sorted(Map.Entry
                        .<TravelPackage, Long>comparingByValue()
                        .thenComparing(o -> o.getKey().getName())
                        .thenComparing(o -> o.getKey().getClass().getName())
                        .reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        return topPackages;
    }

    /**
     * Returns top3 clients of this travel agency.
     *
     * @return the top3 clients
     */
    public List<Client> getTop3Clients() {
        logger.log(Level.INFO, "Fetching top 3 clients");
        List<Client> topClients = this.orders.stream()
                .collect(
                        Collectors.groupingBy(TravelPackageOrder::getClient, Collectors.counting())
                )
                .entrySet().stream()
                .sorted(Map.Entry
                        .<Client, Long>comparingByValue()
                        .thenComparing(o -> o.getKey().getName())
                        .reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        logger.log(Level.FINE, topClients.toString());

        return topClients;
    }
}
