package ee.taltech.iti0202.hotel.strategies;

import ee.taltech.iti0202.hotel.Hotel;

import java.time.LocalDate;
import java.time.Period;

public class LongBookingStrategy implements Strategy {

    public static final float MAXIMUM_DISCOUNT = 0.3f;
    public static final float STARTING_DISCOUNT = 0.15f;
    public static final float DISCOUNT_PER_ADDITIONAL_BOOKING = 0.005f;
    public static final int DISCOUNT_DELIMITER = 7;
    public static final int AVERAGE_AMOUNT_OF_DAYS_IN_YEAR = 365;
    public static final int AVERAGE_AMOUNT_OF_DAYS_IN_MONTH = 30;

    /**
     * Get discount based on the length of the booking made.
     * @param hotel - hotel to get discount for.
     * @param startDate - start date for booking.
     * @param endDate - end date for booking
     * @return - discount as float.
     */
    @Override
    public float getDiscount(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        float discount = 0;
        Period period = Period.between(startDate, endDate);
        int days = period.getDays(); // Days component
        int months = period.getMonths(); // Months component
        int years = period.getYears(); // Years component
        int totalDays = days + months * AVERAGE_AMOUNT_OF_DAYS_IN_MONTH
                + years * AVERAGE_AMOUNT_OF_DAYS_IN_YEAR + 1;
        if (totalDays < DISCOUNT_DELIMITER) {
            return discount;
        } else {
            discount += STARTING_DISCOUNT + (DISCOUNT_PER_ADDITIONAL_BOOKING * (totalDays - DISCOUNT_DELIMITER));
            return Math.min(discount, MAXIMUM_DISCOUNT);
        }
    }
}
