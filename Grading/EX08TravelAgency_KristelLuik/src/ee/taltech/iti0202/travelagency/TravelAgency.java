package ee.taltech.iti0202.travelagency;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TravelAgency {
    private static final Logger LOGGER = Logger.getLogger(TravelAgency.class.getName());

    private final List<Customer> customers;
    private final List<TravelPackage> travelPackages;

    /**
     * Constructor for travel agency
     */
    public TravelAgency() {
        this.customers = new ArrayList<>();
        this.travelPackages = new ArrayList<>();
    }

    /**
     * Add customer
     *
     * @param customer for customer
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Get customers
     *
     * @return customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Add travel package
     *
     * @param travelPackage for package
     */
    public void addTravelPackage(TravelPackage travelPackage) {
        travelPackages.add(travelPackage);
    }

    /**
     * Get packages
     *
     * @return travel packages
     */
    public List<TravelPackage> getTravelPackages() {
        return travelPackages;
    }

    /**
     * Get top customers
     *
     * @return customers
     */
    public List<Customer> getTopCustomers() {
        // Group purchased trips by customer and count the number of trips
        Map<Customer, Long> tripsPerCustomer = customers.stream()
                .collect(Collectors.toMap(
                        customer -> customer,
                        customer -> (long) customer.getPurchasedTrips().size()
                ));

        // Sort customers by the number of trips (descending order)
        List<Customer> topCustomers = tripsPerCustomer.entrySet().stream()
                .sorted(Map.Entry.<Customer, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return topCustomers;
    }

    /**
     * Get most popular packages
     *
     * @return travel packages
     */
    public List<TravelPackage> getMostPopularPackages() {
        // Create a map to store the count of purchases for each travel package
        Map<TravelPackage, Integer> packageCounts = new HashMap<>();

        // Count purchases for each travel package
        for (Customer customer : customers) {
            for (TravelPackage travelPackage : customer.getPurchasedTrips()) {
                packageCounts.put(travelPackage, packageCounts.getOrDefault(travelPackage, 0) + 1);
            }
        }

        // Sort the map entries by value (number of purchases)
        List<Map.Entry<TravelPackage, Integer>> sortedEntries = packageCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        // Extract the keys (travel packages) from sorted entries
        return sortedEntries.stream()
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * Log info
     *
     * @param message for message
     */
    public void logActivity(String message) {
        LOGGER.info(message);
    }
}
