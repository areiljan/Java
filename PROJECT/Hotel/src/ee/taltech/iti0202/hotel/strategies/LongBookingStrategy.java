package ee.taltech.iti0202.hotel.strategies;

import ee.taltech.iti0202.hotel.Hotel;

import java.time.LocalDate;
import java.time.Period;

public class LongBookingStrategy implements Strategy {
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
        int averageAmountOfDaysInMonth = 30;
        int averageAmountOfDaysInYear = 365;
        int totalDays = days + months * averageAmountOfDaysInMonth
                + years * averageAmountOfDaysInYear + 1;
        int discountDelimiter = 7;
        float discountPerAdditionalBooking = 0.005f;
        float startingDiscount = 0.15f;
        float maximumDiscount = 0.3f;
        if (totalDays < discountDelimiter) {
            return discount;
        } else {
            discount += startingDiscount + (discountPerAdditionalBooking * (totalDays - discountDelimiter));
            return Math.min(discount, maximumDiscount);
        }
    }
}
