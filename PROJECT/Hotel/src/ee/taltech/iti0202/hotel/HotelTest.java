package ee.taltech.iti0202.hotel;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.room.CannotCancelBookingIfNotBooked;
import ee.taltech.iti0202.hotel.room.NotEnoughMoneyToBookException;
import ee.taltech.iti0202.hotel.room.OverlappingBookingException;
import ee.taltech.iti0202.hotel.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class HotelTest {
    @Test
    void hotelInitializesNoRooms() {
        Hotel hotel = new Hotel();

        Assertions.assertEquals(0, hotel.getRooms().size());
    }

    @Test
    void hotelDoNotAddSameRoomToSameHotelTwice() {
        Hotel hotel = new Hotel();
        Room suiteRoom = new Room(Room.RoomType.SUITE);

        hotel.addRoom(suiteRoom);
        hotel.addRoom(suiteRoom);

        Assertions.assertEquals(1, hotel.getRooms().size());
    }

    @Test
    void hotelDoNotAddSameRoomToDifferentHotel() {
        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        Room suiteRoom = new Room(Room.RoomType.SUITE);

        hotel1.addRoom(suiteRoom);
        hotel2.addRoom(suiteRoom);

        Assertions.assertEquals(0, hotel2.getRooms().size());
    }

    @Test
    void hotelSuitePrice() {
        Hotel hotel = new Hotel();
        Room suiteRoom = new Room(Room.RoomType.SUITE);

        Assertions.assertEquals(5000, suiteRoom.getRoomType().getPrice());
    }

    @Test
    void hotelFamilyRoomPrice() {
        Hotel hotel = new Hotel();
        Room suiteRoom = new Room(Room.RoomType.FAMILYROOM);

        Assertions.assertEquals(2000, suiteRoom.getRoomType().getPrice());
    }

    @Test
    void hotelEconomyRoomPrice() {
        Hotel hotel = new Hotel();
        Room suiteRoom = new Room(Room.RoomType.ECONOMYROOM);

        Assertions.assertEquals(1000, suiteRoom.getRoomType().getPrice());
    }

    @Test
    void clientBooksFreeRoomHasMoneyWhichGoesAway() throws OverlappingBookingException, NotEnoughMoneyToBookException {
        Hotel hotel = new Hotel();
        Room suiteRoom = new Room(Room.RoomType.SUITE);
        Client client1 = new Client("Joonas", 5000);

        hotel.addRoom(suiteRoom);
        hotel.addClient(client1);
        client1.bookRoom(suiteRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(0, client1.getMoney());
    }

    @Test
    void clientBooksFreeRoomHasNoMoneyThrowsNotEnoughMoneyException() throws NotEnoughMoneyToBookException {
        Hotel hotel = new Hotel();
        Room economyRoom = new Room(Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 0);

        hotel.addRoom(economyRoom);
        hotel.addClient(client1);

        Assertions.assertThrows(NotEnoughMoneyToBookException.class, () -> client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28)));
    }

    @Test
    void clientBooksBookedRoomThrowsOverLappingBookingException() throws OverlappingBookingException, NotEnoughMoneyToBookException {
        Hotel hotel = new Hotel();
        Room economyRoom = new Room(Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);
        Client client2 = new Client("Bing", 2000);

        hotel.addRoom(economyRoom);
        hotel.addClient(client1);
        hotel.addClient(client2);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertThrows(OverlappingBookingException.class, () -> client2.bookRoom(economyRoom, LocalDate.of(2024, 3, 28)));
    }

    @Test
    void clientCancelsBooking() throws OverlappingBookingException, NotEnoughMoneyToBookException, CannotCancelBookingIfNotBooked {
        Hotel hotel = new Hotel();
        Room economyRoom = new Room(Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addRoom(economyRoom);
        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        client1.cancelBooking(economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(0, economyRoom.getBookings().size());
    }

    @Test
    void clientCancelsBookingGetsRefunded() throws OverlappingBookingException, NotEnoughMoneyToBookException, CannotCancelBookingIfNotBooked {
        Hotel hotel = new Hotel();
        Room economyRoom = new Room(Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addRoom(economyRoom);
        hotel.addClient(client1);
        client1.bookRoom(economyRoom, LocalDate.of(2024, 3, 28));
        client1.cancelBooking(economyRoom, LocalDate.of(2024, 3, 28));

        Assertions.assertEquals(2000, client1.getMoney());
    }

    @Test
    void clientCancelsBookingHasNoBooking() throws OverlappingBookingException, NotEnoughMoneyToBookException, CannotCancelBookingIfNotBooked {
        Hotel hotel = new Hotel();
        Room economyRoom = new Room(Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000);

        hotel.addRoom(economyRoom);
        hotel.addClient(client1);

        Assertions.assertThrows(CannotCancelBookingIfNotBooked.class, () -> client1.cancelBooking(economyRoom, LocalDate.of(2024, 3, 28)));
    }
}
