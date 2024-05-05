package ee.taltech.iti0202.hotel.strategies;

import ee.taltech.iti0202.hotel.Hotel;

import java.time.LocalDate;

public interface Strategy {
    public float getDiscount(Hotel hotel, LocalDate startDate, LocalDate endDate);
}
