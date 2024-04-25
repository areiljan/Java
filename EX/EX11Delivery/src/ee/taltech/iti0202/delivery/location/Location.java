package ee.taltech.iti0202.delivery.location;

import ee.taltech.iti0202.delivery.packet.Packet;

import java.util.*;

public class Location {
    private final Map<String, Integer> distanceMap = new HashMap<>();
    private final List<Packet> packets = new ArrayList<>();
    private final String name;
    public Location(String name) {
        this.name = name;
    }

    /**
     * Add a packet to the packets list.
     * @param packet - packet to add.
     */
    public void addPacket(Packet packet) {
        if (!packets.contains(packet)) {
            packets.add(packet);
        }
    }

    /**
     * Packet getter.
     * @param name - name of the packet.
     * @return - optional of the packet.
     */
    public Optional<Packet> getPacket(String name) {
        // a stream returns an Optional by default.
        return packets.stream()
                .filter(packet -> packet.getName().equals(name))
                .findFirst();
    }

    /**
     * Add a distance to the speficied location.
     * @param location - to where is the distance.
     * @param distance - distance.
     */
    public void addDistance(String location, int distance) {
        if (!distanceMap.containsKey(location)) {
            distanceMap.put(location, distance);
        }
    }

    /**
     * Distance getter.
     * @param name - name of the location.
     * @return - the distance.
     */
    public int getDistanceTo(String name) {
        return distanceMap.get(name);
    }

    /**
     * Name getter.
     * @return - the name of the location.
     */
    public String getName() {
        return name;
    }
}
