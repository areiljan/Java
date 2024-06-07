package ee.taltech.iti0202.travelagency;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



class CustomerTest {

    @Test
    void getId() {
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        assertEquals("1", customer.getId());
    }

    @Test
    void getName() {
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        assertEquals("Mati", customer.getName());
    }

    @Test
    void getEmail() {
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        assertEquals("mati@gmail.com", customer.getEmail());
    }

    @Test
    void getAge() {
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        assertEquals(30, customer.getAge());
    }

    @Test
    void getStatus() {
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        assertEquals(CustomerStatus.REGULAR, customer.getStatus());
    }

    @Test
    void getBudget() {
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        assertEquals(2000, customer.getBudget());
    }

    @Test
    void getPurchasedTrips() {
        List<TravelPackage> purchasedTrips = new ArrayList<>();
        purchasedTrips.add(new TravelPackageBuilder()
                .setId("1")
                .setName("Bali")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(new ArrayList<>())
                .build());
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        customer.purchaseTravelPackage(purchasedTrips.get(0));
        assertEquals(purchasedTrips, customer.getPurchasedTrips());
    }

    @Test
    void setStatus() {
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        customer.setStatus(CustomerStatus.SILVER);
        assertEquals(CustomerStatus.SILVER, customer.getStatus());
    }

    @Test
    void purchaseTravelPackage() {
        Customer customer = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        TravelPackage travelPackage = new TravelPackageBuilder()
                .setId("1")
                .setName("Bali")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(new ArrayList<>())
                .build();
        customer.purchaseTravelPackage(travelPackage);
        assertTrue(customer.getPurchasedTrips().contains(travelPackage));
    }

    @Test
    void calculateDiscountSilver() {
        Customer customerSilver = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        customerSilver.setStatus(CustomerStatus.SILVER);
        TravelPackage travelPackage1 = new TravelPackageBuilder()
                .setId("1")
                .setName("Bali")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(new ArrayList<>())
                .build();
        assertEquals(0.05, customerSilver.calculateDiscount(travelPackage1));
    }

    @Test
    void calculateDiscountGold() {
        Customer customerGold = new Customer("2", "Kati", "kati@gmail.com", 25, 3000);
        customerGold.setStatus(CustomerStatus.GOLD);
        TravelPackage travelPackage2 = new TravelPackageBuilder()
                .setId("2")
                .setName("Paris")
                .setPrice(800)
                .setStartDate("2024-07-14")
                .setEndDate("2024-07-20")
                .setCountry("Italy")
                .setType("Cultural")
                .setActivities(new ArrayList<>())
                .build();
        assertEquals(0.1, customerGold.calculateDiscount(travelPackage2));
    }

    @Test
    void calculateDiscountNoDiscount() {
        Customer customerSilver = new Customer("1", "Mati", "mati@gmail.com", 30, 2000);
        customerSilver.setStatus(CustomerStatus.SILVER);
        TravelPackage travelPackage1 = new TravelPackageBuilder()
                .setId("1")
                .setName("Bali")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-05")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(new ArrayList<>())
                .build();
        assertEquals(0.00, customerSilver.calculateDiscount(travelPackage1));
    }
}
