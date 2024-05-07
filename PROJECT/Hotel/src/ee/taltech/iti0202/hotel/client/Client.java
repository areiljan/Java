package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.booking.Service;
import ee.taltech.iti0202.hotel.exceptions.CannotBookHotelIfNotClientException;
import ee.taltech.iti0202.hotel.exceptions.CannotCancelBookingIfNotBooked;
import ee.taltech.iti0202.hotel.exceptions.CannotWriteReviewIfNotBookedInHotelException;
import ee.taltech.iti0202.hotel.exceptions.NotEnoughMoneyToBookException;
import ee.taltech.iti0202.hotel.exceptions.OverlappingBookingException;
import ee.taltech.iti0202.hotel.exceptions.RatingOutOfBoundsException;
import ee.taltech.iti0202.hotel.exceptions.ReviewAlreadyWrittenException;
import ee.taltech.iti0202.hotel.review.Review;
import ee.taltech.iti0202.hotel.room.Room;

import java.text.DecimalFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ee.taltech.iti0202.hotel.Hotel.isOverlap;

public class Client {
    private final ArrayList<Booking> clientBookings;
    private final ArrayList<Review> clientReviews;
    private float money;
    private final String clientName;
    private Hotel currentHotel;
    private final ReservationSystem reservationSystem;

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
        return new ArrayList<>(clientBookings);
    }

    /**
     * Client reviews getter.
     * @return - clients reviews as list.
     */
    public List<Review> getReviews() {
        return new ArrayList<>(clientReviews);
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
     * @param startDateToBook - which date the booking will start.
     * @param endDateToBook - which date the booking will end.
     * @throws OverlappingBookingException - thrown if the date is taken.
     * @throws NotEnoughMoneyToBookException - thrown if the client does not have enough money.
     */
    public void bookRoom(Room roomToBook, LocalDate startDateToBook,
                         LocalDate endDateToBook, List<Service> services)
            throws OverlappingBookingException, NotEnoughMoneyToBookException,
            CannotBookHotelIfNotClientException {
        // defensive copy
        startDateToBook = LocalDate.of(startDateToBook.getYear(),
                startDateToBook.getMonth(), startDateToBook.getDayOfMonth());
        endDateToBook = LocalDate.of(endDateToBook.getYear(),
                endDateToBook.getMonth(), endDateToBook.getDayOfMonth());
        if (currentHotel == null) {
            // The requirement for the booker to be a client is not specified in the assignment,
            // but I assumed it to be logical.
            throw new CannotBookHotelIfNotClientException(this);
        }

        float discount = checkForDiscount(currentHotel, startDateToBook, endDateToBook);
        // a one day booking starts and ends on the same date.
        Period period = Period.between(startDateToBook, endDateToBook);
        int days = period.getDays(); // Days component
        int months = period.getMonths(); // Months component
        int years = period.getYears(); // Years component
        int averageAmountOfDaysInMonth = 30;
        int averageAmountOfDaysInYear = 365;

        int daysBetween = days + months * averageAmountOfDaysInMonth
                + years * averageAmountOfDaysInYear + 1;

        // check for service cost
        int serviceTotalCost = 0;
        for (Service service : services) {
            serviceTotalCost += service.getPrice().intValue();
        }

        // if not enough money for a room and services, throw exception.
        float roomPriceWithDiscounts = daysBetween *
                (roomToBook.getRoomType().getPrice().intValue() + serviceTotalCost) * discount;
        if (money < roomPriceWithDiscounts) {
            throw new NotEnoughMoneyToBookException(money, roomPriceWithDiscounts);
        } else {
            for (Booking existingBooking : currentHotel.getBookings()) {
                // Check if the new booking overlaps with an existing booking, if it does, throw a custom exception.
                if (roomToBook.equals(existingBooking.getRoom()) && (isOverlap(existingBooking.getStartDate(),
                            existingBooking.getEndDate(), startDateToBook, endDateToBook))) {
                        throw new OverlappingBookingException(startDateToBook, endDateToBook);
                }
            }
            // No overlapping bookings found, add the new booking
            Booking newBooking = new Booking.BookingBuilder(currentHotel, roomToBook,
                    this, startDateToBook, endDateToBook)
                    .addServices(services)
                    .build();

            clientBookings.add(newBooking);
            currentHotel.addBooking(newBooking);
            money -= roomPriceWithDiscounts;
            DecimalFormat df = new DecimalFormat("0.00");
            money = Float.parseFloat(df.format(money));
        }
    }


    /**
     * Check for discount.
     * @return - the discount as multiplier. 0 - 1
     */
    public float checkForDiscount(Hotel hotelToCheckFor, LocalDate startDate, LocalDate endDate) {
        float topClientDiscount;
        startDate = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
        endDate = LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth());
        float firstPlaceDiscount = 0.15f;
        float secondPlaceDiscount = 0.10f;
        float thirdPlaceDiscount = 0.05f;
        // discounts from being a top client.
        List<Client> clients = hotelToCheckFor.orderClients();
        if (clients.size() > 3) {
            if (clients.get(0).equals(this)) {
                topClientDiscount = firstPlaceDiscount;
            } else if (clients.get(1).equals(this)) {
                topClientDiscount = secondPlaceDiscount;
            } else if (clients.get(2).equals(this)) {
                topClientDiscount = thirdPlaceDiscount;
            } else {
                topClientDiscount = 0;
            }
        } else {
            topClientDiscount = 0;
        }
        
        // discounts from strategies
        if (hotelToCheckFor.getStrategy() == null) {
            return 1.0f - topClientDiscount;
        } else {
            float strategyDiscount = hotelToCheckFor.getStrategy().getDiscount(hotelToCheckFor, startDate, endDate);
            return 1.0f - topClientDiscount - strategyDiscount;
        }
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
     * @param startDate - the date that the room booking would start.
     * @param endDate - the date that the room booking would end.
     */
    public List<Room> filterRooms(Hotel hotel, LocalDate startDate, LocalDate endDate, Integer budget) {
        ArrayList<Room> roomsInPriceRange = new ArrayList<>();
        startDate = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
        endDate = LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth());
        int daysBetween = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        for (Room room : hotel.getRooms()) {
            float discount = checkForDiscount(hotel, startDate, endDate);
            if (daysBetween * room.getRoomType().getPrice().intValue() * discount <= budget) {
                roomsInPriceRange.add(room);
            }
        }
        return roomsInPriceRange;
    }

    /**
     * Cancel a booking.
     * The client can currently cancel a booking from a hotel, where he has already left.
     * @param hotelToCancel - which hotel.
     * @param dateToCancel - which date. If it overlaps with a booking, cancel it.
     * @throws CannotCancelBookingIfNotBooked - thrown if the client has not booked this.
     */
    public void cancelBooking(Hotel hotelToCancel, Room roomToBook, LocalDate dateToCancel)
            throws CannotCancelBookingIfNotBooked {
        dateToCancel = LocalDate.of(dateToCancel.getYear(), dateToCancel.getMonth(), dateToCancel.getDayOfMonth());
        for (Booking existingBooking : clientBookings) {
            if (existingBooking.getRoom().equals(roomToBook)
                    && isOverlap(existingBooking.getStartDate(),
                    existingBooking.getEndDate(), dateToCancel, dateToCancel)) {
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
     * @param hotelToReview - which hotel to review.
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

    /**
     * Search for rooms in specified hotel.
     * @param hotel - where.
     * @param date - which date.
     */
    public List<Room> searchForRoom(Hotel hotel, LocalDate date) {
        return hotel.searchForFreeRooms(date);
    }

    /**
     * Search for rooms in specified hotel.
     * @param hotel - where.
     * @param type - which type of room.
     */
    public List<Room> searchForRoom(Hotel hotel, Room.RoomType type) {
        return hotel.searchForFreeRooms(type);
    }

    /**
     * Search for rooms in specified hotel.
     * @param hotel - where.
     * @param date - when.
     * @param type - which type of room.
     */
    public List<Room> searchForRoom(Hotel hotel, LocalDate date, Room.RoomType type) {
        return hotel.searchForFreeRooms(date, type);
    }

    /**
     * Search for rooms in specified hotel.
     * @param hotel - where.
     * @param dayOfWeek - when.
     */
    public List<Room> searchForRoom(Hotel hotel, DayOfWeek dayOfWeek, Clock clock) {
        return hotel.searchForFreeRooms(dayOfWeek, clock);
    }
}
