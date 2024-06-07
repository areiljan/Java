package ee.taltech.iti0202.travelagency.travelpackage;

import ee.taltech.iti0202.travelagency.Activities;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a holiday package.
 */
public class HolidayPackage extends BaseTravelPackage implements TravelPackage {
    /**
     * Instantiates a new Vacation package.
     *
     * @param id        the id
     * @param name      the name
     * @param startDate the start date
     * @param endDate   the end date
     * @param country   the country
     * @param basePrice the base price
     */
    public HolidayPackage(
            String id,
            String name,
            LocalDate startDate,
            LocalDate endDate,
            String country,
            int basePrice
    ) {
        super(id, name, startDate, endDate, country, basePrice, HolidayPackage.class.getName());

        this.activities = List.of(
                Activities.SUNSET_ROMANCE,
                Activities.WINE_TASTING
        );
    }
}
