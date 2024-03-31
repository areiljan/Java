package ee.taltech.iti0202.hotel.room;

import ee.taltech.iti0202.hotel.Hotel;

public class Room {
    private final RoomType roomType;
    private Integer roomNumber = 0;
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
        SUITE(5000),
        ECONOMYROOM(1000),
        FAMILYROOM(2000);

        private final int price;

        /**
         * Rooms have different prices.
         * @param price - price of the room.
         */
        RoomType(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }
    public RoomType getRoomType() {
        return roomType;
    }

}
