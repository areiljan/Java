package ee.taltech.iti0202.travelagency.travelpackage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HikingPackageTest {
    @Test
    void hikingPackage() {
        HikingPackage hikingPackage = new HikingPackage(
                "258",
                "A real vacation",
                LocalDate.of(2024, 5, 10),
                LocalDate.of(2024, 5, 17),
                "Malta",
                1000
        );
        assertEquals(1000, hikingPackage.getPrice());
        assertEquals("Malta", hikingPackage.getCountry());
        assertEquals("258", hikingPackage.getId());
        assertEquals("A real vacation", hikingPackage.getName());
        assertEquals(LocalDate.of(2024, 5, 10), hikingPackage.getStartDate());
        assertEquals(LocalDate.of(2024, 5, 17), hikingPackage.getEndDate());
    }
}
