package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.exceptions.*;
import ee.taltech.iti0202.hotel.review.Review;
import ee.taltech.iti0202.hotel.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client {
    private final ArrayList<Booking> clientBookings;
    private final ArrayList<Review> clientReviews;

    private Integer money;

    private String clientName;
    /**
     * Client constructor.
     */
    public Client(String clientName, Integer money) {
        this.clientName = clientName;
        this.money = money;
        this.clientBookings = new ArrayList<>();
        this.clientReviews = new ArrayList<>();
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
     * Book a room in the hotel.
     * @param hotelToBook - which hotel.
     * @param roomToBook - which room.
     * @param dateToBook - which date.
     * @throws OverlappingBookingException - thrown if the date is taken.
     * @throws NotEnoughMoneyToBookException - thrown if the client does not have enough money.
     */
    public void bookRoom(Hotel hotelToBook, Room roomToBook, LocalDate dateToBook) throws OverlappingBookingException, NotEnoughMoneyToBookException {
        if (money >= roomToBook.getRoomType().getPrice()) {
            for (Booking existingBooking : hotelToBook.getBookings()) {
                // Check if the new booking overlaps with an existing booking, if it does, throw a custom exception.
                if (dateToBook.equals(existingBooking.getBookDate()) && roomToBook.equals(existingBooking.getRoom())) {
                    throw new OverlappingBookingException(dateToBook);
                }
            }

            // No overlapping bookings found, add the new booking
            Booking newBooking = new Booking(hotelToBook, roomToBook, this, dateToBook);
            clientBookings.add(newBooking);
            hotelToBook.addBooking(newBooking);
            money -= roomToBook.getRoomType().getPrice();
        } else {
            throw new NotEnoughMoneyToBookException(money, roomToBook.getRoomType());
        }
    }

    /**
     * Cancel a booking.
     * @param hotelToBook - which hotel.
     * @throws CannotCancelBookingIfNotBooked - thrown if the client has not booked this.
     */
    public void cancelBooking(Hotel hotelToBook, Room roomToBook, LocalDate dateToBook) throws CannotCancelBookingIfNotBooked {
        for (Booking existingBooking : clientBookings) {
            if (existingBooking.getRoom().equals(roomToBook) && existingBooking.getBookDate().equals(dateToBook)) {
                clientBookings.remove(existingBooking);
                hotelToBook.removeBooking(existingBooking);
                money += roomToBook.getRoomType().getPrice();
                return;
            }
        }
        throw new CannotCancelBookingIfNotBooked();
    }

    /**
     * Write a review.
     * @param hotelToReview - which hotel.
     * @param reviewText - the content of the review in text.
     * @param rating - 1-5 rating.
     */
    public void writeReview(Hotel hotelToReview, String reviewText, Integer rating) throws RatingOutOfBoundsException, CannotWriteReviewIfNotBookedInHotelException {
        for (Booking existingBooking : hotelToReview.getBookings()) {
            if (existingBooking.getClient().equals(this)) {
                if (rating >= 1 && rating <= 5) {
                    Review newReview = new Review(hotelToReview, reviewText, rating);
                    clientReviews.add(newReview);
                    hotelToReview.addReview(newReview);
                } else {
                    throw new RatingOutOfBoundsException();
                }
            }
        }
        throw new CannotWriteReviewIfNotBookedInHotelException(this, hotelToReview);
    }
}
