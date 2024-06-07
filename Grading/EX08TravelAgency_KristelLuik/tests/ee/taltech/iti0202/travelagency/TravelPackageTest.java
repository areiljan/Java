package ee.taltech.iti0202.travelagency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelPackageTest {

    private TravelPackage travelPackage;

    @BeforeEach
    void setUp() {
        travelPackage = new TravelPackageBuilder()
                .setId("1")
                .setName("Beach Vacation")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(Arrays.asList("Scuba Diving"))
                .build();
    }

    @Test
    void getId() {
        assertEquals("1", travelPackage.getId());
    }

    @Test
    void getName() {
        assertEquals("Beach Vacation", travelPackage.getName());
    }

    @Test
    void getPrice() {
        assertEquals(1000, travelPackage.getPrice());
    }

    @Test
    void getStartDate() {
        assertEquals("2024-06-01", travelPackage.getStartDate());
    }

    @Test
    void getEndDate() {
        assertEquals("2024-06-07", travelPackage.getEndDate());
    }

    @Test
    void getCountry() {
        assertEquals("Maldives", travelPackage.getCountry());
    }

    @Test
    void getType() {
        assertEquals("Beach", travelPackage.getType());
    }

    @Test
    void getActivities() {
        List<String> activities = Arrays.asList("Scuba Diving");
        assertEquals(activities, travelPackage.getActivities());
    }
}
