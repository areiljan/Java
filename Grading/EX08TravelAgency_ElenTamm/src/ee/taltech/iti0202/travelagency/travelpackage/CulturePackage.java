package ee.taltech.iti0202.travelagency.travelpackage;

import ee.taltech.iti0202.travelagency.Activities;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a culture package.
 */
public class CulturePackage extends BaseTravelPackage implements TravelPackage {
    /**
     * Instantiates a new Vacation package.
     *
     * @param id        the id
     * @param name      the name
     * @param startDate the start date
     * @param endDate   the end date
     * @param country   the country
     */
    public CulturePackage(
            String id,
            String name,
            LocalDate startDate,
            LocalDate endDate,
            String country,
            int basePrice
    ) {
        super(id, name, startDate, endDate, country, basePrice, CulturePackage.class.getName());

        this.activities = List.of(
                Activities.CITY_TOUR,
                Activities.VISITING_MUSEUMS,
                Activities.WINE_TASTING
        );
    }
}
