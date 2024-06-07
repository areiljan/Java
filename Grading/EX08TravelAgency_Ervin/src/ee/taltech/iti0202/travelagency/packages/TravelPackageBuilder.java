package ee.taltech.iti0202.travelagency.packages;

import java.time.LocalDate;
import java.util.List;

public class TravelPackageBuilder {
    private int id;
    private String name;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String country;
    private TravelPackageType type;
    private List<String> activities;

    /**
     * @param id Id
     * @return This
     */
    public TravelPackageBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @param name Name
     * @return This
     */
    public TravelPackageBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param price Price
     * @return This
     */
    public TravelPackageBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * @param startDate StartDate
     * @return This
     */
    public TravelPackageBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * @param endDate EndDate
     * @return This
     */
    public TravelPackageBuilder setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * @param country Country
     * @return This
     */
    public TravelPackageBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * @param type Type
     * @return This
     */
    public TravelPackageBuilder setType(TravelPackageType type) {
        this.type = type;
        return this;
    }

    /**
     * @param activities Activities
     * @return This
     */
    public TravelPackageBuilder setActivities(List<String> activities) {
        this.activities = activities;
        return this;
    }

    /**
     * @return TravelPackage
     */
    public TravelPackage createTravelPackage() {
        return new TravelPackage(id, name, price, startDate, endDate, country, type);
    }
}
