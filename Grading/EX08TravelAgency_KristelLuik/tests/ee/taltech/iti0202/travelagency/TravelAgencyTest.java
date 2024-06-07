package ee.taltech.iti0202.travelagency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelAgencyTest {

    private TravelAgency travelAgency;

    @BeforeEach
    void setUp() {
        // Initialize TravelAgency
        travelAgency = new TravelAgency();
    }

    @Test
    void addCustomer() {
        // Create a customer
        Customer customer = new Customer("1", "Mari", "mari@gmail.com", 25, 3000);

        travelAgency.addCustomer(customer);

        // Check if the customer was added successfully
        assertEquals(1, travelAgency.getCustomers().size());
        assertEquals(customer, travelAgency.getCustomers().get(0));
    }

    @Test
    void addTravelPackage() {
        // Create a travel package
        TravelPackage travelPackage = new TravelPackageBuilder()
                .setId("1")
                .setName("Bali")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Indonesia")
                .setType("Beach")
                .setActivities(Arrays.asList("Scuba Diving"))
                .build();

        // Add the travel package to the travel agency
        travelAgency.addTravelPackage(travelPackage);

        // Check if the travel package was added successfully
        assertEquals(1, travelAgency.getTravelPackages().size());
        assertEquals(travelPackage, travelAgency.getTravelPackages().get(0));
    }

    @Test
    void getTravelPackages() {
        // Create travel packages
        TravelPackage package1 = new TravelPackageBuilder()
                .setId("1")
                .setName("Beach Vacation")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(Arrays.asList("Scuba Diving"))
                .build();
        TravelPackage package2 = new TravelPackageBuilder()
                .setId("2")
                .setName("Cultural Tour")
                .setPrice(800)
                .setStartDate("2024-07-15")
                .setEndDate("2024-07-20")
                .setCountry("Italy")
                .setType("Cultural")
                .setActivities(Arrays.asList("Sightseeing"))
                .build();

        travelAgency.addTravelPackage(package1);
        travelAgency.addTravelPackage(package2);

        List<TravelPackage> retrievedPackages = travelAgency.getTravelPackages();

        assertEquals(2, retrievedPackages.size());
        assertTrue(retrievedPackages.contains(package1));
        assertTrue(retrievedPackages.contains(package2));
    }

    @Test
    void getTopCustomers() {
        Customer customer1 = new Customer("1", "Mari", "mari@gmail.com", 30, 2000);
        Customer customer2 = new Customer("2", "Kadri", "kadri@gmail.com", 25, 3000);
        travelAgency.addCustomer(customer1);
        travelAgency.addCustomer(customer2);

        // Get top customers
        List<Customer> topCustomers = travelAgency.getTopCustomers();

        assertEquals(2, topCustomers.size());
        assertTrue(topCustomers.contains(customer1));
        assertTrue(topCustomers.contains(customer2));
    }

    @Test
    void getMostPopularPackages() {
        Customer customer1 = new Customer("1", "Mari", "mari@gmail.com", 30, 2000);
        Customer customer2 = new Customer("2", "Kadri", "kadri@gmail.com", 25, 3000);
        travelAgency.addCustomer(customer1);
        travelAgency.addCustomer(customer2);


        TravelPackage package1 = new TravelPackageBuilder()
                .setId("1")
                .setName("Beach Vacation")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(Arrays.asList("Scuba Diving"))
                .build();
        TravelPackage package2 = new TravelPackageBuilder()
                .setId("2")
                .setName("Cultural Tour")
                .setPrice(800)
                .setStartDate("2024-07-15")
                .setEndDate("2024-07-20")
                .setCountry("Italy")
                .setType("Cultural")
                .setActivities(Arrays.asList("Sightseeing"))
                .build();
        travelAgency.addTravelPackage(package1);
        travelAgency.addTravelPackage(package2);

        customer1.purchaseTravelPackage(package1);
        customer1.purchaseTravelPackage(package2);
        customer2.purchaseTravelPackage(package1);

        // Get most popular packages
        List<TravelPackage> mostPopularPackages = travelAgency.getMostPopularPackages();

        assertEquals(2, mostPopularPackages.size());
        assertEquals(package1, mostPopularPackages.get(0));
        assertEquals(package2, mostPopularPackages.get(1));
    }

    @Test
    void logActivity() {
        // Log an activity
        travelAgency.logActivity("Test log message");
    }
}
