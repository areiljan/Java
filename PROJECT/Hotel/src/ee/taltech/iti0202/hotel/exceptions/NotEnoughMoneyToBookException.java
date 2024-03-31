package ee.taltech.iti0202.hotel.exceptions;

import ee.taltech.iti0202.hotel.room.Room;

import java.time.LocalDate;

public class NotEnoughMoneyToBookException extends Exception {
    private LocalDate dateToBook;

    /**
     * Exception for when the client does not have enough money.
     * @param clientMoney - clients balance.
     * @param roomType - room, which the client wants to book.
     */
    public NotEnoughMoneyToBookException(Integer clientMoney, Room.RoomType roomType) {
        super("The client has " + clientMoney + " Euros, but you need "
                + roomType.getPrice() + " Euros to book a " + roomType);
    }
}
