package Part3Functionality;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.booking.Service;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.exceptions.CannotBookHotelIfNotClientException;
import ee.taltech.iti0202.hotel.exceptions.NotEnoughMoneyToBookException;
import ee.taltech.iti0202.hotel.exceptions.OverlappingBookingException;
import ee.taltech.iti0202.hotel.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.ArrayList;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class ClientTest {
    @Test
    void searchForFreeRoomsNoneAvailableWeekDay() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.of(2025, 5, 8), LocalDate.of(2025, 5, 8), new ArrayList<Service>());
        DayOfWeek dayOfWeek = LocalDate.of(2025, 5, 8).getDayOfWeek();

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        // setting the system clock for this test.
        Clock fixedClock = Clock.fixed(Instant.parse("2025-05-06T12:00:00Z"), ZoneId.systemDefault());
        Assertions.assertEquals(expectedFreeRooms,
                client1.searchForRoom(hotel, dayOfWeek, fixedClock));
    }

    @Test
    void searchForFreeRoomsUsingWeekDayOneRoomFree() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.of(2025, 5, 8), LocalDate.of(2025, 5, 8), new ArrayList<Service>());


        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        expectedFreeRooms.add(economyRoom1);
        // setting the system clock for this test.
        Clock fixedClock = Clock.fixed(Instant.parse("2025-05-06T12:00:00Z"), ZoneId.systemDefault());
        Assertions.assertEquals(expectedFreeRooms,
                client1.searchForRoom(hotel, dayOfWeek, fixedClock));
    }
    
    /*
    Client can now directly access all room filtering methods.
    Same tests as in part1functionality but filtered straight using client.
    */
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
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), LocalDate.of(2024, 3, 28), new ArrayList<Service>());
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 27), LocalDate.of(2024, 3, 27), new ArrayList<Service>());
        client2.bookRoom(economyRoom3, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 3, 29), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        expectedFreeRooms.add(economyRoom1);
        expectedFreeRooms.add(economyRoom3);
        Assertions.assertEquals(expectedFreeRooms,
                client1.searchForRoom(hotel, LocalDate.of(2024, 3, 27)));
    }

    @Test
    void searchForFreeRoomsNoneAvailableByDateOnly() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms, client1.searchForRoom(hotel, LocalDate.of(2024, 3, 28)));
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
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 26), LocalDate.of(2024, 3, 26), new ArrayList<Service>());
        client1.bookRoom(economyRoom2, LocalDate.now(), LocalDate.now(), new ArrayList<Service>());
        client2.bookRoom(suiteRoom3, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 3, 29), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        // not currently booked and has the right type.
        expectedFreeRooms.add(economyRoom1);
        Assertions.assertEquals(expectedFreeRooms, client1.searchForRoom(hotel, Room.RoomType.ECONOMYROOM));
    }

    @Test
    void searchForFreeRoomsNoneAvailableByTypeOnly() throws NotEnoughMoneyToBookException, OverlappingBookingException,
            CannotBookHotelIfNotClientException {

        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.now(), LocalDate.now(), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms, client1.searchForRoom(hotel, Room.RoomType.ECONOMYROOM));
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
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), LocalDate.of(2024, 3, 28),  new ArrayList<Service>());
        client1.bookRoom(economyRoom2, LocalDate.of(2024, 3, 27), LocalDate.of(2024, 3, 27), new ArrayList<Service>());
        client2.bookRoom(suiteRoom3, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 3, 29), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        // the only room that is the right type and is not booked on the day that we are checking.
        expectedFreeRooms.add(economyRoom1);
        Assertions.assertEquals(expectedFreeRooms,
                client1.searchForRoom(hotel, LocalDate.of(2024, 3, 27), Room.RoomType.ECONOMYROOM));
    }

    @Test
    void searchForFreeRoomsNoneAvailableDateAndType() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        hotel.addClient(client1);
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28), LocalDate.of(2024, 3, 28), new ArrayList<Service>());

        ArrayList<Room> expectedFreeRooms = new ArrayList<>();
        Assertions.assertEquals(expectedFreeRooms,
                client1.searchForRoom(hotel, LocalDate.of(2024, 3, 28), Room.RoomType.ECONOMYROOM));
    }
}
