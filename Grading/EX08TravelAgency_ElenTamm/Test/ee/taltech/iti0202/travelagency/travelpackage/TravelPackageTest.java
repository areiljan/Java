package ee.taltech.iti0202.travelagency.travelpackage;

import ee.taltech.iti0202.travelagency.DataGeneratorTest;
import ee.taltech.iti0202.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.client.CannotBuyTravelPackageException;
import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.client.ClientStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TravelPackageTest {

    @Test
    void getPrice_tripIsLongerThanTenDays_moreThanFivePackagesPurchased_goldClient()
            throws UnknownClientStatusException, CannotBuyTravelPackageException {
        Client client = DataGeneratorTest.createClients(1).get(0);

        TravelPackageBuilder travelPackageBuilder = DataGeneratorTest.createTravelPackageBuilder();
        CulturePackage culturePackage = travelPackageBuilder.createCulturePackage();

        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();

        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);

        assertEquals(ClientStatus.GOLD_CUSTOMER, client.getStatus());
        assertTrue(client.getPurchasedTravelPackages().size() >= 5);
        assertEquals(850.0, culturePackage.getPrice(client.getStatus()));
    }

    @Test
    void getPrice_tripIsShorterThanTenDays_moreThanFivePackagesPurchased_goldClient()
            throws UnknownClientStatusException, CannotBuyTravelPackageException {
        TravelPackageBuilder travelPackageBuilder = new TravelPackageBuilder()
                .setPrice(1000)
                .setCountry("Malta")
                .setId("258")
                .setName("A real vacation")
                .setStartDate(LocalDate.of(2024, 5, 10))
                .setEndDate(LocalDate.of(2024, 5, 17));
        CulturePackage culturePackage = travelPackageBuilder.createCulturePackage();

        Client client = DataGeneratorTest.createClients(1).get(0);

        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();

        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);

        assertEquals(ClientStatus.GOLD_CUSTOMER, client.getStatus());
        assertTrue(client.getPurchasedTravelPackages().size() >= 5);
        assertEquals(1000.0, culturePackage.getPrice(client.getStatus()));
    }

    @Test
    void getPrice_tripIsLongerThanTenDays_exactlyThreePackagesPurchased_silverClient()
            throws UnknownClientStatusException, CannotBuyTravelPackageException {
        Client client = DataGeneratorTest.createClients(1).get(0);
        TravelPackageBuilder travelPackageBuilder = DataGeneratorTest.createTravelPackageBuilder();

        CulturePackage culturePackage = travelPackageBuilder.createCulturePackage();

        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();


        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);

        assertEquals(ClientStatus.SILVER_CUSTOMER, client.getStatus());
        assertTrue(client.getPurchasedTravelPackages().size() == 3);
        assertEquals(900.0, culturePackage.getPrice(client.getStatus()));
    }

    @Test
    void getPrice_tripIsShorterThanTenDays_fourPackagesPurchased_silverClient()
            throws UnknownClientStatusException, CannotBuyTravelPackageException {
        TravelPackageBuilder travelPackageBuilder = new TravelPackageBuilder()
                .setPrice(1000)
                .setCountry("Malta")
                .setId("258")
                .setName("A real vacation")
                .setStartDate(LocalDate.of(2024, 5, 10))
                .setEndDate(LocalDate.of(2024, 5, 17));
        CulturePackage culturePackage = travelPackageBuilder.createCulturePackage();

        Client client = DataGeneratorTest.createClients(1).get(0);

        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();

        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);
        travelAgency.buyTravelPackage(client, culturePackage);

        assertEquals(ClientStatus.SILVER_CUSTOMER, client.getStatus());
        assertTrue(client.getPurchasedTravelPackages().size() == 4);
        assertEquals(1000.0, culturePackage.getPrice(client.getStatus()));
    }

    @Test
    void getPrice_tripIsLongerThanTenDays_OnePackagePurchased_regularClient()
            throws UnknownClientStatusException, CannotBuyTravelPackageException {
        Client client = DataGeneratorTest.createClients(1).get(0);

        TravelPackageBuilder travelPackageBuilder = DataGeneratorTest.createTravelPackageBuilder();
        TravelAgency travelAgency = DataGeneratorTest.createTravelAgency();

        CulturePackage culturePackage = travelPackageBuilder.createCulturePackage();
        travelAgency.buyTravelPackage(client, culturePackage);

        assertEquals(ClientStatus.REGULAR_CUSTOMER, client.getStatus());
        assertTrue(client.getPurchasedTravelPackages().size() == 1);
        assertEquals(950.0, culturePackage.getPrice(client.getStatus()));
    }

    @Test
    void getPrice_tripIsShorterThanTenDays_firstTimeBuyingTravelPackage_regularClient()
            throws UnknownClientStatusException {
        TravelPackageBuilder travelPackageBuilder = new TravelPackageBuilder()
                .setPrice(800)
                .setCountry("Malta")
                .setId("258")
                .setName("A real vacation")
                .setStartDate(LocalDate.of(2024, 5, 10))
                .setEndDate(LocalDate.of(2024, 5, 17));
        CulturePackage culturePackage = travelPackageBuilder.createCulturePackage();
        Client client = DataGeneratorTest.createClients(1).get(0);

        assertEquals(ClientStatus.REGULAR_CUSTOMER, client.getStatus());
        assertTrue(client.getPurchasedTravelPackages().size() == 0);
        assertEquals(800.0, culturePackage.getPrice(client.getStatus()));
    }
}
