package ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.Customer;
import ee.taltech.iti0202.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.TravelPackage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example usage
        TravelAgency agency = new TravelAgency();

        Customer customer1 = new Customer("1", "John", "john@example.com", 30, 2000);
        Customer customer2 = new Customer("2", "Alice", "alice@example.com", 25, 3000);

        agency.addCustomer(customer1);
        agency.addCustomer(customer2);

        List<String> activities = new ArrayList<>();
        activities.add("Sightseeing");
        activities.add("Scuba Diving");

        TravelPackage package1 = new TravelPackageBuilder()
                .setId("1")
                .setName("Beach Vacation")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(activities)
                .build();

        TravelPackage package2 = new TravelPackageBuilder()
                .setId("2")
                .setName("Cultural Tour")
                .setPrice(800)
                .setStartDate("2024-07-15")
                .setEndDate("2024-07-20")
                .setCountry("Italy")
                .setType("Cultural")
                .setActivities(activities)
                .build();

        agency.addTravelPackage(package1);
        agency.addTravelPackage(package2);

        customer1.purchaseTravelPackage(package1);
        customer2.purchaseTravelPackage(package2);

        // Log activities
        agency.logActivity("Customer " + customer1.getName() + " purchased " + package1.getName());
        agency.logActivity("Customer " + customer2.getName() + " purchased " + package2.getName());

        // Example: Getting all customers and travel packages
        List<Customer> allCustomers = agency.getCustomers();
        List<TravelPackage> allPackages = agency.getTravelPackages();

        // Example: Printing all customers
        for (Customer customer : allCustomers) {
            System.out.println(customer.getName());
        }

        // Example: Printing all travel packages
        for (TravelPackage travelPackage : allPackages) {
            System.out.println(travelPackage.getName());
        }
    }
}