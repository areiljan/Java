package ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.client.CannotBuyTravelPackageException;
import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.travelpackage.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TravelAgencyTest {

    @Test
    void clientBuysTravelPackage_buyingSuccessful()
            throws CannotBuyTravelPackageException, UnknownClientStatusException {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();
        List<Client> clients = DataGeneratorTest.createClients(1);

        HikingPackage hikingPackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createHikingPackage();

        Client client1 = clients.get(0);

        assertTrue(travelAgency.buyTravelPackage(client1, hikingPackage));
        assertEquals(1, client1.getPurchasedTravelPackages().size());

        travelAgency.buyTravelPackage(client1, hikingPackage);

        assertEquals(2, client1.getPurchasedTravelPackages().size());
        assertEquals(2, travelAgency.getOrders().size());
        assertTrue(client1.getPurchasedTravelPackages().contains(hikingPackage));
    }

    @Test
    void getTop3Packages_retrieveAllSuccessfully()
            throws CannotBuyTravelPackageException, UnknownClientStatusException {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();
        Client client = DataGeneratorTest.createClients(1).get(0);
        TravelPackageBuilder travelPackageBuilder = DataGeneratorTest.createTravelPackageBuilder();

        CulturePackage culturePackage = travelPackageBuilder.createCulturePackage();
        HikingPackage hikingPackage = travelPackageBuilder.createHikingPackage();
        HolidayPackage holidayPackage = travelPackageBuilder.createHolidayPackage();
        NaturePackage naturePackage = travelPackageBuilder.createNaturePackage();

        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);

        travelAgency.buyTravelPackage(client, hikingPackage);
        travelAgency.buyTravelPackage(client, hikingPackage);
        travelAgency.buyTravelPackage(client, hikingPackage);
        travelAgency.buyTravelPackage(client, hikingPackage);

        travelAgency.buyTravelPackage(client, holidayPackage);
        travelAgency.buyTravelPackage(client, holidayPackage);
        travelAgency.buyTravelPackage(client, holidayPackage);
        travelAgency.buyTravelPackage(client, holidayPackage);

        travelAgency.buyTravelPackage(client, naturePackage);

        List<TravelPackage> expectedTopPackages = List.of(holidayPackage, hikingPackage, culturePackage);
        assertEquals(expectedTopPackages, travelAgency.getTop3Packages());
        assertEquals(11, client.getPurchasedTravelPackages().size());
    }

    @Test
    void getTop3Packages_twoPackagesSold() throws CannotBuyTravelPackageException, UnknownClientStatusException {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();
        Client client = DataGeneratorTest.createClients(1).get(0);
        TravelPackageBuilder travelPackageBuilder = DataGeneratorTest.createTravelPackageBuilder();

        CulturePackage culturePackage = travelPackageBuilder.createCulturePackage();
        HikingPackage hikingPackage = travelPackageBuilder.createHikingPackage();

        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);

        travelAgency.buyTravelPackage(client, hikingPackage);
        travelAgency.buyTravelPackage(client, hikingPackage);
        travelAgency.buyTravelPackage(client, hikingPackage);

        List<TravelPackage> expectedTopPackages = List.of(hikingPackage, culturePackage);
        assertEquals(expectedTopPackages, travelAgency.getTop3Packages());
        assertEquals(5, client.getPurchasedTravelPackages().size());
    }

    @Test
    void getTop3Packages_noPackagesPurchased() {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();

        List<Package> expectedTopPackages = List.of();
        assertEquals(expectedTopPackages, travelAgency.getTop3Packages());
    }


    @Test
    void getTop3Clients_retrieveAllSuccessfully() throws CannotBuyTravelPackageException, UnknownClientStatusException {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();
        List<Client> clients = DataGeneratorTest.createClients(5);

        CulturePackage culturePackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createCulturePackage();

        Client client1 = DataGeneratorTest.createClient("Client 1");
        Client client2 = DataGeneratorTest.createClient("Client 2");
        Client client3 = DataGeneratorTest.createClient("Client 3");
        Client client4 = DataGeneratorTest.createClient("Client 4");
        Client client5 = DataGeneratorTest.createClient("Client 5");

        travelAgency.buyTravelPackage(client1, culturePackage);
        travelAgency.buyTravelPackage(client1, culturePackage);

        travelAgency.buyTravelPackage(client2, culturePackage);
        travelAgency.buyTravelPackage(client2, culturePackage);
        travelAgency.buyTravelPackage(client2, culturePackage);

        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, culturePackage);

        travelAgency.buyTravelPackage(client4, culturePackage);

        travelAgency.buyTravelPackage(client5, culturePackage);
        travelAgency.buyTravelPackage(client5, culturePackage);
        travelAgency.buyTravelPackage(client5, culturePackage);
        travelAgency.buyTravelPackage(client5, culturePackage);

        List<Client> expectedTopClients = List.of(client5, client3, client2);
        assertEquals(expectedTopClients, travelAgency.getTop3Clients());
        assertTrue(client2.getPurchasedTravelPackages().contains(culturePackage));
        assertEquals(4, client5.getPurchasedTravelPackages().size());
    }

    @Test
    void getTop3Clients_thereAreNoClients() {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();

        List<Client> expectedTopClients = List.of();
        assertEquals(expectedTopClients, travelAgency.getTop3Clients());
        assertTrue(travelAgency.getTop3Clients().isEmpty());
    }

    @Test
    void getTop3Clients_thereAreOnlyTwoClients() throws CannotBuyTravelPackageException, UnknownClientStatusException {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();
        List<Client> clients = DataGeneratorTest.createClients(2);

        CulturePackage culturePackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createCulturePackage();

        Client client1 = DataGeneratorTest.createClient("Client 1");
        Client client2 = DataGeneratorTest.createClient("Client 2");

        travelAgency.buyTravelPackage(client1, culturePackage);
        travelAgency.buyTravelPackage(client1, culturePackage);

        travelAgency.buyTravelPackage(client2, culturePackage);
        travelAgency.buyTravelPackage(client2, culturePackage);
        travelAgency.buyTravelPackage(client2, culturePackage);

        List<Client> expectedTopClients = List.of(client2, client1);
        assertEquals(expectedTopClients, travelAgency.getTop3Clients());
        assertEquals(2, travelAgency.getTop3Clients().size());
    }
}
