package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private final LocalDate bookDate;
    private final Room roomToBook;
    private final Client clientWhoBooked;
    private final Hotel hotelToBook;
    private final List<Service> serviceList;

    /**
     * Booking initializer.
     * @param hotelToBook - which hotel to make a booking in.
     * @param roomToBook - which room.
     * @param clientWhoBooked - the client who booked the room.
     * @param bookDate - the date of booking.
     */
    public Booking(Hotel hotelToBook, Room roomToBook, Client clientWhoBooked, LocalDate bookDate, List<Service> serviceList) {
        this.hotelToBook = hotelToBook;
        this.roomToBook = roomToBook;
        this.clientWhoBooked = clientWhoBooked;
        this.bookDate = bookDate;
        this.serviceList = serviceList;
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

    public List<Service> getServices() {
        return serviceList;
    }

    public static class BookingBuilder {
        private Hotel hotelToBook;
        private Room roomToBook;
        private Client clientWhoBooked;
        private LocalDate bookDate;


        private List<Service> serviceList = new ArrayList<>();
        public BookingBuilder(Hotel hotelToBook, Room roomToBook, Client clientWhoBooked,
                              LocalDate bookDate) {
            this.hotelToBook = hotelToBook;
            this.roomToBook = roomToBook;
            this.clientWhoBooked = clientWhoBooked;
            this.bookDate = bookDate;
        }

        public BookingBuilder addServices(List<Service> services) {
            serviceList.addAll(services);
            return this;
        }

        public Booking build() {
            return new Booking(hotelToBook, roomToBook, clientWhoBooked, bookDate, serviceList);
        }
    }
}
