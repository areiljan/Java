package ee.taltech.iti0202.travelagency.travelpackage;

import java.time.LocalDate;

public class TravelPackage {
    private final String id;
    private final String name;
    private final int price;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String country;


    /**
     * TravelPackage constructor.
     * @param id - travelPackage id.
     * @param name - travelPackage name.
     * @param price - travelPackage price.
     * @param startDate - travelPackage starting date.
     * @param endDate - travelPackage ending date.
     * @param country - travelPackage country.
     * @param travelType - travelPackage type of travel.
     */
    public TravelPackage(String id, String name, int price, LocalDate startDate, LocalDate endDate, String country, TravelType travelType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
    }

    /**
     * Name getter.
     * @return - TravelPackage name as string.
     */
    public String getName() {
        return name;
    }

    /**
     * Price getter.
     * @return - price as integer.
     */
    public int getPrice() {
        return price;
    }

    /**
     * StartDate getter.
     * @return - StartDate as LocalDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * EndDate getter.
     * @return - EndDate as LocalDate.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    public enum TravelType {
        BEACH, CULTURE, HIKING
    }
}
