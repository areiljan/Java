package ee.taltech.iti0202.hotel.review;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.client.Client;

public class Review {
    private final String reviewText;
    private final Integer rating;
    private final Hotel hotel;
    private final Client client;

    /**
     * Review initializer.
     * @param hotel - which hotel.
     * @param client - which client.
     * @param reviewText - the content.
     * @param rating - the rating.
     */
    public Review(Hotel hotel, Client client, String reviewText, Integer rating) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.hotel = hotel;
        this.client = client;
    }

    /**
     * Rating getter.
     * @return - the reviews rating as an integer.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Hotel getter.
     * @return - the hotel.
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Client getter.
     */
    public Client getClient() { return client; }
}
