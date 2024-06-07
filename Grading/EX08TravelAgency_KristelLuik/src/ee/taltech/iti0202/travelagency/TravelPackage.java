package ee.taltech.iti0202.travelagency;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TravelPackage {
    private String id;
    private String name;
    private double price;
    private String startDate;
    private String endDate;
    private String country;
    private String type;
    private List<String> activities;


    /**
     * Constructor for travel package
     */
    public TravelPackage() { }

    /**
     * @param id int
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param name string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price int
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param startDate for start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @param endDate for end date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @param country string
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @param type string
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param activities for activities
     */
    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
    /**
     * Get id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get prive
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get duration
     * @return duration
     */
    public int getDuration() {
        // Parse start and end dates
        LocalDate startDateObj = LocalDate.parse(startDate);
        LocalDate endDateObj = LocalDate.parse(endDate);

        // Calculate duration in days
        long daysBetween = ChronoUnit.DAYS.between(startDateObj, endDateObj);

        // Convert to int and return
        return Math.toIntExact(daysBetween);
    }

    /**
     * @return start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @return end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @return activities
     */
    public List<String> getActivities() {
        return activities;
    }

}
