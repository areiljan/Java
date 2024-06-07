package ee.taltech.iti0202.travelagency.travelpackage;


import ee.taltech.iti0202.travelagency.ApplicationLogger;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Travel package builder.
 */
public class TravelPackageBuilder {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String country;
    private int price;
    Logger logger = ApplicationLogger.getLogger(TravelPackageBuilder.class.getName());

    /**
     * Sets price.
     *
     * @param price the price
     */
    public TravelPackageBuilder setPrice(int price) {
        logger.log(Level.INFO, "Setting travel package builder price");
        this.price = price;
        return this;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public TravelPackageBuilder setId(String id) {
        logger.log(Level.INFO, "Setting travel package builder id");
        this.id = id;
        return this;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public TravelPackageBuilder setName(String name) {
        logger.log(Level.INFO, "Setting travel package builder name");
        this.name = name;
        return this;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     * @return the start date
     */
    public TravelPackageBuilder setStartDate(LocalDate startDate) {
        logger.log(Level.INFO, "Setting travel package builder start date");
        this.startDate = startDate;
        return this;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     * @return the end date
     */
    public TravelPackageBuilder setEndDate(LocalDate endDate) {
        logger.log(Level.INFO, "Setting travel package builder end date");
        this.endDate = endDate;
        return this;
    }

    /**
     * Sets country.
     *
     * @param country the country
     * @return the country
     */
    public TravelPackageBuilder setCountry(String country) {
        logger.log(Level.INFO, "Setting travel package builder country");
        this.country = country;
        return this;
    }

    /**
     * Create travel package.
     *
     * @return the travel package
     */
    public CulturePackage createCulturePackage() {
        logger.log(Level.INFO, "Building culture package");
        return new CulturePackage(id, name, startDate, endDate, country, price);
    }

    /**
     * Create hiking package hiking package.
     *
     * @return the hiking package
     */
    public HikingPackage createHikingPackage() {
        logger.log(Level.INFO, "Building hiking package");
        return new HikingPackage(id, name, startDate, endDate, country, price);
    }

    /**
     * Create nature package.
     *
     * @return the nature package
     */
    public NaturePackage createNaturePackage() {
        logger.log(Level.INFO, "Building nature package");
        return new NaturePackage(id, name, startDate, endDate, country, price);
    }

    /**
     * Create holiday package.
     *
     * @return the holiday package
     */
    public HolidayPackage createHolidayPackage() {
        logger.log(Level.INFO, "Building holiday package");
        return new HolidayPackage(id, name, startDate, endDate, country, price);
    }
}
