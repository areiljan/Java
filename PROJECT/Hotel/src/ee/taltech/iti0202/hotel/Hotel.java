package ee.taltech.iti0202.hotel;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.review.Review;
import ee.taltech.iti0202.hotel.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private final String name;

    private ArrayList<Room> rooms;
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
     * Average rating getter.
     */
    public double getAverageRating() {
        double average = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
        return Math.round(average * 100) / 100.0;
    }

    /**
     * Clients getter.
     * @return - list of clients.
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * Reviews getter.
     * @return - list of reviews.
     */
    public ArrayList<Review> getReviews() {
        return reviews;
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
        clientToAdd.setHotel(this);
    }

    /**
     * Method to remove a client from the Hotel.
     */
    public void removeClient(Client clientToRemove) {
        clients.remove(clientToRemove);
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

    /**
     * Order clients.
     * The clients, the first criterion for ordering is the amount of bookings per client in descending order.
     * If the amount of bookings is the same then order by the rating that the client has given the hotel.
     */
    public List<Client> orderClients() {
        return clients.stream()
                .sorted(
                        Comparator.comparingInt(Client::getRoomCount)
                                .reversed()
                                .thenComparingInt(client -> client.getRatingAboutHotel(this))
                                .reversed()
                )
                .collect(Collectors.toList());
    }

    /**
     * Search for free rooms in the hotel by date.
     */
    public List<Room> searchForFreeRooms(LocalDate dateToFind) {
        ArrayList<Room> vacantRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean isVacant = true;
            for (Booking existingBooking : bookings) {
                if (existingBooking.getBookDate().equals(dateToFind) && existingBooking.getRoom().equals(room)) {
                    isVacant = false;
                    break;
                }
            }
            if (isVacant) {
                vacantRooms.add(room);
            }
        }
        return vacantRooms;
    }

    /**
     * Search for free rooms in the hotel by room type.
     * Assumed for this search to only look for dates on the same day.
     */
    public List<Room> searchForFreeRooms(Room.RoomType roomType) {
        ArrayList<Room> vacantRooms = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (Room room : rooms) {
            boolean isVacant = true;
            for (Booking existingBooking : bookings) {
                if (existingBooking.getBookDate().equals(currentDate)
                        && existingBooking.getRoom().equals(room)) {
                    isVacant = false;
                    break;
                }
            }
            if (isVacant && room.getRoomType().equals(roomType)) {
                vacantRooms.add(room);
            }
        }
        return vacantRooms;
    }

    /**
     * Search for free rooms in the hotel by date and room type.
     */
    public List<Room> searchForFreeRooms(LocalDate dateToFind, Room.RoomType roomType) {
        ArrayList<Room> vacantRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean isVacant = true;
            for (Booking existingBooking : bookings) {
                if (existingBooking.getBookDate().equals(dateToFind)
                        && existingBooking.getRoom().equals(room)) {
                    isVacant = false;
                    break;
                }
            }
            if (isVacant && room.getRoomType().equals(roomType)) {
                vacantRooms.add(room);
            }
        }
        return vacantRooms;
    }
}
