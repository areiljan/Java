package ee.taltech.iti0202.travelagency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerBuilderTest {

    @Test
    void setId() {
        CustomerBuilder builder = new CustomerBuilder();
        Customer customer = builder.setId("1").build();
        assertEquals("1", customer.getId());
    }

    @Test
    void setName() {
        CustomerBuilder builder = new CustomerBuilder();
        Customer customer = builder.setName("Kati").build();
        assertEquals("Kati", customer.getName());
    }

    @Test
    void setEmail() {
        CustomerBuilder builder = new CustomerBuilder();
        Customer customer = builder.setEmail("kati@gmail.com").build();
        assertEquals("kati@gmail.com", customer.getEmail());
    }

    @Test
    void setAge() {
        CustomerBuilder builder = new CustomerBuilder();
        Customer customer = builder.setAge(30).build();
        assertEquals(30, customer.getAge());
    }

    @Test
    void setBudget() {
        CustomerBuilder builder = new CustomerBuilder();
        Customer customer = builder.setBudget(2000).build();
        assertEquals(2000, customer.getBudget());
    }

    @Test
    void build() {
        CustomerBuilder builder = new CustomerBuilder();
        Customer customer = builder.setId("1")
                .setName("Kati")
                .setEmail("kati@gmail.com")
                .setAge(30)
                .setBudget(2000)
                .build();
        assertNotNull(customer);
        assertEquals("1", customer.getId());
        assertEquals("Kati", customer.getName());
        assertEquals("kati@gmail.com", customer.getEmail());
        assertEquals(30, customer.getAge());
        assertEquals(2000, customer.getBudget());
    }
}
