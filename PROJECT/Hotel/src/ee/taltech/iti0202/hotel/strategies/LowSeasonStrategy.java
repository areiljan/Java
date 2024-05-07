package ee.taltech.iti0202.hotel.strategies;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.booking.Booking;

import java.time.LocalDate;

public class LowSeasonStrategy implements Strategy {

    public static final float MINIMUM_DISCOUNT = 0.1f;

    /**
     * Get discount based on bookings made in the hotel on the same month.
     * @param hotel - hotel to get discount for.
     * @param startDate - start date for booking.
     * @param endDate - end date for booking
     * @return - discount as float.
     */
    @Override
    public float getDiscount(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        float discount = MINIMUM_DISCOUNT;
        Integer bookingCount = 0;
        for (Booking booking : hotel.getBookings()) {
            if (booking.getStartDate().getMonth() == startDate.getMonth()) {
                bookingCount++;
            }
        }
        if (bookingCount >= 10) {
            return discount;
        } else {
            discount += (float) (10 - bookingCount) / 100;
            return discount;
        }
    }
}
