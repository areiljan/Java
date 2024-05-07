package thirdpartfunctionality;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.booking.Service;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.exceptions.CannotBookHotelIfNotClientException;
import ee.taltech.iti0202.hotel.exceptions.NotEnoughMoneyToBookException;
import ee.taltech.iti0202.hotel.exceptions.OverlappingBookingException;
import ee.taltech.iti0202.hotel.room.Room;
import ee.taltech.iti0202.hotel.strategies.LowSeasonStrategy;
import ee.taltech.iti0202.hotel.strategies.Strategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelTest {
    @Test
    void hotelOrdersClientsByServiceAverageCostAsLastParameter() throws NotEnoughMoneyToBookException,
            OverlappingBookingException, CannotBookHotelIfNotClientException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room economyRoom1 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom2 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom3 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Room economyRoom4 = new Room(hotel, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 10000, reservationSystem);
        Client client2 = new Client("Fred", 10000, reservationSystem);
        Client client3 = new Client("Kalle", 10000, reservationSystem);

        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        ArrayList<Service> client1Services = new ArrayList<>();
        ArrayList<Service> client2Services = new ArrayList<>();
        ArrayList<Service> client3Services = new ArrayList<>();
        // client 1 has the lowest average cost.
        client1Services.add(Service.BREAKFAST);
        client1Services.add(Service.BREAKFAST); //average cost 200
        client1Services.add(Service.BREAKFAST);
        // client 2 has the middle average cost.
        client2Services.add(Service.DINNER); // average cost 350
        client2Services.add(Service.JACUZZI);
        // client 3 has the highest average cost, therefore he is the first one.
        client3Services.add(Service.JACUZZI); // average cost 400
        client1.bookRoom(economyRoom1, LocalDate.of(2024, 3, 28),
                LocalDate.of(2024, 3, 28), client1Services);
        client2.bookRoom(economyRoom2, LocalDate.of(2024, 3, 29),
                LocalDate.of(2024, 3, 29), client2Services);
        client3.bookRoom(economyRoom2, LocalDate.of(2024, 3, 30),
                LocalDate.of(2024, 3, 30), client3Services);

        ArrayList<Client> expectedOrderedClientList = new ArrayList<>();
        expectedOrderedClientList.add(client3);
        expectedOrderedClientList.add(client2);
        expectedOrderedClientList.add(client1);
        Assertions.assertEquals(expectedOrderedClientList, hotel.orderClients());
    }

    @Test
    void canSetStrategy() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);

        Strategy lowSeasonStrategy = new LowSeasonStrategy();
        Strategy longBookingStrategy = new LowSeasonStrategy();
        hotel1.setStrategy(lowSeasonStrategy);
        hotel1.setStrategy(longBookingStrategy);

        Assertions.assertEquals(hotel1.getStrategy(), longBookingStrategy);
    }
}
