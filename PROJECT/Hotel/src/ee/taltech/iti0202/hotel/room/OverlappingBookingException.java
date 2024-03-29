package ee.taltech.iti0202.hotel.room;

import java.time.LocalDate;

public class OverlappingBookingException extends Exception {
    private LocalDate dateToBook;
    public OverlappingBookingException(LocalDate dateToBook) {
        super("Booking period " + dateToBook + " overlaps with an existing booking.");
    }
}
