package ee.taltech.iti0202.hotel.exceptions;

import ee.taltech.iti0202.hotel.client.Client;

public class ReviewAlreadyWrittenException extends Exception {
    private Client client;
    public ReviewAlreadyWrittenException(Client client) {
        super("The client " + client.getName() + " has already written a review.");
    }
}
