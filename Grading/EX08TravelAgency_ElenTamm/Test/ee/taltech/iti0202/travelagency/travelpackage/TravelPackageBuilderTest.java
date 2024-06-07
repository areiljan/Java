package ee.taltech.iti0202.travelagency.travelpackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelPackageBuilderTest {
    TravelPackageBuilder travelPackageBuilder;

    @BeforeEach
    void setUp() {
        travelPackageBuilder = new TravelPackageBuilder()
                .setPrice(1000)
                .setCountry("Italy")
                .setId("558")
                .setName("Nice days")
                .setStartDate(LocalDate.of(2024, 5, 10))
                .setEndDate(LocalDate.of(2024, 5, 25));

    }

    @Test
    void createCulturePackage_validateAllDataIsCorrect() {
        CulturePackage travelPackage = travelPackageBuilder.createCulturePackage();

        assertEquals(1000, travelPackage.getPrice());
        assertEquals("Italy", travelPackage.getCountry());
        assertEquals("558", travelPackage.getId());
        assertEquals("Nice days", travelPackage.getName());
        assertEquals(LocalDate.of(2024, 5, 10), travelPackage.getStartDate());
        assertEquals(LocalDate.of(2024, 5, 25), travelPackage.getEndDate());
    }


    @Test
    void createHikingPackage_validateAllDataIsCorrect() {
        HikingPackage hikingPackage = travelPackageBuilder.createHikingPackage();

        assertEquals(1000, hikingPackage.getPrice());
        assertEquals("Italy", hikingPackage.getCountry());
        assertEquals("558", hikingPackage.getId());
        assertEquals("Nice days", hikingPackage.getName());
        assertEquals(LocalDate.of(2024, 5, 10), hikingPackage.getStartDate());
        assertEquals(LocalDate.of(2024, 5, 25), hikingPackage.getEndDate());
    }


    @Test
    void createNaturePackage_validateAllDataIsCorrect() {
        NaturePackage naturePackage = travelPackageBuilder.createNaturePackage();

        assertEquals(1000, naturePackage.getPrice());
        assertEquals("Italy", naturePackage.getCountry());
        assertEquals("558", naturePackage.getId());
        assertEquals("Nice days", naturePackage.getName());
        assertEquals(LocalDate.of(2024, 5, 10), naturePackage.getStartDate());
        assertEquals(LocalDate.of(2024, 5, 25), naturePackage.getEndDate());
    }


    @Test
    void createHolidayPackage_validateAllDataIsCorrect() {
        HolidayPackage holidayPackage = travelPackageBuilder.createHolidayPackage();

        assertEquals(1000, holidayPackage.getPrice());
        assertEquals("Italy", holidayPackage.getCountry());
        assertEquals("558", holidayPackage.getId());
        assertEquals("Nice days", holidayPackage.getName());
        assertEquals(LocalDate.of(2024, 5, 10), holidayPackage.getStartDate());
        assertEquals(LocalDate.of(2024, 5, 25), holidayPackage.getEndDate());
    }
}
