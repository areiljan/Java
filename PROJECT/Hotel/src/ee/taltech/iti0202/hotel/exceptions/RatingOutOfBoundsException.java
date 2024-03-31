package ee.taltech.iti0202.hotel.exceptions;

public class RatingOutOfBoundsException extends Exception {
    private Integer rating;

    /**
     * Exception for when the rating is too big or too small.
     */
    public RatingOutOfBoundsException() {
        super("Your rating needs to be between 1-5 points");
    }
}
