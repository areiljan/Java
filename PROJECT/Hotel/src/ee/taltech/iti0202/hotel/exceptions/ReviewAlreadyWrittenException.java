package ee.taltech.iti0202.hotel.exceptions;

import ee.taltech.iti0202.hotel.client.Client;

public class ReviewAlreadyWrittenException extends Exception {
    private Client client;

    /**
     * Exception that triggers when the client has already written a review.
     * @param client - that tries to write the review.
     */
    public ReviewAlreadyWrittenException(Client client) {
        super("The client " + client.getName() + " has already written a review.");
    }
}
