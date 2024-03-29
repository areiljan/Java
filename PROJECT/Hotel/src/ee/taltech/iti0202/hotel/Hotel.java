package ee.taltech.iti0202.hotel;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.room.Room;

import java.util.ArrayList;

public class Hotel {
    public ArrayList getRooms() {
        return rooms;
    }

    private ArrayList rooms;
    public ArrayList getClients() {
        return rooms;
    }

    private ArrayList clients;

    /**
     * Hotel constructor.
     */
    public Hotel() {
        this.rooms = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    /**
     * Method to add a room to the Hotel.
     */
    public void addRoom(Room roomToAdd) {
        if (roomToAdd.getHotel() == null) {
            rooms.add(roomToAdd);
            roomToAdd.setHotel(this);
        }
    }

    /**
     * Method to add a client to the Hotel.
     */
    public void addClient(Client clientToAdd) {
        clients.add(clientToAdd);
    }


}
