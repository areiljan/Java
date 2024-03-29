package ee.taltech.iti0202.hotel.review;

import ee.taltech.iti0202.hotel.Hotel;

public class Review {
    private final String reviewText;
    private final Integer rating;

    public Review(Hotel hotel, String reviewText, Integer rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
