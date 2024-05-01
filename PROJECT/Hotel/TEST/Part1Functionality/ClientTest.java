package Part1Functionality;

import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.booking.Service;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.exceptions.*;
import ee.taltech.iti0202.hotel.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class ClientTest {
    @Test
    void clientCannotBookRoomIfNotRegisteredAsClient() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 5000, reservationSystem);

        Assertions.assertThrows(CannotBookHotelIfNotClientException.class,
                () -> client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>()));
    }

    @Test
    void clientBooksFreeRoomHasMoneyWhichGoesAway() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 5000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        Assertions.assertEquals(0, client1.getMoney());
    }

    @Test
    void clientBooksFreeRoomHasNoMoneyThrowsNotEnoughMoneyException() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 0, reservationSystem);

        hotel.addClient(client1);

        Assertions.assertThrows(NotEnoughMoneyToBookException.class,
                () -> client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>()));
    }

    @Test
    void clientBooksBookedRoomThrowsOverLappingBookingException() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Bing", 2000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        Assertions.assertThrows(OverlappingBookingException.class,
                () -> client2.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>()));
    }

    @Test
    void clientCancelsBookingBookingDisappears() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotCancelBookingIfNotBooked, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.cancelBooking(hotel, economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(0, hotel.getBookings().size());
    }

    @Test
    void clientCancelsBookingGetsRefunded() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotCancelBookingIfNotBooked, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.cancelBooking(hotel, economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(2000, client1.getMoney());
    }

    @Test
    void clientCancelsBookingHasNoBookingThrowsException() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);

        Assertions.assertThrows(CannotCancelBookingIfNotBooked.class,
                () -> client1.cancelBooking(hotel, economyRoom, LocalDate.of(2024, 3, 28)));
    }

    @Test
    void clientWritesReviewHasNoBookingThrowsException() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);

        Assertions.assertThrows(CannotWriteReviewIfNotBookedInHotelException.class,
                () -> client1.writeReview(hotel, "Horrible place to stay at", 1));
    }

    @Test
    void clientWritesReviewHasAlreadyWrittenAReviewAboutHotelThrowsException() throws RatingOutOfBoundsException,
            ReviewAlreadyWrittenException, CannotWriteReviewIfNotBookedInHotelException,
            CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException, OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.now(), new ArrayList<Service>());
        client1.writeReview(hotel, "Good", 5);

        Assertions.assertThrows(ReviewAlreadyWrittenException.class,
                () -> client1.writeReview(hotel, "Changed my mind", 1));
    }

    @Test
    void clientWritesReviewRatingTooBigThrowsException() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        Assertions.assertThrows(RatingOutOfBoundsException.class,
                () -> client1.writeReview(hotel, "Horrible place to stay at", 6));
    }

    @Test
    void clientWritesReviewNegativeRatingThrowsException() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        Assertions.assertThrows(RatingOutOfBoundsException.class,
                () -> client1.writeReview(hotel, "Horrible place to stay at", -1));
    }

    @Test
    void clientHasOverviewOfReviews() throws RatingOutOfBoundsException, NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotWriteReviewIfNotBookedInHotelException,
            ReviewAlreadyWrittenException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Hotel hotel2 = new Hotel("Grand Delphi", "Greece", "Delphi", reservationSystem);
        Room economyRoom = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel1.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.writeReview(hotel1, "Wonderful place to stay at.", 5);
        hotel2.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.writeReview(hotel2, "Wonderful place to stay at.", 5);

        Assertions.assertEquals(2, client1.getReviews().size());
    }

    @Test
    void clientHasOverviewOfBookings() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 6000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        Assertions.assertEquals(2, client1.getBookings().size());
    }
}