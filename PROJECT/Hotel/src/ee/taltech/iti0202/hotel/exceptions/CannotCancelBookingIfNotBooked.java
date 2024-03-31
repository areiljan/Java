package ee.taltech.iti0202.hotel.exceptions;

public class CannotCancelBookingIfNotBooked extends Exception {
    /**
     * Exception for when the client tries to cancel a booking that is not made.
     */
    public CannotCancelBookingIfNotBooked() {
        super("Excuse me, sir, you have not booked this room. No refund for you.");
    }
}
