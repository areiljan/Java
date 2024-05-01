package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.booking.Service;
import ee.taltech.iti0202.hotel.exceptions.*;
import ee.taltech.iti0202.hotel.review.Review;
import ee.taltech.iti0202.hotel.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Client {
    private final ArrayList<Booking> clientBookings;
    private final ArrayList<Review> clientReviews;
    private float money;
    private String clientName;
    private Hotel currentHotel;
    private ReservationSystem reservationSystem;

    /**
     * Client constructor.
     */
    public Client(String clientName, Integer money, ReservationSystem reservationSystem) {
        this.clientName = clientName;
        this.money = money;
        this.clientBookings = new ArrayList<>();
        this.clientReviews = new ArrayList<>();
        this.currentHotel = null;
        this.reservationSystem = reservationSystem;
    }

    /**
     * Set current hotel.
     */
    public void setHotel(Hotel hotel) {
        currentHotel = hotel;
    }

    /**
     * Client bookings getter.
     * @return - clients bookings as list.
     */
    public List<Booking> getBookings() {
        return clientBookings;
    }

    /**
     * Client reviews getter.
     * @return - clients reviews as list.
     */
    public List<Review> getReviews() {
        return clientReviews;
    }

    /**
     * Get the rating about the hotel specified.
     * @param hotel - the hotel the review is needed for.
     * @return - rating as integer.
     */
    public Integer getRatingAboutHotel(Hotel hotel) {
        for (Review review : clientReviews) {
            if (review.getHotel().equals(hotel)) {
                return review.getRating();
            }
        }
        return 0;
        // returns 0, so the clients who have not written a review will be ranked lowest.
    }

    /**
     * Money getter.
     * @return - money amount.
     */
    public float getMoney() {
        return money;
    }

    /**
     * Name getter.
     * @return - name as string.
     */
    public String getName() {
        return clientName;
    }

    /**
     * Get amount of different rooms booked.
     */
    public int getRoomCount() {
        Set<Room> distinctRooms = new HashSet<>();
        for (Booking booking : clientBookings) {
            distinctRooms.add(booking.getRoom());
        }
        return distinctRooms.size();
    }

    /**
     * Book a room in the hotel.
     * @param roomToBook - which room.
     * @param dateToBook - which date.
     * @throws OverlappingBookingException - thrown if the date is taken.
     * @throws NotEnoughMoneyToBookException - thrown if the client does not have enough money.
     */
    public void bookRoom(Room roomToBook, LocalDate dateToBook, ArrayList<Service> services)
            throws OverlappingBookingException, NotEnoughMoneyToBookException,
            CannotBookHotelIfNotClientException {
        float discount = checkForDiscount(currentHotel);
        // check for service cost
        int serviceTotalCost = 0;
        for (Service service : services) {
            serviceTotalCost += service.getPrice().intValue();
        }
        // if not enough money for a room and services, throw exception.
        if (money < (roomToBook.getRoomType().getPrice().intValue() * discount) + serviceTotalCost) {
            throw new NotEnoughMoneyToBookException(money, roomToBook.getRoomType());
        } else if (currentHotel == null) {
            // The requirement for the booker to be a client is not specified in the assignment,
            // but I assumed it to be logical.
            throw new CannotBookHotelIfNotClientException(this);
        } else {
            for (Booking existingBooking : currentHotel.getBookings()) {
                // Check if the new booking overlaps with an existing booking, if it does, throw a custom exception.
                if (dateToBook.equals(existingBooking.getBookDate()) && roomToBook.equals(existingBooking.getRoom())) {
                    throw new OverlappingBookingException(dateToBook);
                }
            }

            // No overlapping bookings found, add the new booking
            Booking newBooking = new Booking.BookingBuilder(currentHotel, roomToBook, this, dateToBook)
                    .addServices(services)
                    .build();

            clientBookings.add(newBooking);
            currentHotel.addBooking(newBooking);
            money -= (roomToBook.getRoomType().getPrice().intValue() * discount) + serviceTotalCost;
        }
    }


    /**
     * Check for discount.
     * @return - the discount as multiplier. 0 - 1
     */
    public float checkForDiscount(Hotel hotelToCheckFor) {
        List<Client> clients = hotelToCheckFor.orderClients();
        if (clients.size() > 3) {
            if (clients.get(0).equals(this)) {
                return 0.85f;
            } else if (clients.get(1).equals(this)) {
                return 0.9f;
            } else if (clients.get(2).equals(this)) {
                return 0.95f;
            }
        }
        // full price.
        return 1;
    }

    /**
     * Search for a hotel using location.
     */
    public List<Hotel> filterHotels(String country, String city) {
        return reservationSystem.filterByLocation(country, city);
    }

    /**
     * Search for a hotel using rating.
     */
    public List<Hotel> filterHotels(Integer rating) {
        return reservationSystem.filterByRating(rating);
    }

    /**
     * Search for a hotel using rating.
     */
    public List<Hotel> filterHotels(Integer rating, String country, String city) {
        Set<Hotel> hotelsWithSufficientRatings = new HashSet<>(reservationSystem.filterByRating(rating));
        Set<Hotel> hotelsInRightLocation = new HashSet<>(reservationSystem.filterByLocation(country, city));
        hotelsWithSufficientRatings.retainAll(hotelsInRightLocation);
        return new ArrayList<>(hotelsWithSufficientRatings);
    }

    /**
     * Search for rooms in the specified price range.
     * @param hotel - where to find rooms.
     * @param budget - the budget of the rooms.
     */
    public ArrayList<Room> filterRooms(Hotel hotel, Integer budget) {
        ArrayList<Room> roomsInPriceRange = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            float discount = checkForDiscount(hotel);
            if (room.getRoomType().getPrice().intValue() * discount <= budget) {
                roomsInPriceRange.add(room);
            }
        }
        return roomsInPriceRange;
    }

    /**
     * Cancel a booking.
     * The client can currently cancel a booking from a hotel, where he has already left.
     * @param hotelToCancel - which hotel.
     * @param dateToCancel - which date.
     * @throws CannotCancelBookingIfNotBooked - thrown if the client has not booked this.
     */
    public void cancelBooking(Hotel hotelToCancel, Room roomToBook, LocalDate dateToCancel)
            throws CannotCancelBookingIfNotBooked {
        for (Booking existingBooking : clientBookings) {
            if (existingBooking.getRoom().equals(roomToBook) && existingBooking.getBookDate().equals(dateToCancel)) {
                clientBookings.remove(existingBooking);
                hotelToCancel.removeBooking(existingBooking);
                money += roomToBook.getRoomType().getPrice().intValue();
                return;
            }
        }
        throw new CannotCancelBookingIfNotBooked();
    }

    /**
     * Write a review.
     * The client can currently write one review per hotel.
     * @param hotelToReview
     * @param reviewText - the content of the review in text.
     * @param rating - 1-5 rating.
     */
    public void writeReview(Hotel hotelToReview, String reviewText, Integer rating)
            throws RatingOutOfBoundsException, CannotWriteReviewIfNotBookedInHotelException,
            ReviewAlreadyWrittenException {
        for (Review existingReview : hotelToReview.getReviews()) {
            if (this.equals(existingReview.getClient())) {
                throw new ReviewAlreadyWrittenException(this);
            }
        }
        for (Booking existingBooking : hotelToReview.getBookings()) {
            if (this.equals(existingBooking.getClient())) {
                if (rating >= 1 && rating <= 5) {
                    Review newReview = new Review(hotelToReview, this, reviewText, rating);
                    clientReviews.add(newReview);
                    hotelToReview.addReview(newReview);
                    return;
                } else {
                    throw new RatingOutOfBoundsException();
                }
            }
        }
        throw new CannotWriteReviewIfNotBookedInHotelException(this, hotelToReview);
    }
}
