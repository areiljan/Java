package ee.taltech.iti0202.hotel;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.review.Review;
import ee.taltech.iti0202.hotel.room.Room;

import java.util.ArrayList;

public class Hotel {
    private final String name;

    private ArrayList<Room> rooms;
    public ArrayList<Client> getClients() {
        return clients;
    }
    private ArrayList<Client> clients;
    private ArrayList<Booking> bookings;
    private ArrayList<Review> reviews;

    /**
     * Hotel constructor.
     */
    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    /**
     * Name getter.
     * @return - name of the hotel.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get total list of all bookings in the hotel.
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Method to get all rooms in the hotel.
     * @return - list of rooms.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Method to add the room to the hotel.
     */
    public void addRoom(Room roomToAdd) {
        rooms.add(roomToAdd);
    }

    /**
     * Method to add a client to the Hotel.
     */
    public void addReview(Review reviewToAdd) {
        reviews.add(reviewToAdd);
    }

    /**
     * Method to add a client to the Hotel.
     */
    public void addClient(Client clientToAdd) {
        clients.add(clientToAdd);
    }

    /**
     * Add a booking to the hotel.
     * @param bookingToAdd - booking to add.
     */
    public void addBooking(Booking bookingToAdd) {
        bookings.add(bookingToAdd);
    }

    /**
     * Remove a booking from the hotel.
     * @param bookingToRemove - booking to remove.
     */
    public void removeBooking(Booking bookingToRemove) {
        bookings.remove(bookingToRemove);
    }


}
