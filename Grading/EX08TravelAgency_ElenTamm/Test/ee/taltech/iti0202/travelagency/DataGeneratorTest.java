package ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.client.ClientBuilder;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackageBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Test data generator.
 */
public class DataGeneratorTest {
    /**
     * Create clients list.
     *
     * @param count the count
     * @return the list
     */
    public static List<Client> createClients(int count) {
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Client client = new ClientBuilder()
                    .setIdCode(Integer.toString(i))
                    .setAge(26)
                    .setName("Peeter")
                    .setEmail("peeter@gmail.com")
                    .createClient();

            client.setBudget(10000);
            clients.add(client);
        }
        return clients;
    }

    /**
     * Create client client.
     *
     * @param name the name
     * @return the client
     */
    public static Client createClient(String name) {
        Client client = new ClientBuilder()
                .setIdCode("1234")
                .setAge(26)
                .setName(name)
                .setEmail("peeter@gmail.com")
                .createClient();

        client.setBudget(10000);

        return client;
    }

    /**
     * Create travel agency.
     *
     * @return the travel agency
     */
    public static TravelAgency createTravelAgency() {
        return new TravelAgency();
    }

    /**
     * Create travel package builder travel package builder.
     *
     * @return the travel package builder
     */
    public static TravelPackageBuilder createTravelPackageBuilder() {
        return new TravelPackageBuilder()
                .setPrice(1000)
                .setCountry("Italy")
                .setId("558")
                .setName("Nice days")
                .setStartDate(LocalDate.of(2024, 5, 10))
                .setEndDate(LocalDate.of(2024, 5, 25));
    }

    /**
     * Create travel package builder travel package builder.
     *
     * @param price the price
     * @return the travel package builder
     */
    public static TravelPackageBuilder createTravelPackageBuilder(int price) {
        return new TravelPackageBuilder()
                .setPrice(price)
                .setCountry("Italy")
                .setId("558")
                .setName("Nice days")
                .setStartDate(LocalDate.of(2024, 5, 10))
                .setEndDate(LocalDate.of(2024, 5, 25));
    }
}
