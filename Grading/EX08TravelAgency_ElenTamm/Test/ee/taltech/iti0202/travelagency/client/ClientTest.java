package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.DataGeneratorTest;
import ee.taltech.iti0202.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;
import ee.taltech.iti0202.travelagency.travelpackage.UnknownClientStatusException;
import ee.taltech.iti0202.travelagency.travelpackage.HolidayPackage;
import ee.taltech.iti0202.travelagency.travelpackage.HikingPackage;
import ee.taltech.iti0202.travelagency.travelpackage.CulturePackage;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackageBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientTest {

    @Test
    void getPurchasedTravelPackages()
            throws CannotBuyTravelPackageException, UnknownClientStatusException {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();
        List<Client> client = DataGeneratorTest.createClients(1);

        CulturePackage culturePackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createCulturePackage();
        HolidayPackage holidayPackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createHolidayPackage();
        HikingPackage hikingPackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createHikingPackage();
        Client client1 = client.get(0);
        travelAgency.buyTravelPackage(client1, culturePackage);
        travelAgency.buyTravelPackage(client1, holidayPackage);
        travelAgency.buyTravelPackage(client1, hikingPackage);

        List<TravelPackage> expected = List.of(culturePackage, holidayPackage, hikingPackage);

        assertEquals(expected, client1.getPurchasedTravelPackages());
        assertEquals(3, client1.getPurchasedTravelPackages().size());
    }

    @Test
    void getClientStatus_purchasedSamePackage() throws CannotBuyTravelPackageException, UnknownClientStatusException {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();
        List<Client> clients = DataGeneratorTest.createClients(3);

        CulturePackage culturePackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createCulturePackage();

        Client client1 = clients.get(0);
        Client client2 = clients.get(1);
        Client client3 = clients.get(2);

        travelAgency.buyTravelPackage(client1, culturePackage);
        travelAgency.buyTravelPackage(client1, culturePackage);

        travelAgency.buyTravelPackage(client2, culturePackage);
        travelAgency.buyTravelPackage(client2, culturePackage);
        travelAgency.buyTravelPackage(client2, culturePackage);

        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, culturePackage);


        assertEquals(ClientStatus.REGULAR_CUSTOMER, client1.getStatus());
        assertEquals(ClientStatus.SILVER_CUSTOMER, client2.getStatus());
        assertEquals(ClientStatus.GOLD_CUSTOMER, client3.getStatus());
    }

    @Test
    void getClientStatus_purchaseDifferentPackages()
            throws CannotBuyTravelPackageException, UnknownClientStatusException {
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();
        List<Client> clients = DataGeneratorTest.createClients(3);

        CulturePackage culturePackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createCulturePackage();
        HolidayPackage holidayPackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createHolidayPackage();
        HikingPackage hikingPackage = DataGeneratorTest
                .createTravelPackageBuilder()
                .createHikingPackage();

        Client client1 = clients.get(0);
        Client client2 = clients.get(1);
        Client client3 = clients.get(2);

        travelAgency.buyTravelPackage(client1, hikingPackage);
        travelAgency.buyTravelPackage(client1, culturePackage);

        travelAgency.buyTravelPackage(client2, culturePackage);
        travelAgency.buyTravelPackage(client2, hikingPackage);
        travelAgency.buyTravelPackage(client2, holidayPackage);

        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, hikingPackage);
        travelAgency.buyTravelPackage(client3, holidayPackage);
        travelAgency.buyTravelPackage(client3, culturePackage);
        travelAgency.buyTravelPackage(client3, holidayPackage);


        assertEquals(ClientStatus.REGULAR_CUSTOMER, client1.getStatus());
        assertEquals(ClientStatus.SILVER_CUSTOMER, client2.getStatus());
        assertEquals(ClientStatus.GOLD_CUSTOMER, client3.getStatus());
    }

    @Test
    void canBuyPackage_enoughMoney() throws CannotBuyTravelPackageException, UnknownClientStatusException {
        Client client = new ClientBuilder()
                .setIdCode("1234")
                .setAge(35)
                .setName("Pille")
                .setEmail("pille@gmail.com")
                .createClient();

        client.setBudget(500);

        TravelPackageBuilder travelPackageBuilder = DataGeneratorTest.createTravelPackageBuilder(
                100
        );

        HikingPackage hikingPackage = travelPackageBuilder.createHikingPackage();
        assertTrue(client.canBuyPackage(hikingPackage));
    }

    @Test
    void notEnoughMoney_cannotBuyPackage() throws CannotBuyTravelPackageException, UnknownClientStatusException {
        Client client = new ClientBuilder()
                .setIdCode("1234")
                .setAge(35)
                .setName("Pille")
                .setEmail("pille@gmail.com")
                .createClient();

        client.setBudget(500);

        TravelPackageBuilder travelPackageBuilder = DataGeneratorTest.createTravelPackageBuilder(
                1000
        );

        HikingPackage hikingPackage = travelPackageBuilder.createHikingPackage();
        CannotBuyTravelPackageException exception = assertThrows(CannotBuyTravelPackageException.class,
                () -> client.canBuyPackage(hikingPackage)
        );
        assertTrue(exception.getMessage().contains("There are no funds to purchase a travel package!")
        );
        assertTrue(client.getPurchasedTravelPackages().isEmpty());
    }
}
