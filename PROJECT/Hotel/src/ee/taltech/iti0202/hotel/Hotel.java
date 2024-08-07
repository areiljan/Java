package ee.taltech.iti0202.hotel;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.booking.Service;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.review.Review;
import ee.taltech.iti0202.hotel.room.Room;
import ee.taltech.iti0202.hotel.strategies.Strategy;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private final String name;
    private final String city;
    private final String country;
    private ReservationSystem reservationSystem;
    private ArrayList<Room> rooms;
    private ArrayList<Client> clients;
    private ArrayList<Booking> bookings;
    private ArrayList<Review> reviews;
    private Strategy strategy;


    /**
     * Hotel constructor.
     */
    public Hotel(String name, String country, String city, ReservationSystem reservationSystem) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.rooms = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.strategy = null;
        this.reservationSystem = reservationSystem;
        reservationSystem.addHotel(this);
    }

    /**
     * Set current Strategy.
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Get the current Strategy.
     * @return - strategy.
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * City getter.
     * @return city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Country getter.
     * @return country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * BookingSystem getter.
     * @return bookingSystem.
     */
    public ReservationSystem getBookingSystem() {
        return reservationSystem;
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
    public List<Client> getClients() {
        return new ArrayList<>(clients);
    }

    /**
     * Reviews getter.
     * @return - list of reviews.
     */
    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
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
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    /**
     * Method to get all rooms in the hotel.
     * @return - list of rooms.
     */
    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
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
     * The clients, the first criteria for ordering is the amount of bookings per client in descending order.
     * If the amount of bookings is the same then order by the rating that the client has given the hotel.
     */
    public List<Client> orderClients() {
        return clients.stream()
                .sorted(
                        Comparator.comparingInt(Client::getRoomCount)
                                .reversed()
                                .thenComparingInt(client -> client.getRatingAboutHotel(this))
                                .thenComparingDouble(client -> calculateAverageServiceCost(client))
                                .reversed()
                )
                .collect(Collectors.toList());
    }

    /**
     * Calculate the average cost of service for a client.
     * @param client - the client whose average cost is to be calculated.
     * @return - average score as double.
     */
    private double calculateAverageServiceCost(Client client) {
        List<Service> services = new ArrayList<>();
        for (Booking booking : client.getBookings()) {
            services.addAll(booking.getServices());
        }
        if (services.isEmpty()) {
            return 0.0;
        }
        double totalCost = 0.0;
        for (Service service : services) {
            double price = service.getPrice().doubleValue();
            totalCost += price;
        }
        return totalCost / services.size();
    }

    /**
     * Search for free rooms in the hotel by date.
     */
    public List<Room> searchForFreeRooms(LocalDate dateToFind) {
        ArrayList<Room> vacantRooms = new ArrayList<>();
        LocalDate copyDate = LocalDate.of(dateToFind.getYear(), dateToFind.getMonth(), dateToFind.getDayOfMonth());
        for (Room room : rooms) {
            boolean isVacant = true;
            for (Booking existingBooking : bookings) {
                if (isOverlap(existingBooking.getStartDate(), existingBooking.getEndDate(),
                        copyDate, copyDate) && existingBooking.getRoom().equals(room)) {
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
                if (isOverlap(existingBooking.getStartDate(), existingBooking.getEndDate(), currentDate, currentDate)
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
        // making a defensive copy.
        LocalDate copyDate = LocalDate.of(dateToFind.getYear(), dateToFind.getMonth(), dateToFind.getDayOfMonth());
        for (Room room : rooms) {
            boolean isVacant = true;
            for (Booking existingBooking : bookings) {
                if (isOverlap(existingBooking.getStartDate(), existingBooking.getEndDate(), copyDate, copyDate)
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
     * Search for free rooms using weekday.
     * If the next instance of this weekday is free, put it in the list.
     */
    public List<Room> searchForFreeRooms(DayOfWeek dayOfWeek, Clock clock) {
        ArrayList<Room> vacantRooms = new ArrayList<>();
        LocalDate currentDate = LocalDate.now(clock);
        LocalDate nextDate = currentDate.with(TemporalAdjusters.next(dayOfWeek));
        for (Room room : rooms) {
            boolean isVacant = true;
            for (Booking existingBooking : bookings) {
                if (isOverlap(existingBooking.getStartDate(), existingBooking.getEndDate(), nextDate, nextDate)
                        && existingBooking.getRoom().equals(room)) {
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
     * Helper function to check for overlap of dates.
     * @param start1 - start date of the first booking.
     * @param finish1 - finish date of the first booking.
     * @param start2 - start date of the second booking.
     * @param finish2 - finish date of the second booking.
     * @return - true if there is overlap.
     */
    public static boolean isOverlap(LocalDate start1, LocalDate finish1, LocalDate start2, LocalDate finish2) {
        return !start1.isAfter(finish2) && !finish1.isBefore(start2);
    }
}
