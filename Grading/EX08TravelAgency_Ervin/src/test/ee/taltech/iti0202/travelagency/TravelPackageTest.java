package test.ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.packages.TravelPackage;
import ee.taltech.iti0202.travelagency.packages.TravelPackageBuilder;
import ee.taltech.iti0202.travelagency.packages.TravelPackageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelPackageTest {
    private TravelPackage package1;
    @BeforeEach
    void setUp() {
        package1 = new TravelPackageBuilder().setId(1).setName("Location One").setPrice(1000).setStartDate(LocalDate.now()).setEndDate(LocalDate.now().
                plusDays(7)).setCountry("Estonia1").setType(TravelPackageType.ADVENTURE).createTravelPackage();
    }

    @Test
    void testGetDuration() {
        // Only method that might need a test.
        assertEquals(7, package1.getDuration());
    }
}
