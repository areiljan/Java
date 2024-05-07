package secondpartfunctionality;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.booking.Service;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.exceptions.CannotBookHotelIfNotClientException;
import ee.taltech.iti0202.hotel.exceptions.CannotWriteReviewIfNotBookedInHotelException;
import ee.taltech.iti0202.hotel.exceptions.NotEnoughMoneyToBookException;
import ee.taltech.iti0202.hotel.exceptions.OverlappingBookingException;
import ee.taltech.iti0202.hotel.exceptions.RatingOutOfBoundsException;
import ee.taltech.iti0202.hotel.exceptions.ReviewAlreadyWrittenException;
import ee.taltech.iti0202.hotel.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClientTest {
    @Test
    void clientBooksFreeRoomNoDiscountBecauseNotEnoughClients() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);

        Client client1 = new Client("Joonas", 6000, reservationSystem);
        Client client2 = new Client("Toomas", 5000, reservationSystem);
        Client client3 = new Client("Soomas", 5000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28),
                LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 29),
                LocalDate.of(2024, 3, 29), new ArrayList<Service>());

        Assertions.assertEquals(0, client1.getMoney());
    }

    @Test
    void clientBooksFreeRoomWithDiscountBecauseFirstInOrderingSystem() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotBookHotelIfNotClientException,
            RatingOutOfBoundsException, ReviewAlreadyWrittenException, CannotWriteReviewIfNotBookedInHotelException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);

        Client client2 = new Client("Toomas", 5000, reservationSystem);
        Client client3 = new Client("Soomas", 5000, reservationSystem);
        Client client1 = new Client("Joonas", 6000, reservationSystem);
        Client client4 = new Client("Soomas", 5000, reservationSystem);
        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        hotel.addClient(client4);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28),
                LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.writeReview(hotel, "bad", 1);
        client2.bookRoom(suiteRoom, LocalDate.of(2024, 3, 29),
                LocalDate.of(2024, 3, 29), new ArrayList<Service>());
        client2.writeReview(hotel, "bad", 1);
        client3.bookRoom(suiteRoom, LocalDate.of(2024, 3, 30),
                LocalDate.of(2024, 3, 30), new ArrayList<Service>());
        client3.writeReview(hotel, "bad", 1);
        
        // now eligible to get a discount of 15%.
        client4.bookRoom(suiteRoom, LocalDate.of(2024, 3, 31),
                LocalDate.of(2024, 3, 31), new ArrayList<Service>());
        int client4MoneyLeft = 750;
        Assertions.assertEquals(client4MoneyLeft, client4.getMoney());
    }

    @Test
    void clientBooksFreeRoomWithDiscountBecauseSecondInOrderingSystem() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotBookHotelIfNotClientException,
            RatingOutOfBoundsException, ReviewAlreadyWrittenException, CannotWriteReviewIfNotBookedInHotelException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);

        Client client2 = new Client("Toomas", 5000, reservationSystem);
        Client client3 = new Client("Soomas", 5000, reservationSystem);
        Client client1 = new Client("Joonas", 10000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28),
                LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.writeReview(hotel, "bad", 1);
        client2.bookRoom(suiteRoom, LocalDate.of(2024, 3, 29),
                LocalDate.of(2024, 3, 29), new ArrayList<Service>());
        client2.writeReview(hotel, "bad", 1);
        client3.bookRoom(suiteRoom, LocalDate.of(2024, 3, 30),
                LocalDate.of(2024, 3, 30), new ArrayList<Service>());
        client3.writeReview(hotel, "bad", 1);

        Client client4 = new Client("Soomas", 5000, reservationSystem);
        hotel.addClient(client4);
        // now eligible to get a discount of 10%.
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 31),
                LocalDate.of(2024, 3, 31), new ArrayList<Service>());
        Assertions.assertEquals(500, client1.getMoney());
    }

    @Test
    void clientBooksFreeRoomWithDiscountBecauseThirdInOrderingSystem() throws OverlappingBookingException,
            NotEnoughMoneyToBookException, CannotBookHotelIfNotClientException,
            RatingOutOfBoundsException, ReviewAlreadyWrittenException, CannotWriteReviewIfNotBookedInHotelException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);

        Client client2 = new Client("Toomas", 10000, reservationSystem);
        Client client3 = new Client("Soomas", 5000, reservationSystem);
        Client client1 = new Client("Joonas", 5000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 25),
                LocalDate.of(2024, 3, 25), new ArrayList<Service>());
        client1.writeReview(hotel, "bad", 1);
        client2.bookRoom(suiteRoom, LocalDate.of(2024, 3, 26),
                LocalDate.of(2024, 3, 26), new ArrayList<Service>());
        client2.writeReview(hotel, "bad", 1);
        client3.bookRoom(suiteRoom, LocalDate.of(2024, 3, 27),
                LocalDate.of(2024, 3, 27), new ArrayList<Service>());
        client3.writeReview(hotel, "bad", 1);

        Client client4 = new Client("Soomas", 5000, reservationSystem);
        hotel.addClient(client4);
        // now eligible to get a discount of 5%.
        client2.bookRoom(suiteRoom, LocalDate.of(2024, 3, 31),
                LocalDate.of(2024, 3, 31), new ArrayList<Service>());
        Assertions.assertEquals(250, client2.getMoney());
    }

    @Test
    void clientFiltersHotelsByLocation() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Hotel hotel2 = new Hotel("Grand Roma", "Italy", "Rome", reservationSystem);
        Hotel hotel3 = new Hotel("Grand Vatican", "Italy", "Rome", reservationSystem);
        Hotel hotel4 = new Hotel("Grand Karlsruhe", "Germany", "Karlsruhe", reservationSystem);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        ArrayList<Hotel> expectedOrderedHotelList = new ArrayList<>();
        expectedOrderedHotelList.add(hotel2);
        expectedOrderedHotelList.add(hotel3);
        Assertions.assertEquals(expectedOrderedHotelList, client1.filterHotels("Italy", "Rome"));
    }

    @Test
    void clientFiltersHotelsByRating() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException,
            RatingOutOfBoundsException, ReviewAlreadyWrittenException, CannotWriteReviewIfNotBookedInHotelException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Hotel hotel2 = new Hotel("Grand Roma", "Italy", "Rome", reservationSystem);
        Hotel hotel3 = new Hotel("Grand Vatican", "Italy", "Rome", reservationSystem);
        Hotel hotel4 = new Hotel("Grand Karlsruhe", "Germany", "Karlsruhe", reservationSystem);
        Room suiteRoom = new Room(hotel1, Room.RoomType.SUITE);
        Room economyRoom = new Room(hotel2, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 5000, reservationSystem);
        Client client2 = new Client("Toomas", 5000, reservationSystem);
        Client client3 = new Client("Soomas", 5000, reservationSystem);

        hotel1.addClient(client1);
        hotel2.addClient(client2);
        hotel3.addClient(client3);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28),
                LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.writeReview(hotel1, "Best", 4);
        client2.bookRoom(suiteRoom, LocalDate.of(2024, 3, 29),
                LocalDate.of(2024, 3, 29), new ArrayList<Service>());
        client2.writeReview(hotel2, "Worst", 1);
        client3.bookRoom(suiteRoom, LocalDate.of(2024, 3, 30),
                LocalDate.of(2024, 3, 30), new ArrayList<Service>());
        client3.writeReview(hotel3, "Mid", 3);


        ArrayList<Hotel> expectedOrderedHotelList = new ArrayList<>();
        expectedOrderedHotelList.add(hotel1);
        expectedOrderedHotelList.add(hotel3);
        Assertions.assertEquals(expectedOrderedHotelList, client1.filterHotels(3));
    }

    @Test
    void clientFiltersHotelsByLocationAndRating() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException,
            RatingOutOfBoundsException, ReviewAlreadyWrittenException, CannotWriteReviewIfNotBookedInHotelException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Hotel hotel2 = new Hotel("Grand Roma", "Italy", "Rome", reservationSystem);
        Hotel hotel3 = new Hotel("Grand Vatican", "Italy", "Rome", reservationSystem);
        Hotel hotel4 = new Hotel("Grand Karlsruhe", "Germany", "Karlsruhe", reservationSystem);
        Room suiteRoom = new Room(hotel1, Room.RoomType.SUITE);
        Room economyRoom = new Room(hotel2, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 5000, reservationSystem);
        Client client2 = new Client("Toomas", 5000, reservationSystem);
        Client client3 = new Client("Soomas", 5000, reservationSystem);

        hotel1.addClient(client1);
        hotel2.addClient(client2);
        hotel3.addClient(client3);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28),
                LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.writeReview(hotel1, "Best", 4);
        client2.bookRoom(suiteRoom, LocalDate.of(2024, 3, 29),
                LocalDate.of(2024, 3, 29), new ArrayList<Service>());
        client2.writeReview(hotel2, "Worst", 1);
        client3.bookRoom(suiteRoom, LocalDate.of(2024, 3, 30),
                LocalDate.of(2024, 3, 30), new ArrayList<Service>());
        client3.writeReview(hotel3, "Mid", 3);


        ArrayList<Hotel> expectedOrderedHotelList = new ArrayList<>();
        expectedOrderedHotelList.add(hotel1);
        Assertions.assertEquals(expectedOrderedHotelList, client1.filterHotels(3, "Hungary", "Budapest"));
    }

    @Test
    void clientFiltersRoomsByPrice() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Room suiteRoom2 = new Room(hotel, Room.RoomType.SUITE);
        Room familyRoom = new Room(hotel, Room.RoomType.FAMILYROOM);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 5000, reservationSystem);
        hotel.addClient(client1);

        ArrayList<Room> expectedRoomList = new ArrayList<>();
        expectedRoomList.add(familyRoom);
        expectedRoomList.add(economyRoom);

        Assertions.assertEquals(expectedRoomList, client1.filterRooms(hotel,
                LocalDate.of(2024, 3, 30), LocalDate.of(2024, 3, 30), 4000));
    }

    @Test
    void clientBooksRoomWithAllServicesSubtractsMoney() throws CannotBookHotelIfNotClientException,
            NotEnoughMoneyToBookException, OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 7000, reservationSystem);
        hotel.addClient(client1);

        ArrayList<Service> servicesForClient1 = new ArrayList<>();
        servicesForClient1.add(Service.BREAKFAST);
        servicesForClient1.add(Service.DINNER);
        servicesForClient1.add(Service.CLEANING);
        servicesForClient1.add(Service.JACUZZI);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 12), servicesForClient1);

        Assertions.assertEquals(800, client1.getMoney());
    }

    @Test
    void clientBooksRoomWithAllServicesHasNoMoneyThrowsException() throws CannotBookHotelIfNotClientException,
            NotEnoughMoneyToBookException, OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        hotel.addClient(client1);

        ArrayList<Service> servicesForClient1 = new ArrayList<>();
        servicesForClient1.add(Service.BREAKFAST);
        servicesForClient1.add(Service.DINNER);
        servicesForClient1.add(Service.CLEANING);
        servicesForClient1.add(Service.JACUZZI);

        Assertions.assertThrows(NotEnoughMoneyToBookException.class,
                () -> client1.bookRoom(economyRoom, LocalDate.of(2024, 12, 12),
                        LocalDate.of(2024, 12, 12), servicesForClient1));
    }

    @Test
    void clientHasAccessToServicesThroughBooking() throws CannotBookHotelIfNotClientException,
            NotEnoughMoneyToBookException, OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room suiteRoom = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 7000, reservationSystem);
        hotel.addClient(client1);

        ArrayList<Service> servicesForClient1 = new ArrayList<>();
        servicesForClient1.add(Service.BREAKFAST);
        servicesForClient1.add(Service.DINNER);
        servicesForClient1.add(Service.CLEANING);
        servicesForClient1.add(Service.JACUZZI);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 12), servicesForClient1);

        Booking booking = client1.getBookings().get(0);
        Assertions.assertEquals(servicesForClient1,
                booking.getServices());
    }
}
