package ee.taltech.iti0202.hotel.exceptions;

import java.time.LocalDate;

public class OverlappingBookingException extends Exception {
    private LocalDate dateToBook;

    /**
     * Booking for when the room is already booked on that date.
     * @param dateToBook - which date the attempt was made.
     */
    public OverlappingBookingException(LocalDate dateToBook) {
        super("Booking period " + dateToBook + " overlaps with an existing booking.");
    }
}
