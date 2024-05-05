package ee.taltech.iti0202.hotel.exceptions;

import java.time.LocalDate;

public class OverlappingBookingException extends Exception {
    private LocalDate dateToBook;

    /**
     * Booking for when the room is already booked on that date.
     * @param startDateToBook - which date the booking will start.
     * @param endDateToBook - which date the booking will end.
     */
    public OverlappingBookingException(LocalDate startDateToBook, LocalDate endDateToBook) {
        super("Booking period " + startDateToBook + "to" + endDateToBook + " overlaps with an existing booking.");
    }
}
