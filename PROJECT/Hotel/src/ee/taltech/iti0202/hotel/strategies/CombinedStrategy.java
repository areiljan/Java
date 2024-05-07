package ee.taltech.iti0202.hotel.strategies;

import ee.taltech.iti0202.hotel.Hotel;

import java.time.LocalDate;

public class CombinedStrategy implements Strategy {
    private final Strategy longBooking;
    private final Strategy lowSeason;

    public CombinedStrategy() {
        this.longBooking = new LongBookingStrategy();
        this.lowSeason = new LowSeasonStrategy();
    }
    @Override
    public float getDiscount(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        float longBookingDiscount = longBooking.getDiscount(hotel, startDate, endDate);
        float lowSeasonDiscount = lowSeason.getDiscount(hotel, startDate, endDate);
        float maxDiscount = 0.4f;
        return Math.min(maxDiscount, lowSeasonDiscount + longBookingDiscount);
    }
}
