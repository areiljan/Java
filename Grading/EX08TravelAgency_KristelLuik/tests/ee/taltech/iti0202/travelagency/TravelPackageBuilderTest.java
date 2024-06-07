package ee.taltech.iti0202.travelagency;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelPackageBuilderTest {

    @Test
    void setId() {
        // Create a builder and set the ID
        TravelPackageBuilder builder = new TravelPackageBuilder();
        builder.setId("1");

        // Build the TravelPackage
        TravelPackage travelPackage = builder.build();

        // Check if the ID was set correctly
        assertEquals("1", travelPackage.getId());
    }

    @Test
    void setName() {
        TravelPackageBuilder builder = new TravelPackageBuilder();
        TravelPackage travelPackage = builder.setName("Beach Vacation").build();
        assertEquals("Beach Vacation", travelPackage.getName());
    }

    @Test
    void setPrice() {
        TravelPackageBuilder builder = new TravelPackageBuilder();
        TravelPackage travelPackage = builder.setPrice(1000).build();
        assertEquals(1000, travelPackage.getPrice());
    }

    @Test
    void setStartDate() {
        TravelPackageBuilder builder = new TravelPackageBuilder();
        TravelPackage travelPackage = builder.setStartDate("2024-06-01").build();
        assertEquals("2024-06-01", travelPackage.getStartDate());
    }

    @Test
    void setEndDate() {
        TravelPackageBuilder builder = new TravelPackageBuilder();
        TravelPackage travelPackage = builder.setEndDate("2024-06-07").build();
        assertEquals("2024-06-07", travelPackage.getEndDate());
    }

    @Test
    void setCountry() {
        TravelPackageBuilder builder = new TravelPackageBuilder();
        TravelPackage travelPackage = builder.setCountry("Maldives").build();
        assertEquals("Maldives", travelPackage.getCountry());
    }

    @Test
    void setType() {
        TravelPackageBuilder builder = new TravelPackageBuilder();
        TravelPackage travelPackage = builder.setType("Beach").build();
        assertEquals("Beach", travelPackage.getType());
    }

    @Test
    void setActivities() {
        TravelPackageBuilder builder = new TravelPackageBuilder();
        List<String> activities = new ArrayList<>(Arrays.asList("Scuba Diving", "Snorkeling"));
        TravelPackage travelPackage = builder.setActivities(activities).build();
        assertEquals(activities, travelPackage.getActivities());
    }

    @Test
    void build() {
        TravelPackageBuilder builder = new TravelPackageBuilder();
        TravelPackage travelPackage = builder.setId("1")
                .setName("Beach Vacation")
                .setPrice(1000)
                .setStartDate("2024-06-01")
                .setEndDate("2024-06-07")
                .setCountry("Maldives")
                .setType("Beach")
                .setActivities(Arrays.asList("Scuba Diving", "Snorkeling"))
                .build();
        assertNotNull(travelPackage);
        assertEquals("1", travelPackage.getId());
        assertEquals("Beach Vacation", travelPackage.getName());
        assertEquals(1000, travelPackage.getPrice());
        assertEquals("2024-06-01", travelPackage.getStartDate());
        assertEquals("2024-06-07", travelPackage.getEndDate());
        assertEquals("Maldives", travelPackage.getCountry());
        assertEquals("Beach", travelPackage.getType());
        assertEquals(Arrays.asList("Scuba Diving", "Snorkeling"), travelPackage.getActivities());
    }
}
