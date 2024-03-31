package ee.taltech.iti0202.hotel.exceptions;

import ee.taltech.iti0202.hotel.client.Client;

public class CannotBookHotelIfNotClientException extends Exception {
    public CannotBookHotelIfNotClientException(Client client) {
        super(client.getName() + " is not a client in the specified Hotel.");
    }
}
