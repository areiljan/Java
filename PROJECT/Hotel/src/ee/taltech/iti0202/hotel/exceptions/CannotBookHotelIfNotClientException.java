package ee.taltech.iti0202.hotel.exceptions;

import ee.taltech.iti0202.hotel.client.Client;

public class CannotBookHotelIfNotClientException extends Exception {
    /**
     * Exception that triggers when the booker is not a client.
     * @param client - booker.
     */
    public CannotBookHotelIfNotClientException(Client client) {
        super(client.getName() + " is not a client in the specified Hotel.");
    }
}
