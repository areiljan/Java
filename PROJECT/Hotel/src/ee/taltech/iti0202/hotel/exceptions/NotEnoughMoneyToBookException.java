package ee.taltech.iti0202.hotel.exceptions;

import java.time.LocalDate;

public class NotEnoughMoneyToBookException extends Exception {
    private LocalDate dateToBook;

    /**
     * Exception for when the client does not have enough money.
     * @param clientMoney - clients balance.
     * @param roomType - room, which the client wants to book.
     */
    public NotEnoughMoneyToBookException(float clientMoney, float roomPrice) {
        super("The client has " + clientMoney + " Euros, but you need "
                + roomPrice + " Euros to book this room with all amenities selected.");
    }
}
