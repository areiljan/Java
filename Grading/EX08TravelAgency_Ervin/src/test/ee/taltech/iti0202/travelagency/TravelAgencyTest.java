package test.ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.client.*;
import ee.taltech.iti0202.travelagency.packages.TravelPackage;
import ee.taltech.iti0202.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.packages.TravelPackageBuilder;
import ee.taltech.iti0202.travelagency.packages.TravelPackageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class TravelAgencyTest {

    private TravelAgency agency;
    private Client standardClient;
    private Client silverClient;
    private Client goldClient;
    private Client sameEmailClient;
    private Client sameIdClient;
    private TravelPackage package1;
    private TravelPackage package2;
    private TravelPackage package3;
    private TravelPackage package4;
    private TravelPackage package5;
    private TravelPackage sameIdPackage;

    @BeforeEach
    void setUp() {
        agency = new TravelAgency();

        standardClient = new StandardClientBuilder().setId(1).setName("John Standard").setEmail("john.standard@gmail.com").setAge(25).setBudget(10000.00).createStandardClient();
        // silverClient manually set to a client with Silver benefits. Aspects regarding promotions are not considered currently.
        silverClient = new SilverClientBuilder().setId(2).setName("John Silver").setEmail("john.silver@gmail.com").setAge(28).setBudget(20000.00).createSilverClient();
        // goldClient manually set to a client with Gold benefits. Aspects regarding promotions are not considered currently.
        goldClient = new GoldClientBuilder().setId(3).setName("John Gold").setEmail("john.gold@gmail.com").setAge(30).setBudget(30000.00).createGoldClient();

        sameEmailClient = new GoldClientBuilder().setId(3).setName("Mike Gold").setEmail("mike.gold@gmail.com").setAge(32).setBudget(5000.00).createGoldClient();
        sameIdClient = new GoldClientBuilder().setId(4).setName("Mike Gold").setEmail("john.gold@gmail.com").setAge(32).setBudget(5000.00).createGoldClient();

        package1 = new TravelPackageBuilder().setId(1).setName("Location One").setPrice(1000).setStartDate(LocalDate.now()).setEndDate(LocalDate.now().
                plusDays(7)).setCountry("Estonia1").setType(TravelPackageType.ADVENTURE).createTravelPackage();
        package2 = new TravelPackageBuilder().setId(2).setName("Location Two").setPrice(1500).setStartDate(LocalDate.now()).setEndDate(LocalDate.now().
                plusDays(5)).setCountry("Estonia2").setType(TravelPackageType.CULTURAL_TRIP).createTravelPackage();
        package3 = new TravelPackageBuilder().setId(3).setName("Location Three").setPrice(2500).setStartDate(LocalDate.now()).setEndDate(LocalDate.now().
                plusDays(7)).setCountry("Estonia3").setType(TravelPackageType.ADVENTURE).createTravelPackage();
        package4 = new TravelPackageBuilder().setId(4).setName("Location Five").setPrice(3000).setStartDate(LocalDate.now()).setEndDate(LocalDate.now().
                plusDays(5)).setCountry("Estonia4").setType(TravelPackageType.CULTURAL_TRIP).createTravelPackage();
        package5 = new TravelPackageBuilder().setId(5).setName("Location Six").setPrice(3500).setStartDate(LocalDate.now()).setEndDate(LocalDate.now().
                plusDays(7)).setCountry("Estonia5").setType(TravelPackageType.CULTURAL_TRIP).createTravelPackage();
        sameIdPackage = new TravelPackageBuilder().setId(5).setName("Location Seven").setPrice(4000).setStartDate(LocalDate.now()).setEndDate(LocalDate.now().
                plusDays(5)).setCountry("Estonia6").setType(TravelPackageType.CULTURAL_TRIP).createTravelPackage();

        agency.addClient(standardClient);
        agency.addClient(silverClient);
        agency.addClient(goldClient);
        agency.addPackage(package1);
        agency.addPackage(package2);
        agency.addPackage(package3);
        agency.addPackage(package4);
        agency.addPackage(package5);
    }

    @Test
    void testSellPackageToClient() {
        assertTrue(agency.sellPackageToClient(1, 1)); // John Standard buys Location One
        assertEquals(9000.00, standardClient.getBudget()); // Budget should be updated

        // Since the sellPackageToClient method calls the client purchasePackage methods there is no need to test further.
    }

    @Test
    void testPromotionFromStandardToSilver() {
        agency.sellPackageToClient(standardClient.getId(), package1.getId());
        agency.sellPackageToClient(standardClient.getId(), package2.getId());
        agency.sellPackageToClient(standardClient.getId(), package3.getId());
        // After buying 3 packages John Standard Should now be a Silver client.
        assertEquals("Silver", agency.getClientStatus(standardClient.getId()));
    }

    @Test
    void testPromotionFromSilverToGold() {
        agency.sellPackageToClient(silverClient.getId(), package1.getId());
        agency.sellPackageToClient(silverClient.getId(), package2.getId());
        agency.sellPackageToClient(silverClient.getId(), package3.getId());
        agency.sellPackageToClient(silverClient.getId(), package4.getId());
        agency.sellPackageToClient(silverClient.getId(), package5.getId());
        // After buying 5 packages John Silver Should now be a Gold client.
        assertEquals("Gold", agency.getClientStatus(silverClient.getId()));
    }

    @Test
    void testCantAddClientWithSameId() {
        assertFalse(agency.addClient(sameIdClient));
    }

    @Test
    void testCantAddClientWithSameEmail() {
        assertFalse(agency.addClient(sameEmailClient));
    }

    @Test
    void testCantAddPackageWithSameId() {
        assertFalse(agency.addPackage(sameIdPackage));
    }

    @Test
    void testGetTopClients() {
        agency.sellPackageToClient(standardClient.getId(), package1.getId());
        agency.sellPackageToClient(standardClient.getId(), package2.getId());
        agency.sellPackageToClient(standardClient.getId(), package3.getId());
        // Standard client has now bought 3 packages.

        agency.sellPackageToClient(silverClient.getId(), package1.getId());
        agency.sellPackageToClient(silverClient.getId(), package2.getId());
        agency.sellPackageToClient(silverClient.getId(), package3.getId());
        agency.sellPackageToClient(silverClient.getId(), package4.getId());
        agency.sellPackageToClient(silverClient.getId(), package5.getId());
        // Silver client has now bought 5 packages.

        List<Client> topClients = agency.getTopClients();

        assertTrue(topClients.stream().anyMatch(client -> client.getId() == silverClient.getId()));

        assertEquals(3, topClients.size()); // Three clients have been added among the clients.
        assertEquals(silverClient.getId(), topClients.get(0).getId()); // Silver client has the most packages.
        assertEquals(standardClient.getId(), topClients.get(1).getId()); // Followed by standard client.
        assertEquals(goldClient.getId(), topClients.get(2).getId()); // Gold client is last due to having 0 packages.
    }

    @Test
    void testGetMostPopularPackages() {
        agency.sellPackageToClient(standardClient.getId(), package1.getId()); // Purchased once
        agency.sellPackageToClient(standardClient.getId(), package2.getId()); // Purchased once
        agency.sellPackageToClient(silverClient.getId(), package1.getId());   // Package 1 now purchased twice, making it the most popular
        agency.sellPackageToClient(goldClient.getId(), package3.getId());     // Purchased once

        List<TravelPackage> mostPopularPackages = agency.getMostPopularPackages();


        // Package 1 is the most popular.
        assertFalse(mostPopularPackages.isEmpty(), "The list of popular packages should not be empty.");
        assertEquals(package1, mostPopularPackages.get(0), "Package 1 should be the most popular.");

        assertEquals(3, mostPopularPackages.size(), "There should be 3 unique packages in the popularity list.");
    }
}