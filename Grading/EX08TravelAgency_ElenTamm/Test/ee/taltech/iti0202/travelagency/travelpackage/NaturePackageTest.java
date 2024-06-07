package ee.taltech.iti0202.travelagency.travelpackage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NaturePackageTest {
    @Test
    void naturePackage() {
        NaturePackage naturePackage = new NaturePackage(
                "258",
                "A real vacation",
                LocalDate.of(2024, 5, 10),
                LocalDate.of(2024, 5, 17),
                "Malta",
                1000
        );
        assertEquals(1000, naturePackage.getPrice());
        assertEquals("Malta", naturePackage.getCountry());
        assertEquals("258", naturePackage.getId());
        assertEquals("A real vacation", naturePackage.getName());
        assertEquals(LocalDate.of(2024, 5, 10), naturePackage.getStartDate());
        assertEquals(LocalDate.of(2024, 5, 17), naturePackage.getEndDate());
    }
}
