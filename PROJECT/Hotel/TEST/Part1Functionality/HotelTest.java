package Part1Functionality;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.booking.Service;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.exceptions.*;
import ee.taltech.iti0202.hotel.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

// these tests were for part one
class HotelTest {
    @Test
    void hotelInitializesNoRooms() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);

        Assertions.assertEquals(0, hotel.getRooms().size());
    }

    @Test
    void hotelSuitePrice() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);

        Assertions.assertEquals(BigDecimal.valueOf(5000), suiteRoom.getRoomType().getPrice());
    }

    @Test
    void hotelFamilyRoomPrice() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room familyRoom = new Room(hotel, Room.RoomType.FAMILYROOM);

        Assertions.assertEquals(BigDecimal.valueOf(2000), familyRoom.getRoomType().getPrice());
    }

    @Test
    void hotelEconomyRoomPrice() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);

        Assertions.assertEquals(BigDecimal.valueOf(1000), economyRoom.getRoomType().getPrice());
    }

    @Test
    void hotelOrdersClientsByAmountOfRoomsBooked() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom3 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom4 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Fred", 3000, reservationSystem);
        Client client3 = new Client("Kalle", 4000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 29), new ArrayList<Service>());
        client2.bookRoom(economyRoom1, LocalDate.of(2024, 3, 30), new ArrayList<Service>());
        client2.bookRoom(economyRoom2, LocalDate.of(2024, 3, 31), new ArrayList<Service>());
        client2.bookRoom(economyRoom3, LocalDate.of(2024, 4, 1), new ArrayList<Service>());
        client3.bookRoom(economyRoom1, LocalDate.of(2024, 4, 2), new ArrayList<Service>());
        client3.bookRoom(economyRoom2, LocalDate.of(2024, 4, 3), new ArrayList<Service>());
        client3.bookRoom(economyRoom3, LocalDate.of(2024, 4, 4), new ArrayList<Service>());
        client3.bookRoom(economyRoom4, LocalDate.of(2024, 4, 5), new ArrayList<Service>());

        ArrayList<Client> expectedOrderedClientList = new ArrayList<>();
        expectedOrderedClientList.add(client1);
        expectedOrderedClientList.add(client2);
        expectedOrderedClientList.add(client3);
        Assertions.assertEquals(expectedOrderedClientList, hotel.orderClients());
    }

    @Test
    void hotelOrdersClientsByRatingGivenByClient() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException,
            RatingOutOfBoundsException, ReviewAlreadyWrittenException, CannotWriteReviewIfNotBookedInHotelException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Fred", 3000, reservationSystem);
        Client client3 = new Client("Kalle", 4000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        // each client has the same amount of bookings.
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client2.bookRoom(economyRoom1, LocalDate.of(2024, 3, 30), new ArrayList<Service>());
        client3.bookRoom(economyRoom1, LocalDate.of(2024, 4, 2), new ArrayList<Service>());
        client3.writeReview(hotel, "Wonderful place to stay at.", 5);
        client2.writeReview(hotel, "Very average", 3);
        client1.writeReview(hotel, "Horrible, I am disgusted", 1);
        ArrayList<Client> expectedOrderedClientList = new ArrayList<>();
        expectedOrderedClientList.add(client3);
        expectedOrderedClientList.add(client2);
        expectedOrderedClientList.add(client1);
        Assertions.assertEquals(expectedOrderedClientList, hotel.orderClients());
    }

    @Test
    void searchForFreeRoomsRightOnesAvailableByDateOnly() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom3 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Fred", 3000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 27), new ArrayList<Service>());
        client2.bookRoom(economyRoom3, LocalDate.of(2024, 3, 29), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        expectedFreeRooms.add(economyRoom1);
        expectedFreeRooms.add(economyRoom3);
        Assertions.assertEquals(expectedFreeRooms,
                hotel.searchForFreeRooms(LocalDate.of(2024, 3, 27)));
    }

    @Test
    void searchForFreeRoomsNoneAvailableByDateOnly() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms, hotel.searchForFreeRooms(LocalDate.of(2024, 3, 28)));
    }

    @Test
    void searchForFreeRoomsRightOnesAvailableByTypeOnly() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom3 = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Fred", 5000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom1, LocalDate.now(), new ArrayList<Service>());
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 27), new ArrayList<Service>());
        client2.bookRoom(suiteRoom3, LocalDate.of(2024, 3, 29), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        expectedFreeRooms.add(economyRoom2);
        Assertions.assertEquals(expectedFreeRooms, hotel.searchForFreeRooms(Room.RoomType.ECONOMYROOM));
    }

    @Test
    void searchForFreeRoomsNoneAvailableByTypeOnly() throws NotEnoughMoneyToBookException, OverlappingBookingException,
            CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.now(), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms, hotel.searchForFreeRooms(Room.RoomType.ECONOMYROOM));
    }

    @Test
    void searchForFreeRoomsRightOnesAvailableDateAndType() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom3 = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Fred", 5000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 27), new ArrayList<Service>());
        client2.bookRoom(suiteRoom3, LocalDate.of(2024, 3, 29), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        expectedFreeRooms.add(economyRoom1);
        Assertions.assertEquals(expectedFreeRooms,
                hotel.searchForFreeRooms(LocalDate.of(2024, 3, 27), Room.RoomType.ECONOMYROOM));
    }

    @Test
    void searchForFreeRoomsNoneAvailableDateAndType() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms,
                hotel.searchForFreeRooms(LocalDate.of(2024, 3, 28), Room.RoomType.ECONOMYROOM));
    }

    @Test
    void hotelHasOverviewOfRooms() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);

        Assertions.assertEquals(2, hotel.getRooms().size());
    }

    @Test
    void hotelHasOverviewOfClients() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Heinrich", 5000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);

        Assertions.assertEquals(2, hotel.getClients().size());
    }

    @Test
    void hotelHasOverviewOfBookings() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 6000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        Assertions.assertEquals(2, hotel.getBookings().size());
    }

    @Test
    void hotelHasOverviewOfReviews() throws RatingOutOfBoundsException, NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotWriteReviewIfNotBookedInHotelException,
            ReviewAlreadyWrittenException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Sanjay", 2000, reservationSystem);
        Client client2 = new Client("Jeff", 5000, reservationSystem);


        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        hotel.addClient(client2);
        client2.bookRoom(economyRoom, LocalDate.of(2024, 3, 29), new ArrayList<Service>());
        client1.writeReview(hotel, "I found a snake in my toilet.", 1);
        client2.writeReview(hotel, "My name is Jeff.", 5);

        Assertions.assertEquals(2, hotel.getReviews().size());
    }

    @Test
    void hotelHasOverviewOfAverageReviewScore() throws NotEnoughMoneyToBookException, OverlappingBookingException,
            RatingOutOfBoundsException, CannotWriteReviewIfNotBookedInHotelException,
            ReviewAlreadyWrittenException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Fred", 5000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        hotel.addClient(client2);
        client2.bookRoom(economyRoom, LocalDate.of(2024, 3, 29), new ArrayList<Service>());
        client1.writeReview(hotel, "I found a snake in my toilet.", 1);
        client2.writeReview(hotel, "Great Interior design.", 5);

        Assertions.assertEquals(3, hotel.getAverageRating());
    }
}
