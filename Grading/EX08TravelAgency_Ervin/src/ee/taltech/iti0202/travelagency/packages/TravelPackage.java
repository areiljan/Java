package ee.taltech.iti0202.travelagency.packages;

import java.time.LocalDate;

public class TravelPackage {
    private int id;
    private String name;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String country;
    private TravelPackageType type;

    /**
     * @param id Id
     * @param name Name
     * @param price Price
     * @param startDate StartDate
     * @param endDate EndDate
     * @param country Country
     * @param type Type
     * Used to also have a list of activities however needed to be removed due to a style error (too many parameters in constructor)
     *             (doesn't really change anything since the activities were not required to be used in any way).
     */
    public TravelPackage(int id, String name, double price, LocalDate startDate, LocalDate endDate,
                         String country, TravelPackageType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.type = type;
    }

    public long getDuration() {
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getCountry() {
        return country;
    }

    public TravelPackageType getType() {
        return type;
    }


    public void setPrice(double price) {
        this.price = price;
    }

}
