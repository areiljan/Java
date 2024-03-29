package ee.taltech.iti0202.hotel;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.room.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hotel {
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    private ArrayList<Room> rooms;
    public ArrayList<Client> getClients() {
        return clients;
    }
    private ArrayList<Client> clients;

    /**
     * Hotel constructor.
     */
    public Hotel() {
        this.rooms = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    /**
     * Method to add a client to the Hotel.
     */
    public void addClient(Client clientToAdd) {
        clients.add(clientToAdd);
    }

    /**
     * Method to get total map of all bookings in the hotel.
     */
    public Map<> getBookings() {
        Map hotelBookings = new HashMap<>();
        for (Room room : rooms) {
            hotelBookings.putAll(room.getBookings());
        }
    }


}
