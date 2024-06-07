package ee.taltech.iti0202.travelagency;

import java.util.List;

public class TravelPackageBuilder {
    private String id;
    private String name;
    private double price;
    private String startDate;
    private String endDate;
    private String country;
    private String type;
    private List<String> activities;

    /**
     * @param id int
     * @return id
     */
    public TravelPackageBuilder setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * @param name string
     * @return name
     */
    public TravelPackageBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param price int
     * @return price
     */
    public TravelPackageBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * @param startDate date
     * @return start date
     */
    public TravelPackageBuilder setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * @param endDate date
     * @return end date
     */
    public TravelPackageBuilder setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * @param country string
     * @return string country
     */
    public TravelPackageBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * @param type string
     * @return type
     */
    public TravelPackageBuilder setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @param activities list
     * @return list
     */
    public TravelPackageBuilder setActivities(List<String> activities) {
        this.activities = activities;
        return this;
    }

    /**
     * @return travel package
     */
    public TravelPackage build() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId(id);
        travelPackage.setName(name);
        travelPackage.setPrice(price);
        travelPackage.setStartDate(startDate);
        travelPackage.setEndDate(endDate);
        travelPackage.setCountry(country);
        travelPackage.setType(type);
        travelPackage.setActivities(activities);
        return travelPackage;
    }
}
