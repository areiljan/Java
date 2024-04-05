package ee.taltech.iti0202.travelagency.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.client.ClientBuilder;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

class TravelAgencyTest {

    @org.junit.jupiter.api.Test
    void clientBuilderAgeNegativeThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ClientBuilder("5403039203", "Priit", "priittoobal@gmail.com", -15);
        });
    }

    @org.junit.jupiter.api.Test
    void travelAgencyAddClientsCanGetFromAgency() {
        TravelAgency travelAgency1 = new TravelAgency("Reisihunt");
        Client client1 = new ClientBuilder()
                .idCode("3493943")
                .name("Fred")
                .email("fredpeep@gmail.com")
                .age(10)
                .withPhoneNumber("+372 9932 2039")
                .withCity("Narva")
                .withBudget(100000)
                .build();

        travelAgency1.addClient(client1);

        List<Client> expectedClients = new ArrayList<Client>();
        expectedClients.add(client1);
        Assertions.assertEquals(expectedClients, travelAgency1.getClients());
    }

    @org.junit.jupiter.api.Test
    void addTravelPackage() {
    }

    @org.junit.jupiter.api.Test
    void topClients() {
    }

    @org.junit.jupiter.api.Test
    void mostPopularPackages() {
    }
}