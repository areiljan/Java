package test.ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.client.GoldClientBuilder;
import ee.taltech.iti0202.travelagency.client.SilverClientBuilder;
import ee.taltech.iti0202.travelagency.client.StandardClientBuilder;
import ee.taltech.iti0202.travelagency.packages.TravelPackage;
import ee.taltech.iti0202.travelagency.packages.TravelPackageBuilder;
import ee.taltech.iti0202.travelagency.packages.TravelPackageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Client standardClient;
    private Client silverClient;
    private Client goldClient;
    private Client poorClient;
    private TravelPackage travelPackage;
    @BeforeEach
    void setUp() {
        standardClient = new StandardClientBuilder().setId(1).setName("John Standard").setEmail("john.standard@gmail.com").setAge(30).setBudget(5000.00).createStandardClient();
        silverClient = new SilverClientBuilder().setId(2).setName("John Silver").setEmail("john.silver@gmail.com").setAge(28).setBudget(5000.00).createSilverClient();
        goldClient = new GoldClientBuilder().setId(3).setName("John Gold").setEmail("john.gold@gmail.com").setAge(32).setBudget(5000.00).createGoldClient();;

        poorClient = new StandardClientBuilder().setId(4).setName("John Poor").setEmail("john.poor@gmail.com").setAge(18).setBudget(100.00).createStandardClient();

        travelPackage = new TravelPackageBuilder().setId(1).setName("Beach Paradise").setPrice(1000.00).setStartDate(LocalDate.now()).setEndDate(LocalDate.now()
                .plusDays(7)).setCountry("Estonia").setType(TravelPackageType.BEACH_HOLIDAY).createTravelPackage();
    }

    @Test
    void testPurchasePackageSuccessStandardClient() {
        assertTrue(standardClient.purchasePackage(travelPackage));
        assertEquals(4000.00, standardClient.getBudget()); // Money left when buying without discount.
        assertFalse(standardClient.getPurchasedPackages().isEmpty());
        assertTrue(standardClient.getPurchasedPackages().contains(travelPackage));
    }

    @Test
    void testPurchasePackageSuccessSilverClient() {
        assertTrue(silverClient.purchasePackage(travelPackage));
        assertEquals(4050.00, silverClient.getBudget()); // Money left when buying with 5% discount.
        assertFalse(silverClient.getPurchasedPackages().isEmpty());
        assertTrue(silverClient.getPurchasedPackages().contains(travelPackage));
    }

    @Test
    void testPurchasePackageSuccessGoldClient() {
        assertTrue(goldClient.purchasePackage(travelPackage));
        assertEquals(4100.00, goldClient.getBudget()); // Money left when buying with 10% discount.
        assertFalse(goldClient.getPurchasedPackages().isEmpty());
        assertTrue(goldClient.getPurchasedPackages().contains(travelPackage));
    }

    @Test
    void testPurchasePackageFailsWhenNotEnoughBudget() {
        assertFalse(poorClient.purchasePackage(travelPackage));
        assertEquals(100.00, poorClient.getBudget()); // The client does not lose any money.
    }

}
