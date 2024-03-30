package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.room.Room;

import java.time.LocalDate;

public class Booking {
    private final LocalDate bookDate;
    private final Room roomToBook;
    private final Client clientWhoBooked;
    private final Hotel hotelToBook;

    public Booking(Hotel hotelToBook, Room roomToBook, Client clientWhoBooked, LocalDate bookDate) {
        this.hotelToBook = hotelToBook;
        this.roomToBook = roomToBook;
        this.clientWhoBooked = clientWhoBooked;
        this.bookDate = bookDate;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    public Room getRoom() {
        return roomToBook;
    }

    public Client getClient() {
        return clientWhoBooked;
    }

    public Hotel getHotel() {
        return hotelToBook;
    }
}
