package ee.taltech.iti0202.hotel.strategies;

import ee.taltech.iti0202.hotel.Hotel;

import java.time.LocalDate;
import java.time.Period;

public class LongBookingStrategy implements Strategy {
    @Override
    public float getDiscount(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        float discount = 0;
        Period period = Period.between(startDate, endDate);
        int days = period.getDays(); // Days component
        int months = period.getMonths(); // Months component
        int years = period.getYears(); // Years component
        int totalDays = days + months * 30 + years * 365 + 1;
        if (totalDays < 7) {
            return discount;
        } else {
            discount += 0.15f + (0.005f * (totalDays - 7));
            return Math.min(discount, 0.3f);
        }
    }
}
