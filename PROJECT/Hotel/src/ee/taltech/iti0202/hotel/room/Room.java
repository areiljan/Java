package ee.taltech.iti0202.hotel.room;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.client.Client;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private final RoomType roomType;
    private Integer roomNumber = 0;
    private Hotel hotel;

    private Map<LocalDate, Client> bookings;

    public Room(Hotel hotelTheRoomIsIn, RoomType roomType) {
        this.roomType = roomType;
        this.roomNumber += 1;
        this.bookings = new HashMap<>();
        this.hotel = hotelTheRoomIsIn;
    }

    // The different rooms do not have different functionality, so I will use enum.
    public enum RoomType {
        SUITE(5000),
        ECONOMYROOM(1000),
        FAMILYROOM(2000);

        private final int price;

        /**
         * Rooms have different prices.
         * @param price - price of the room.
         */
        RoomType(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }
    public void addBooking(LocalDate dateToBook, Client clientWhoBooked) throws OverlappingBookingException {
        for (Map.Entry<LocalDate, Client> entry : bookings.entrySet()) {
            LocalDate existingDateBooked = entry.getKey();
            // Check if the new booking overlaps with an existing booking, if it does, throw a custom exception.
            if (dateToBook.isEqual(existingDateBooked)) {
                throw new OverlappingBookingException(dateToBook);
            }
        }

        // No overlapping bookings found, add the new booking
        bookings.put(dateToBook, clientWhoBooked);
    }

    public void removeBooking(Client clientWhoBooked, LocalDate dateToBook) throws CannotCancelBookingIfNotBooked {
        for (Map.Entry<LocalDate, Client> entry : bookings.entrySet()) {
            LocalDate existingDateBooked = entry.getKey();
            Client existingClient = entry.getValue();
            // Check if the new booking overlaps with an existing booking, if it does, throw a custom exception.
            if (dateToBook.isEqual(existingDateBooked) && clientWhoBooked.equals(existingClient)) {
                bookings.remove(dateToBook);
                return;
            }
        }
        throw new CannotCancelBookingIfNotBooked();
    }

    public Map<LocalDate, Client> getBookings() {
        return bookings;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public RoomType getRoomType() {
        return roomType;
    }

}
