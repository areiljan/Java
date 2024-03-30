package ee.taltech.iti0202.hotel.exceptions;

public class RatingOutOfBoundsException extends Exception {
    private Integer rating;
    public RatingOutOfBoundsException() {
        super("Your rating needs to be between 1-5 points");
    }
}
