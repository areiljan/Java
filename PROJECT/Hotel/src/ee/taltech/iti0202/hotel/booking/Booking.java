package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private final Room roomToBook;
    private final Client clientWhoBooked;
    private final Hotel hotelToBook;
    private final List<Service> serviceList;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Booking initializer.
     * @param hotelToBook - which hotel to make a booking in.
     * @param roomToBook - which room.
     * @param clientWhoBooked - the client who booked the room.
     * @param startDate - the start date of booking.
     * @param endDate - the end date of booking.
     */
    public Booking(Hotel hotelToBook, Room roomToBook, Client clientWhoBooked, LocalDate startDate, LocalDate endDate, List<Service> serviceList) {
        this.hotelToBook = hotelToBook;
        this.roomToBook = roomToBook;
        this.clientWhoBooked = clientWhoBooked;
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceList = serviceList;
    }

    public LocalDate getStartDate() {
        return LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
    }
    public LocalDate getEndDate() {
        return LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth());
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
        return new ArrayList<>(serviceList);
    }

    public static class BookingBuilder {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private Hotel hotelToBook;
        private Room roomToBook;
        private Client clientWhoBooked;


        private List<Service> serviceList = new ArrayList<>();
        public BookingBuilder(Hotel hotelToBook, Room roomToBook, Client clientWhoBooked,
                              LocalDate startDate, LocalDate endDate) {
            this.hotelToBook = hotelToBook;
            this.roomToBook = roomToBook;
            this.clientWhoBooked = clientWhoBooked;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public BookingBuilder addServices(List<Service> services) {
            serviceList.addAll(services);
            return this;
        }

        public Booking build() {
            return new Booking(hotelToBook, roomToBook, clientWhoBooked, startDate, endDate, serviceList);
        }
    }
}
