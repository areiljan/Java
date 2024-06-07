package ee.taltech.iti0202.travelagency.travelpackage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HolidayPackageTest {
    @Test
    void holidayPackage() {
        HolidayPackage holidayPackage = new HolidayPackage(
                "258",
                "A real vacation",
                LocalDate.of(2024, 5, 10),
                LocalDate.of(2024, 5, 17),
                "Malta",
                1000
        );
        assertEquals(1000, holidayPackage.getPrice());
        assertEquals("Malta", holidayPackage.getCountry());
        assertEquals("258", holidayPackage.getId());
        assertEquals("A real vacation", holidayPackage.getName());
        assertEquals(LocalDate.of(2024, 5, 10), holidayPackage.getStartDate());
        assertEquals(LocalDate.of(2024, 5, 17), holidayPackage.getEndDate());
    }
}
