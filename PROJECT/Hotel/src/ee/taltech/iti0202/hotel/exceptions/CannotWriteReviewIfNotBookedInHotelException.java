package ee.taltech.iti0202.hotel.exceptions;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.client.Client;

public class CannotWriteReviewIfNotBookedInHotelException extends Throwable {
    public CannotWriteReviewIfNotBookedInHotelException(Client client, Hotel hotel) {
        super("Hello mr/mrs " + client.getName() + " you are trying to write a review about " + hotel.getName() + " but you have not even booked there yet.");
    }
}
