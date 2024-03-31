package ee.taltech.iti0202.hotel;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.exceptions.*;
import ee.taltech.iti0202.hotel.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class HotelTest {
    @Test
    void hotelInitializesNoRooms() {
        Hotel hotel = new Hotel("Grand Budapest");

        Assertions.assertEquals(0, hotel.getRooms().size());
    }

    @Test
    void hotelSuitePrice() {
        Hotel hotel = new Hotel("Grand Budapest");
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);

        Assertions.assertEquals(5000, suiteRoom.getRoomType().getPrice());
    }

    @Test
    void hotelFamilyRoomPrice() {
        Hotel hotel = new Hotel("Grand Budapest");
        Room suiteRoom = new Room(hotel, Room.RoomType.FAMILYROOM);

        Assertions.assertEquals(2000, suiteRoom.getRoomType().getPrice());
    }

    @Test
    void hotelEconomyRoomPrice() {
        Hotel hotel = new Hotel("Grand Budapest");
        Room suiteRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);

        Assertions.assertEquals(1000, suiteRoom.getRoomType().getPrice());
    }

    // This was not specified in the assignment, but I thought for it to make sense.
    @Test
    void clientCannotBookRoomIfNotRegisteredAsClient() {
        Hotel hotel = new Hotel("Grand Budapest");
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 5000);

        Assertions.assertThrows(CannotBookHotelIfNotClientException.class,
                () -> client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28)));
    }

    @Test
    void clientBooksFreeRoomHasMoneyWhichGoesAway() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 5000);

        hotel.addClient(client1);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(0, client1.getMoney());
    }

    @Test
    void clientBooksFreeRoomHasNoMoneyThrowsNotEnoughMoneyException() {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 0);

        hotel.addClient(client1);

        Assertions.assertThrows(NotEnoughMoneyToBookException.class,
                () -> client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28)));
    }

    @Test
    void clientBooksBookedRoomThrowsOverLappingBookingException() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Bing", 2000);

        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertThrows(OverlappingBookingException.class,
                () -> client2.bookRoom(economyRoom, LocalDate.of(2024, 3, 28)));
    }

    @Test
    void clientCancelsBookingBookingDisappears() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotCancelBookingIfNotBooked, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        client1.cancelBooking(hotel, economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(0, hotel.getBookings().size());
    }

    @Test
    void clientCancelsBookingGetsRefunded() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotCancelBookingIfNotBooked, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        client1.cancelBooking(hotel, economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(2000, client1.getMoney());
    }

    @Test
    void clientCancelsBookingHasNoBookingThrowsException() {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);
        
        hotel.addClient(client1);

        Assertions.assertThrows(CannotCancelBookingIfNotBooked.class,
                () -> client1.cancelBooking(hotel, economyRoom, LocalDate.of(2024, 3, 28)));
    }

    @Test
    void clientWritesReviewHasNoBookingThrowsException() {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);

        Assertions.assertThrows(CannotWriteReviewIfNotBookedInHotelException.class,
                () -> client1.writeReview(hotel, "Horrible place to stay at", 1));
    }

    @Test
    void clientWritesReviewHasAlreadyWrittenAReviewAboutHotelThrowsException() throws RatingOutOfBoundsException,
            ReviewAlreadyWrittenException, CannotWriteReviewIfNotBookedInHotelException,
            CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException, OverlappingBookingException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.now());
        client1.writeReview(hotel,"Good", 5);

        Assertions.assertThrows(ReviewAlreadyWrittenException.class,
                () -> client1.writeReview(hotel,"Changed my mind", 1));
    }

    @Test
    void clientWritesReviewRatingTooBigThrowsException() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertThrows(RatingOutOfBoundsException.class,
                () -> client1.writeReview(hotel,"Horrible place to stay at", 6));
    }

    @Test
    void clientWritesReviewNegativeRatingThrowsException() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertThrows(RatingOutOfBoundsException.class,
                () -> client1.writeReview(hotel,"Horrible place to stay at", -1));
    }

    @Test
    void hotelOrdersClientsByAmountOfRoomsBooked() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom3 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom4 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Fred", 3000);
        Client client3 = new Client("Kalle", 4000);

        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28));
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 29));
        client2.bookRoom(economyRoom1, LocalDate.of(2024, 3, 30));
        client2.bookRoom(economyRoom2, LocalDate.of(2024, 3, 31));
        client2.bookRoom(economyRoom3, LocalDate.of(2024, 4, 1));
        client3.bookRoom(economyRoom1, LocalDate.of(2024, 4, 2));
        client3.bookRoom(economyRoom2, LocalDate.of(2024, 4, 3));
        client3.bookRoom(economyRoom3, LocalDate.of(2024, 4, 4));
        client3.bookRoom(economyRoom4, LocalDate.of(2024, 4, 5));

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
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Fred", 3000);
        Client client3 = new Client("Kalle", 4000);

        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        // each client has the same amount of bookings.
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28));
        client2.bookRoom(economyRoom1, LocalDate.of(2024, 3, 30));
        client3.bookRoom(economyRoom1, LocalDate.of(2024, 4, 2));
        client3.writeReview(hotel, "Wonderful place to stay at.", 5);
        client2.writeReview(hotel,"Very average", 3);
        client1.writeReview(hotel,"Horrible, I am disgusted", 1);
        ArrayList<Client> expectedOrderedClientList = new ArrayList<>();
        expectedOrderedClientList.add(client3);
        expectedOrderedClientList.add(client2);
        expectedOrderedClientList.add(client1);
        Assertions.assertEquals(expectedOrderedClientList, hotel.orderClients());
    }

    @Test
    void searchForFreeRoomsRightOnesAvailableByDateOnly() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom3 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Fred", 3000);

        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28));
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 27));
        client2.bookRoom(economyRoom3, LocalDate.of(2024, 3, 29));

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        expectedFreeRooms.add(economyRoom1);
        expectedFreeRooms.add(economyRoom3);
        Assertions.assertEquals(expectedFreeRooms,
                hotel.searchForFreeRooms(LocalDate.of(2024, 3, 27)));
    }

    @Test
    void searchForFreeRoomsNoneAvailableByDateOnly() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28));

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms, hotel.searchForFreeRooms(LocalDate.of(2024, 3, 28)));
    }

    @Test
    void searchForFreeRoomsRightOnesAvailableByTypeOnly() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom3 = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Fred", 5000);

        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom1, LocalDate.now());
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 27));
        client2.bookRoom(suiteRoom3, LocalDate.of(2024, 3, 29));

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        expectedFreeRooms.add(economyRoom2);
        Assertions.assertEquals(expectedFreeRooms, hotel.searchForFreeRooms(Room.RoomType.ECONOMYROOM));
    }

    @Test
    void searchForFreeRoomsNoneAvailableByTypeOnly() throws NotEnoughMoneyToBookException, OverlappingBookingException,
            CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.now());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms, hotel.searchForFreeRooms(Room.RoomType.ECONOMYROOM));
    }

    @Test
    void searchForFreeRoomsRightOnesAvailableDateAndType() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom3 = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Fred", 5000);

        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28));
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 27));
        client2.bookRoom(suiteRoom3, LocalDate.of(2024, 3, 29));

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        expectedFreeRooms.add(economyRoom1);
        Assertions.assertEquals(expectedFreeRooms,
                hotel.searchForFreeRooms(LocalDate.of(2024, 3, 27), Room.RoomType.ECONOMYROOM));
    }

    @Test
    void searchForFreeRoomsNoneAvailableDateAndType() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28));

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms,
                hotel.searchForFreeRooms(LocalDate.of(2024, 3, 28), Room.RoomType.ECONOMYROOM));
    }

    @Test
    void clientHasOverviewOfReviews() throws RatingOutOfBoundsException, NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotWriteReviewIfNotBookedInHotelException,
            ReviewAlreadyWrittenException, CannotBookHotelIfNotClientException {
        Hotel hotel1 = new Hotel("Grand Budapest");
        Hotel hotel2 = new Hotel("Grand Delphi");
        Room economyRoom = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel1.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        client1.writeReview(hotel1, "Wonderful place to stay at.", 5);
        hotel2.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        client1.writeReview(hotel2, "Wonderful place to stay at.", 5);
        
        Assertions.assertEquals(2, client1.getReviews().size());
    }

    @Test
    void clientHasOverviewOfBookings() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 6000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(2, client1.getBookings().size());
    }

    @Test
    void hotelHasOverviewOfRooms() {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);

        Assertions.assertEquals(2, hotel.getRooms().size());
    }

    @Test
    void hotelHasOverviewOfClients() {
        Hotel hotel = new Hotel("Grand Budapest");
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Heinrich", 5000);

        hotel.addClient(client1);
        hotel.addClient(client2);

        Assertions.assertEquals(2, hotel.getClients().size());
    }

    @Test
    void hotelHasOverviewOfBookings() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 6000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(2, hotel.getBookings().size());
    }

    @Test
    void hotelHasOverviewOfReviews() throws RatingOutOfBoundsException, NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotWriteReviewIfNotBookedInHotelException,
            ReviewAlreadyWrittenException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Mumbai");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Sanjay", 2000);
        Client client2 = new Client("Jeff", 5000);


        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        hotel.addClient(client2);
        client2.bookRoom(economyRoom, LocalDate.of(2024, 3, 29));
        client1.writeReview(hotel, "I found a snake in my toilet.", 1);
        client2.writeReview(hotel, "My name is Jeff.", 5);

        Assertions.assertEquals(2, hotel.getReviews().size());
    }

    @Test
    void hotelHasOverviewOfAverageReviewScore() throws NotEnoughMoneyToBookException, OverlappingBookingException,
            RatingOutOfBoundsException, CannotWriteReviewIfNotBookedInHotelException,
            ReviewAlreadyWrittenException, CannotBookHotelIfNotClientException {
        Hotel hotel = new Hotel("Grand Budapest");
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Fred", 5000);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        hotel.addClient(client2);
        client2.bookRoom(economyRoom, LocalDate.of(2024, 3, 29));
        client1.writeReview(hotel, "I found a snake in my toilet.", 1);
        client2.writeReview(hotel, "Great Interior design.", 5);

        Assertions.assertEquals(3, hotel.getAverageRating());
    }
}
