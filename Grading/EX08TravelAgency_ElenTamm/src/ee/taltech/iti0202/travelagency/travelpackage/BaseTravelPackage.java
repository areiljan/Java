package ee.taltech.iti0202.travelagency.travelpackage;

import ee.taltech.iti0202.travelagency.Activities;
import ee.taltech.iti0202.travelagency.ApplicationLogger;
import ee.taltech.iti0202.travelagency.client.ClientStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a travel package.
 */
public class BaseTravelPackage implements TravelPackage {
    /**
     * The constant REGULAR_DISCOUNT.
     */
    public static final double REGULAR_DISCOUNT = 0.95;
    /**
     * The constant SILVER_DISCOUNT.
     */
    public static final double SILVER_DISCOUNT = 0.9;
    /**
     * The constant GOLD_DISCOUNT.
     */
    public static final double GOLD_DISCOUNT = 0.85;
    private final String id;
    private final String name;
    private final int price;
    private LocalDate startDate;
    private LocalDate endDate;
    private final String country;
    /**
     * The Activities.
     */
    protected List<Activities> activities = new ArrayList<>();
    /**
     * The Logger.
     */
    Logger logger;

    /**
     * Instantiates a new Vacation package.
     *
     * @param id         the id
     * @param name       the name
     * @param startDate  the start date
     * @param endDate    the end date
     * @param country    the country
     * @param basePrice  the base price
     * @param loggerName the logger name
     */
    public BaseTravelPackage(
            String id,
            String name,
            LocalDate startDate,
            LocalDate endDate,
            String country,
            int basePrice,
            String loggerName) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.price = basePrice;
        this.logger = ApplicationLogger.getLogger(loggerName);
    }

    /**
     * Returns the id of this travel package.
     *
     * @return the id
     */
    public String getId() {
        logger.log(Level.INFO, "Getting travel package ID");
        return id;
    }

    /**
     * Returns the price this travel package.
     *
     * @return the price
     */
    public int getPrice() {
        logger.log(Level.INFO, "Getting travel package price");
        return price;
    }

    /**
     * Returns the country this travel package.
     *
     * @return the country
     */
    public String getCountry() {
        logger.log(Level.INFO, "Getting travel package country");
        return country;
    }

    /**
     * Returns the name this travel package.
     *
     * @return the name
     */
    public String getName() {
        logger.log(Level.INFO, "Getting travel package name");
        return name;
    }

    /**
     * Returns the price this travel package.
     *
     * @param status the status
     * @return the price of the travel package.
     * @throws UnknownClientStatusException the unknown client status exception
     */
    public double getPrice(ClientStatus status) throws UnknownClientStatusException {
        logger.log(Level.INFO, "Getting travel package price");
        double lengthInDays = ChronoUnit.DAYS.between(this.startDate, this.endDate);
        switch (status) {
            case REGULAR_CUSTOMER:
                if (lengthInDays >= 10) {
                    return price * REGULAR_DISCOUNT;
                }
                break;
            case SILVER_CUSTOMER:
                if (lengthInDays >= 10) {
                    return price * SILVER_DISCOUNT;
                }
                break;
            case GOLD_CUSTOMER:
                if (lengthInDays >= 10) {
                    return price * GOLD_DISCOUNT;
                }
                break;
            default:
                throw new UnknownClientStatusException("Unknown client status " + status);
        }
        return price;
    }

    /**
     * Returns the start date this travel package.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        logger.log(Level.INFO, "Getting travel package start date");
        return startDate;
    }

    /**
     * Returns the end date this travel package.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        logger.log(Level.INFO, "Getting travel package end date");
        return endDate;
    }
}
