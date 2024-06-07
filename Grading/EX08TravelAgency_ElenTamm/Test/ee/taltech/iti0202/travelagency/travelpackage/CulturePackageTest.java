package ee.taltech.iti0202.travelagency.travelpackage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CulturePackageTest {
    @Test
    void culturePackage() {
        CulturePackage culturePackage = new CulturePackage(
                "258",
                "A real vacation",
                LocalDate.of(2024, 5, 10),
                LocalDate.of(2024, 5, 17),
                "Malta",
                1000
        );
        assertEquals(1000, culturePackage.getPrice());
        assertEquals("Malta", culturePackage.getCountry());
        assertEquals("258", culturePackage.getId());
        assertEquals("A real vacation", culturePackage.getName());
        assertEquals(LocalDate.of(2024, 5, 10), culturePackage.getStartDate());
        assertEquals(LocalDate.of(2024, 5, 17), culturePackage.getEndDate());
    }
}
