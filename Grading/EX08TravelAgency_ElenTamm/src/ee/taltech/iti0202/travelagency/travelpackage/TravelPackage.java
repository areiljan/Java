package ee.taltech.iti0202.travelagency.travelpackage;

import ee.taltech.iti0202.travelagency.client.ClientStatus;

import java.time.LocalDate;

/**
 * The interface TravelPackage.
 */
public interface TravelPackage {
    /**
     * Returns the id of this travel package.
     *
     * @return the id
     */
    String getId();

    /**
     * Returns the price this travel package.
     *
     * @return the price
     */
    int getPrice();

    /**
     * Returns the country this travel package.
     *
     * @return the country
     */
    String getCountry();

    /**
     * Returns the name this travel package.
     *
     * @return the name
     */
    String getName();

    /**
     * Returns the price this travel package.
     *
     * @param status the status
     * @return the price of the travel package.
     * @throws UnknownClientStatusException the unknown client status exception
     */
    double getPrice(ClientStatus status) throws UnknownClientStatusException;

    /**
     * Returns the start date this travel package.
     *
     * @return the start date
     */
    LocalDate getStartDate();

    /**
     * Returns the end date this travel package.
     *
     * @return the end date
     */
    LocalDate getEndDate();
}
