package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.room.CannotCancelBookingIfNotBooked;
import ee.taltech.iti0202.hotel.room.NotEnoughMoneyToBookException;
import ee.taltech.iti0202.hotel.room.OverlappingBookingException;
import ee.taltech.iti0202.hotel.room.Room;

import java.time.LocalDate;

public class Client {
    public Integer getMoney() {
        return money;
    }

    private Integer money;
    private String clientName;
    /**
     * Client constructor.
     */
    public Client(String clientName, Integer money) {
        this.clientName = clientName;
        this.money = money;
    }

    public void bookRoom(Room roomToBook, LocalDate dateToBook) throws OverlappingBookingException, NotEnoughMoneyToBookException {
        if (money >= roomToBook.getRoomType().getPrice()) {
            roomToBook.addBooking(dateToBook, this);
            money -= roomToBook.getRoomType().getPrice();
        } else {
            throw new NotEnoughMoneyToBookException(money, roomToBook.getRoomType());
        }
    }

    public void cancelBooking(Room roomToCancel, LocalDate dateToCancel) throws CannotCancelBookingIfNotBooked {
        roomToCancel.removeBooking(this, dateToCancel);
        money += roomToCancel.getRoomType().getPrice();
    }
}
