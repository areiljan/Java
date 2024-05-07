package secondPartFunctionality;

import ee.taltech.iti0202.hotel.ReservationSystem;
import ee.taltech.iti0202.hotel.Hotel;
import ee.taltech.iti0202.hotel.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class HotelTest {
    @Test
    public void hotelHasACountryTest() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Firenze", "Italy", "Florence", reservationSystem);

        String country = "Italy";
        Assertions.assertEquals(hotel.getCountry(), country);
    }

    @Test
    public void hotelHasACityTest() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Firenze", "Italy", "Florence", reservationSystem);

        String city = "Florence";
        Assertions.assertEquals(hotel.getCity(), city);
    }

    @Test
    public void roomNumberIncrements() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Firenze", "Italy", "Florence", reservationSystem);
        Room room1 = new Room(hotel, Room.RoomType.FAMILYROOM);
        Room room2 = new Room(hotel, Room.RoomType.ECONOMYROOM);

        Assertions.assertEquals(2, room2.getRoomNumber());
    }

    @Test
    public void roomPriceIsABigDecimal() {
        ReservationSystem reservationSystem = new ReservationSystem();
        Hotel hotel = new Hotel("Grand Firenze", "Italy", "Florence", reservationSystem);
        Room room1 = new Room(hotel, Room.RoomType.FAMILYROOM);

        Assertions.assertInstanceOf(BigDecimal.class, room1.getRoomType().getPrice());
    }


}
