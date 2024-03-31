package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.exceptions.CannotBookHotelIfNotClientException;
import ee.taltech.iti0202.hotel.exceptions.CannotCancelBookingIfNotBooked;
import ee.taltech.iti0202.hotel.exceptions.CannotWriteReviewIfNotBookedInHotelException;
import ee.taltech.iti0202.hotel.exceptions.NotEnoughMoneyToBookException;
import ee.taltech.iti0202.hotel.exceptions.OverlappingBookingException;
import ee.taltech.iti0202.hotel.exceptions.RatingOutOfBoundsException;
import ee.taltech.iti0202.hotel.exceptions.ReviewAlreadyWrittenException;
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
    private Integer money;
    private String clientName;
    private Hotel currentHotel;

    /**
     * Client constructor.
     */
    public Client(String clientName, Integer money) {
        this.clientName = clientName;
        this.money = money;
        this.clientBookings = new ArrayList<>();
        this.clientReviews = new ArrayList<>();
        this.currentHotel = null;
    }

    /**
     * Set current hotel.
     */

    public void SetHotel(Hotel hotel) {
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
    public Integer getMoney() {
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
    public void bookRoom(Room roomToBook, LocalDate dateToBook)
            throws OverlappingBookingException, NotEnoughMoneyToBookException,
            CannotBookHotelIfNotClientException {
        if (money < roomToBook.getRoomType().getPrice()) {
            throw new NotEnoughMoneyToBookException(money, roomToBook.getRoomType());
        } else if (currentHotel == null) {
            // The requirement for the booker to be a client is not specified in the assignment, but I assumed it to be logical.
            throw new CannotBookHotelIfNotClientException(this);
        } else {
            for (Booking existingBooking : currentHotel.getBookings()) {
                // Check if the new booking overlaps with an existing booking, if it does, throw a custom exception.
                if (dateToBook.equals(existingBooking.getBookDate()) && roomToBook.equals(existingBooking.getRoom())) {
                    throw new OverlappingBookingException(dateToBook);
                }
            }

            // No overlapping bookings found, add the new booking
            Booking newBooking = new Booking(currentHotel, roomToBook, this, dateToBook);
            clientBookings.add(newBooking);
            currentHotel.addBooking(newBooking);
            money -= roomToBook.getRoomType().getPrice();
        }
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
                money += roomToBook.getRoomType().getPrice();
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
