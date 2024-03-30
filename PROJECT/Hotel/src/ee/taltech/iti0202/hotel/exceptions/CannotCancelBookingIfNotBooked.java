package ee.taltech.iti0202.hotel.exceptions;

public class CannotCancelBookingIfNotBooked extends Exception {
    public CannotCancelBookingIfNotBooked() {
        super("Excuse me, sir, you have not booked this room. No refund for you.");
    }
}