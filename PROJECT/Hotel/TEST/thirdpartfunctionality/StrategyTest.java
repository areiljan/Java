package thirdpartfunctionality;

import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.exceptions.CannotBookHotelIfNotClientException;
import ee.taltech.iti0202.hotel.exceptions.NotEnoughMoneyToBookException;
import ee.taltech.iti0202.hotel.exceptions.OverlappingBookingException;
import ee.taltech.iti0202.hotel.room.Room;
import ee.taltech.iti0202.hotel.strategies.CombinedStrategy;
import ee.taltech.iti0202.hotel.strategies.LongBookingStrategy;
import ee.taltech.iti0202.hotel.strategies.LowSeasonStrategy;
import ee.taltech.iti0202.hotel.strategies.Strategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class StrategyTest {
    @Test
    void lowSeasonStrategyDiscountsBasedOnBookingNumberNoBookingsMaximumDiscount()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException,
            OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);

        Strategy lowSeasonStrategy = new LowSeasonStrategy();
        hotel1.setStrategy(lowSeasonStrategy);
        hotel1.addClient(client1);
        // two day booking
        client1.bookRoom(room1, LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 13), new ArrayList<>());

        // get maximum discount of 20 percent.
        // the price of an economyroom is 1000 per day, so that a 20% discount equals to 200 euros saved per day.
        int client1ExpectedMoney = 400;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void lowSeasonStrategyDiscountsBasedOnBookingHotelFullyBookedMinimumDiscountNoTopClientDiscount()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException,
            OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Toomas", 100000, reservationSystem);

        Strategy lowSeasonStrategy = new LowSeasonStrategy();
        hotel1.setStrategy(lowSeasonStrategy);
        hotel1.addClient(client1);
        hotel1.addClient(client2);
        // two day booking
        client2.bookRoom(room1, LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 12), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 13),
                LocalDate.of(2022, 12, 13), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 14),
                LocalDate.of(2022, 12, 14), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 15),
                LocalDate.of(2022, 12, 15), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 16),
                LocalDate.of(2022, 12, 16), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 17),
                LocalDate.of(2022, 12, 17), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 18),
                LocalDate.of(2022, 12, 18), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 19),
                LocalDate.of(2022, 12, 19), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 20),
                LocalDate.of(2022, 12, 20), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 21),
                LocalDate.of(2022, 12, 21), new ArrayList<>());
        // all rooms are booked by the same client to not mess with the top client discount system.

        // the client will now only have a 10 percent discount,
        // because of 10 bookings each driving the price down one percent.
        client1.bookRoom(room1, LocalDate.of(2022, 12, 25),
                LocalDate.of(2022, 12, 26), new ArrayList<>());
        // the price of an economyroom is 1000 per day, so that a 10% discount equals to 100 euros saved per day.
        int client1ExpectedMoney = 200;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void lowSeasonStrategyDiscountsBasedOnBookingHotelFullyBookedMinimumDiscountWithTopClientDiscount()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException, OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 2000, reservationSystem);
        Client client2 = new Client("Toomas", 100000, reservationSystem);
        Client client3 = new Client("Paul", 20000, reservationSystem);
        Client client4 = new Client("Peeter", 100000, reservationSystem);
        Client client5 = new Client("Tauno", 20000, reservationSystem);
        Client client6 = new Client("Rauno", 100000, reservationSystem);

        Strategy lowSeasonStrategy = new LowSeasonStrategy();
        hotel1.setStrategy(lowSeasonStrategy);
        hotel1.addClient(client1);
        hotel1.addClient(client2);
        hotel1.addClient(client3);
        hotel1.addClient(client4);
        hotel1.addClient(client5);
        hotel1.addClient(client6);
        client2.bookRoom(room1, LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 12), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 13),
                LocalDate.of(2022, 12, 13), new ArrayList<>());
        client3.bookRoom(room1, LocalDate.of(2022, 12, 14),
                LocalDate.of(2022, 12, 14), new ArrayList<>());
        client3.bookRoom(room1, LocalDate.of(2022, 12, 15),
                LocalDate.of(2022, 12, 15), new ArrayList<>());
        client4.bookRoom(room1, LocalDate.of(2022, 12, 16),
                LocalDate.of(2022, 12, 16), new ArrayList<>());
        client4.bookRoom(room1, LocalDate.of(2022, 12, 17),
                LocalDate.of(2022, 12, 17), new ArrayList<>());
        client5.bookRoom(room1, LocalDate.of(2022, 12, 18),
                LocalDate.of(2022, 12, 18), new ArrayList<>());
        client5.bookRoom(room1, LocalDate.of(2022, 12, 19),
                LocalDate.of(2022, 12, 19), new ArrayList<>());
        client6.bookRoom(room1, LocalDate.of(2022, 12, 20),
                LocalDate.of(2022, 12, 20), new ArrayList<>());
        client6.bookRoom(room1, LocalDate.of(2022, 12, 21),
                LocalDate.of(2022, 12, 21), new ArrayList<>());


        // the client will now have a 25 percent discount, 10% from the strategy and 15% from being a top client.
        client1.bookRoom(room1, LocalDate.of(2022, 12, 25),
                LocalDate.of(2022, 12, 26), new ArrayList<>());
        // the price of an economyroom is 1000 per day, so that a 25% discount equals to 250 euros saved per day.
        int client1ExpectedMoney = 500;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void longBookingNoDiscountBecauseBookingLessThanSevenDays()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException,
            OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 6000, reservationSystem);

        Strategy longBookingStrategy = new LongBookingStrategy();
        hotel1.setStrategy(longBookingStrategy);
        hotel1.addClient(client1);
        // six day booking
        client1.bookRoom(room1, LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 17), new ArrayList<>());


        // the client will now have no discount.
        int client1ExpectedMoney = 0;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void longBookingMinimumDiscountBecauseBookingLastsForExactlySevenDays()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException,
            OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 7000, reservationSystem);

        Strategy longBookingStrategy = new LongBookingStrategy();
        hotel1.setStrategy(longBookingStrategy);
        hotel1.addClient(client1);
        // six day booking
        client1.bookRoom(room1, LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 18), new ArrayList<>());


        // the client will now have 15% of discount, which makes 7*0.15*1000 = 1050
        int client1ExpectedMoney = 1050;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void longBookingDiscountTwentyDaysHalfAPercentPerDay()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException,
            OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 20000, reservationSystem);

        Strategy longBookingStrategy = new LongBookingStrategy();
        hotel1.setStrategy(longBookingStrategy);
        hotel1.addClient(client1);
        // six day booking
        client1.bookRoom(room1, LocalDate.of(2022, 12, 1),
                LocalDate.of(2022, 12, 20), new ArrayList<>());


        // the client will now have 15% + 12*0.5 = 21.5% of discount, which makes saving of 20*0.215*1000 = 4300
        int client1ExpectedMoney = 4300;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void longBookingMaximumDiscountOfThirtyPercent()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException,
            OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 50000, reservationSystem);

        Strategy longBookingStrategy = new LongBookingStrategy();
        hotel1.setStrategy(longBookingStrategy);
        hotel1.addClient(client1);
        // fifty day booking
        client1.bookRoom(room1, LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 1, 20), new ArrayList<>());


        // the client will now have 30% of discount that means 0.3*50000 = 15000
        int client1ExpectedMoney = 15000;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void longBookingStrategyDiscountTopClientDiscountAlsoApplies()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException,
            OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 50000, reservationSystem);
        Client client2 = new Client("Toomas", 100000, reservationSystem);
        Client client3 = new Client("Paul", 20000, reservationSystem);
        Client client4 = new Client("Peeter", 100000, reservationSystem);
        Client client5 = new Client("Tauno", 20000, reservationSystem);
        Client client6 = new Client("Rauno", 100000, reservationSystem);

        Strategy longBookingStrategy = new LongBookingStrategy();
        hotel1.setStrategy(longBookingStrategy);
        hotel1.addClient(client1);
        hotel1.addClient(client2);
        hotel1.addClient(client3);
        hotel1.addClient(client4);
        hotel1.addClient(client5);
        hotel1.addClient(client6);
        // two day booking
        client2.bookRoom(room1, LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 12), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2022, 12, 13),
                LocalDate.of(2022, 12, 13), new ArrayList<>());
        client3.bookRoom(room1, LocalDate.of(2022, 12, 14),
                LocalDate.of(2022, 12, 14), new ArrayList<>());
        client3.bookRoom(room1, LocalDate.of(2022, 12, 15),
                LocalDate.of(2022, 12, 15), new ArrayList<>());
        client4.bookRoom(room1, LocalDate.of(2022, 12, 16),
                LocalDate.of(2022, 12, 16), new ArrayList<>());
        client4.bookRoom(room1, LocalDate.of(2022, 12, 17),
                LocalDate.of(2022, 12, 17), new ArrayList<>());
        client5.bookRoom(room1, LocalDate.of(2022, 12, 18),
                LocalDate.of(2022, 12, 18), new ArrayList<>());
        client5.bookRoom(room1, LocalDate.of(2022, 12, 19),
                LocalDate.of(2022, 12, 19), new ArrayList<>());
        client6.bookRoom(room1, LocalDate.of(2022, 12, 20),
                LocalDate.of(2022, 12, 20), new ArrayList<>());
        client6.bookRoom(room1, LocalDate.of(2022, 12, 21),
                LocalDate.of(2022, 12, 21), new ArrayList<>());


        // the client will now have a 45 percent discount, 30% from the strategy and 15% from being a top client.
        client1.bookRoom(room1, LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 2, 20), new ArrayList<>());
        // the price of an economyroom is 1000 per day,
        // so that a 45% discount equals to 450 euros saved per day for 50 days.
        int client1ExpectedMoney = 22500;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void combinedStrategyDiscountsBasedOnBookingNumberNoBookingsAndLongBookingNoTopClientMaximumDiscount()
            throws CannotBookHotelIfNotClientException,
            NotEnoughMoneyToBookException, OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 50000, reservationSystem);

        Strategy combinedStrategy = new CombinedStrategy();
        hotel1.setStrategy(combinedStrategy);
        hotel1.addClient(client1);
        // two day booking
        client1.bookRoom(room1, LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 1, 20), new ArrayList<>());

        // get maximum discount of  40 percent.
        // the price of an economyroom is 1000 per day, so that a 40% discount equals to 400 euros saved per day.
        int client1ExpectedMoney = 20000;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }

    @Test
    void combinedStrategyDiscountTwentyDaysAndHotelHalfBookedCombinesResults()
            throws CannotBookHotelIfNotClientException, NotEnoughMoneyToBookException, OverlappingBookingException {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel1 = new Hotel("Grand Budapest", "Hungary", "Budapest", reservationSystem);
        Room room1 = new Room(hotel1, Room.RoomType.ECONOMYROOM);
        Client client1 = new Client("Joonas", 20000, reservationSystem);
        Client client2 = new Client("Joonas", 50000, reservationSystem);

        Strategy combinedStrategy = new CombinedStrategy();
        hotel1.setStrategy(combinedStrategy);
        hotel1.addClient(client1);
        hotel1.addClient(client2);
        client2.bookRoom(room1, LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 1), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2023, 1, 2),
                LocalDate.of(2023, 1, 2), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2023, 1, 3),
                LocalDate.of(2023, 1, 3), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2023, 1, 4),
                LocalDate.of(2023, 1, 4), new ArrayList<>());
        client2.bookRoom(room1, LocalDate.of(2023, 1, 5),
                LocalDate.of(2023, 1, 5), new ArrayList<>());
        // all rooms are booked by the same client to not mess with the top client discount system.
        client1.bookRoom(room1, LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 30), new ArrayList<>());

        // get discount of (15 + 12 * 0.5) + (0.1 + 5*1) = 36.5 percent.
        // the price of an economyroom is 1000 per day, so that a 36.5% discount equals
        // to 36.5 euros saved per day or 7300 euros per 20 days.
        int client1ExpectedMoney = 7300;
        Assertions.assertEquals(client1ExpectedMoney, client1.getMoney());
    }
}
