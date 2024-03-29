package ee.taltech.iti0202.hotel.room;

import java.time.LocalDate;

public class NotEnoughMoneyToBookException extends Exception {
    private LocalDate dateToBook;
    public NotEnoughMoneyToBookException(Integer clientMoney, Room.RoomType roomType) {
        super("The client has " + clientMoney + " Euros, but you needs " + roomType.getPrice() + " Euros to book a " + roomType);
    }
}