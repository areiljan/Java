package ee.taltech.iti0202.travelagency.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientBuilderTest {

    @Test
    void createClient_validateAllDataIsCorrect() {
        Client client = new ClientBuilder()
                .setIdCode("1234")
                .setAge(26)
                .setName("Peeter")
                .setEmail("peeter@gmail.com")
                .createClient();

        client.setBudget(10000);

        assertEquals("1234", client.getIdCode());
        assertEquals(26, client.getAge());
        assertEquals("Peeter", client.getName());
        assertEquals("peeter@gmail.com", client.getEmail());
        assertEquals(10000, client.getBudget());
    }
}
