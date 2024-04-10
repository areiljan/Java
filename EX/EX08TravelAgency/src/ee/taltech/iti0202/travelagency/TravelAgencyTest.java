package ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.client.ClientBuilder;
import ee.taltech.iti0202.travelagency.client.InsufficientFundsException;
import ee.taltech.iti0202.travelagency.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TravelAgencyTest {

    @org.junit.jupiter.api.Test
    void clientBuilderAgeNegativeThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ClientBuilder("5403039203", "Priit",
                    "priittoobal@gmail.com", -15);
        });
    }

    @org.junit.jupiter.api.Test
    void cannotBuyTravelPackageIfNoMoney() {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        TravelPackage travelPackage1 = new TravelPackage("223", "Tenerife Toxic Boogaloo",
                1500, LocalDate.of(2024, 12, 12), LocalDate.of(2024,
                12, 20), "Spain", TravelPackage.TravelType.PARTY);
        Client client1 = new ClientBuilder("342143", "Fred", "fredpeep@gmail.com", 23)
                .withPhoneNumber("+372 9921 2039")
                .withCity("Tallinn")
                .withBudget(10)
                .build();

        travelAgency1.addTravelPackage(travelPackage1);
        travelAgency1.addClient(client1);

        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            client1.buyTravelPackage(travelAgency1, travelPackage1);
        });
    }

    @org.junit.jupiter.api.Test
    void clientsBudgetDecreasesUponTravelPackageBought() throws InsufficientFundsException {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        TravelPackage travelPackage1 = new TravelPackage("223",
                "Tenerife Toxic Boogaloo", 1500, LocalDate.of(2024,
                12, 12), LocalDate.of(2024, 12, 20), "Spain",
                TravelPackage.TravelType.PARTY);
        Client client1 = new ClientBuilder("342143", "Fred", "fredpeep@gmail.com", 23)
                .withPhoneNumber("+372 9921 2039")
                .withCity("Tallinn")
                .withBudget(10000)
                .build();

        travelAgency1.addTravelPackage(travelPackage1);
        travelAgency1.addClient(client1);
        client1.buyTravelPackage(travelAgency1, travelPackage1);

        int expectedMoneyLeft = 8500;
        Assertions.assertEquals(expectedMoneyLeft, client1.getBudget());
    }

    @org.junit.jupiter.api.Test
    void canEditBudget() throws InsufficientFundsException {
        Client client1 = new ClientBuilder("342143", "Fred",
                "fredpeep@gmail.com", 23)
                .withPhoneNumber("+372 9921 2039")
                .withCity("Tallinn")
                .withBudget(10000)
                .build();

        client1.updateBudget(1000); // the client is now a broke boi
        int expectedBudget = 1000;
        Assertions.assertEquals(expectedBudget, client1.getBudget());
    }

    @org.junit.jupiter.api.Test
    void threePurchasedPackagesClientTypeChangesToSilver() throws InsufficientFundsException {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        TravelPackage travelPackage1 = new TravelPackage("223",
                "Tenerife Toxic Boogaloo", 1500, LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 20), "Spain", TravelPackage.TravelType.PARTY);
        TravelPackage travelPackage2 = new TravelPackage("124",
                "Berlin Beach", 1500, LocalDate.of(2024, 6, 12),
                LocalDate.of(2024, 6, 15), "Germany", TravelPackage.TravelType.BEACH);
        TravelPackage travelPackage3 = new TravelPackage("543",
                "Paris tour", 1500, LocalDate.of(2024, 5, 12),
                LocalDate.of(2025, 5, 20), "France", TravelPackage.TravelType.CULTURE);
        Client client1 = new ClientBuilder("342143", "Fred", "fredpeep@gmail.com", 23)
                .withPhoneNumber("+372 9921 2039")
                .withCity("Tallinn")
                .withBudget(100000)
                .build();

        // adding all travelPackages
        travelAgency1.addTravelPackage(travelPackage1);
        travelAgency1.addTravelPackage(travelPackage2);
        travelAgency1.addTravelPackage(travelPackage3);
        // adding the client
        travelAgency1.addClient(client1);
        // buying three travelPackages
        client1.buyTravelPackage(travelAgency1, travelPackage1);
        client1.buyTravelPackage(travelAgency1, travelPackage2);
        client1.buyTravelPackage(travelAgency1, travelPackage3);

        Assertions.assertEquals(Client.ClientType.SILVER, client1.getType());
    }

    @org.junit.jupiter.api.Test
    void fivePurchasedPackagesClientTypeChangesToGold() throws InsufficientFundsException {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        TravelPackage travelPackage1 = new TravelPackage("223", "Tenerife Toxic Boogaloo",
                1500, LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 20), "Spain", TravelPackage.TravelType.PARTY);
        TravelPackage travelPackage2 = new TravelPackage("124", "Berlin Beach",
                1500, LocalDate.of(2024, 6, 12),
                LocalDate.of(2024, 6, 15), "Germany", TravelPackage.TravelType.BEACH);
        TravelPackage travelPackage3 = new TravelPackage("543", "Paris tour",
                1500, LocalDate.of(2024, 5, 12),
                LocalDate.of(2025, 5, 20), "France", TravelPackage.TravelType.CULTURE);
        TravelPackage travelPackage4 = new TravelPackage("512", "December Decadence",
                1500, LocalDate.of(2024, 12, 12),
                LocalDate.of(2025, 12, 20), "Cyprus", TravelPackage.TravelType.PARTY);
        TravelPackage travelPackage5 = new TravelPackage("523", "Mongolia Throat Singing",
                1500, LocalDate.of(2024, 5, 12),
                LocalDate.of(2025, 5, 20), "Mongolia", TravelPackage.TravelType.CULTURE);
        Client client1 = new ClientBuilder("342143", "Fred", "fredpeep@gmail.com", 23)
                .withPhoneNumber("+372 9921 2039")
                .withCity("Tallinn")
                .withBudget(100000)
                .build();

        // adding all travelPackages
        travelAgency1.addTravelPackage(travelPackage1);
        travelAgency1.addTravelPackage(travelPackage2);
        travelAgency1.addTravelPackage(travelPackage3);
        travelAgency1.addTravelPackage(travelPackage4);
        travelAgency1.addTravelPackage(travelPackage5);
        // adding a client
        travelAgency1.addClient(client1);
        // buying all travelPackages
        client1.buyTravelPackage(travelAgency1, travelPackage1);
        client1.buyTravelPackage(travelAgency1, travelPackage2);
        client1.buyTravelPackage(travelAgency1, travelPackage3);
        client1.buyTravelPackage(travelAgency1, travelPackage4);
        client1.buyTravelPackage(travelAgency1, travelPackage5);


        Assertions.assertEquals(Client.ClientType.GOLD, client1.getType());
    }

    @org.junit.jupiter.api.Test
    void travelAgencyAddClientsCanAccessFromAgency() {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        Client client1 = new ClientBuilder("3493943",
                "Fred", "fredpeep@gmail.com", 10)
                .withPhoneNumber("+372 9932 2039")
                .withCity("Narva")
                .withBudget(100000)
                .build();

        // adding the client
        travelAgency1.addClient(client1);

        // the getter will have the clients as a hashmap of clients and amount of packages bought
        HashMap<Object, Integer> expectedClients = new HashMap<>();
        expectedClients.put(client1, 0);
        Assertions.assertEquals(expectedClients, travelAgency1.getClients());
    }

    @org.junit.jupiter.api.Test
    void travelAgencyAddTravelPackageCanAccessFromAgency() {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        TravelPackage travelPackage1 = new TravelPackage("223",
                "Toxic Boogaloo", 1500, LocalDate.of(2024, 12,
                12), LocalDate.of(2024, 12, 20),
                "Spain", TravelPackage.TravelType.PARTY);

        // adding the travelPackage
        travelAgency1.addTravelPackage(travelPackage1);

        // the getter will have the travelPackages as a hashmap of travelPackages and amount of times bought
        HashMap<Object, Integer> expectedPackages = new HashMap<>();
        expectedPackages.put(travelPackage1, 0);
        Assertions.assertEquals(expectedPackages, travelAgency1.getTravelPackages());
    }

    @org.junit.jupiter.api.Test
    void getTopClientOrderedByPackagesBought() throws InsufficientFundsException {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        TravelPackage travelPackage1 = new TravelPackage("223", "Tenerife Toxic Boogaloo",
                1500, LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 20), "Spain", TravelPackage.TravelType.PARTY);
        TravelPackage travelPackage2 = new TravelPackage("124", "Berlin Beach",
                1500, LocalDate.of(2024, 6, 12),
                LocalDate.of(2024, 6, 15), "Germany", TravelPackage.TravelType.BEACH);
        TravelPackage travelPackage3 = new TravelPackage("543", "Paris tour",
                1500, LocalDate.of(2024, 5, 12),
                LocalDate.of(2025, 5, 20), "France", TravelPackage.TravelType.CULTURE);
        Client client1 = new ClientBuilder("342143", "Fred", "fredpeep@gmail.com", 23)
                .withPhoneNumber("+372 9921 2039")
                .withCity("Tallinn")
                .withBudget(100000)
                .build();
        Client client2 = new ClientBuilder("3493943", "Kristian", "kristianski@gmail.com", 45)
                .withPhoneNumber("+372 5432 2139")
                .withCity("Narva")
                .withBudget(100000)
                .build();
        Client client3 = new ClientBuilder("2343943", "Ragnar", "ragnarsinihammas@gmail.com", 55)
                .withPhoneNumber("+372 9001 2029")
                .withCity("Pärnu")
                .withBudget(50000)
                .build();

        // adding all travelPackages
        travelAgency1.addTravelPackage(travelPackage1);
        travelAgency1.addTravelPackage(travelPackage2);
        travelAgency1.addTravelPackage(travelPackage3);
        // adding all clients
        travelAgency1.addClient(client1);
        travelAgency1.addClient(client2);
        travelAgency1.addClient(client3);
        // client1 buys 1 package
        client1.buyTravelPackage(travelAgency1, travelPackage1);
        // client2 buys 2 packages
        client2.buyTravelPackage(travelAgency1, travelPackage1);
        client2.buyTravelPackage(travelAgency1, travelPackage2);
        // client3 buys 3 packages
        client3.buyTravelPackage(travelAgency1, travelPackage1);
        client3.buyTravelPackage(travelAgency1, travelPackage2);
        client3.buyTravelPackage(travelAgency1, travelPackage3);

        // the clients should be in descending order based on the packages bought.
        Map.Entry<Client, Integer> entry1 = new AbstractMap.SimpleEntry<>(client3, 3);
        Map.Entry<Client, Integer> entry2 = new AbstractMap.SimpleEntry<>(client2, 2);
        Map.Entry<Client, Integer> entry3 = new AbstractMap.SimpleEntry<>(client1, 1);
        List<Map.Entry<Client, Integer>> expectedList = new ArrayList<>();
        expectedList.add(entry1);
        expectedList.add(entry2);
        expectedList.add(entry3);
        Assertions.assertEquals(expectedList, travelAgency1.topClients());
    }

    @org.junit.jupiter.api.Test
    void getMostPopularPackagesRightOrder() throws InsufficientFundsException {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        TravelPackage travelPackage1 = new TravelPackage("223", "Tenerife Toxic Boogaloo",
                1500, LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 20), "Spain", TravelPackage.TravelType.PARTY);
        TravelPackage travelPackage2 = new TravelPackage("124", "Berlin Beach",
                1500, LocalDate.of(2024, 6, 12),
                LocalDate.of(2024, 6, 15), "Germany", TravelPackage.TravelType.BEACH);
        TravelPackage travelPackage3 = new TravelPackage("543", "Paris tour",
                1500, LocalDate.of(2024, 5, 12),
                LocalDate.of(2025, 5, 20), "France", TravelPackage.TravelType.CULTURE);
        Client client1 = new ClientBuilder("342143", "Fred", "fredpeep@gmail.com", 23)
                .withPhoneNumber("+372 9921 2039")
                .withCity("Tallinn")
                .withBudget(100000)
                .build();
        Client client2 = new ClientBuilder("3493943", "Kristian", "kristianski@gmail.com", 45)
                .withPhoneNumber("+372 5432 2139")
                .withCity("Narva")
                .withBudget(100000)
                .build();
        Client client3 = new ClientBuilder("2343943", "Ragnar", "ragnarsinihammas@gmail.com", 55)
                .withPhoneNumber("+372 9001 2029")
                .withCity("Pärnu")
                .withBudget(50000)
                .build();

        // adding all travelPackages
        travelAgency1.addTravelPackage(travelPackage1);
        travelAgency1.addTravelPackage(travelPackage2);
        travelAgency1.addTravelPackage(travelPackage3);
        // adding all clients
        travelAgency1.addClient(client1);
        travelAgency1.addClient(client2);
        travelAgency1.addClient(client3);
        // travelPackage 1 is bought 3 times
        // travelPackage 2 is bought 2 times
        // travelPackage 3 is bought 1 time
        client1.buyTravelPackage(travelAgency1, travelPackage1);
        client2.buyTravelPackage(travelAgency1, travelPackage1);
        client2.buyTravelPackage(travelAgency1, travelPackage2);
        client3.buyTravelPackage(travelAgency1, travelPackage1);
        client3.buyTravelPackage(travelAgency1, travelPackage2);
        client3.buyTravelPackage(travelAgency1, travelPackage3);

        // the getter will have the most popular as a list of travelPackages that were bought in
        // descending order by the times they were bought.
        Map.Entry<TravelPackage, Integer> entry1 = new AbstractMap.SimpleEntry<>(travelPackage1, 3);
        Map.Entry<TravelPackage, Integer> entry2 = new AbstractMap.SimpleEntry<>(travelPackage2, 2);
        Map.Entry<TravelPackage, Integer> entry3 = new AbstractMap.SimpleEntry<>(travelPackage3, 1);
        List<Map.Entry<TravelPackage, Integer>> expectedList = new ArrayList<>();
        expectedList.add(entry1);
        expectedList.add(entry2);
        expectedList.add(entry3);
        Assertions.assertEquals(expectedList, travelAgency1.mostPopularPackages());
    }
}
