package ee.taltech.iti0202.hotel.room;

import ee.taltech.iti0202.hotel.Hotel;

import java.math.BigDecimal;

public class Room {
    private final RoomType roomType;

    public Integer getRoomNumber() {
        return roomNumber;
    }

    private Integer roomNumber = 1;
    private Hotel hotel;

    /**
     * Room Initializer.
     * @param hotelTheRoomIsIn - the hotel.
     * @param roomType - the type of room.
     */
    public Room(Hotel hotelTheRoomIsIn, RoomType roomType) {
        this.roomType = roomType;
        this.roomNumber += 1;
        hotelTheRoomIsIn.addRoom(this);
    }

    // The different rooms do not have different functionality, so I will use enum.
    public enum RoomType {
        SUITE(new BigDecimal(5000)),
        ECONOMYROOM(new BigDecimal(1000)),
        FAMILYROOM(new BigDecimal(2000));

        private final BigDecimal price;

        /**
         * Rooms have different prices.
         * @param price - price of the room.
         */
        RoomType(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }
    public RoomType getRoomType() {
        return roomType;
    }

}
