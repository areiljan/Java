package ee.taltech.iti0202.hotel.strategies;

import ee.taltech.iti0202.hotel.Hotel;

import java.time.LocalDate;

public interface Strategy {
    /**
     * Discount getter.
     * @param hotel - hotel to get discount for.
     * @param startDate - start date for booking.
     * @param endDate - end date for booking
     * @return - discount as float.
     */
    float getDiscount(Hotel hotel, LocalDate startDate, LocalDate endDate);
}
