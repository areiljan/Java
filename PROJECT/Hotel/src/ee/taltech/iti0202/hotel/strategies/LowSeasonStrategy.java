package ee.taltech.iti0202.hotel.strategies;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.booking.Booking;

import java.time.LocalDate;

public class LowSeasonStrategy implements Strategy {
    @Override
    public float getDiscount(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        float discount = 0.1f;
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
