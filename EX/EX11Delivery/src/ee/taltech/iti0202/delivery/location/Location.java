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

    @Override
    public String toString() {
        return this.name + " PACKETS: " + this.packets.size();
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
     * Take the packet from the location, confusing name required.
     * Not to be mistaken with getPackets.
     * @param name - name of the packet.
     * @return - optional of the packet.
     */
    public Optional<Packet> getPacket(String name) {
        // a stream returns an Optional by default.
        Optional<Packet> gottenPacket = packets.stream()
                .filter(packet -> packet.getName().equals(name))
                .findFirst();
        gottenPacket.ifPresent(packets::remove);
        return gottenPacket;
    }

    /**
     * Get list of packets to get from.
     * @return - packets in the location.
     */
    public List<Packet> getPackets() {
        return packets;
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

    public String getFarthestOrClosestDistance(Boolean closest) {
        Integer distance = 0;
        if (closest) {
            distance = Collections.min(distanceMap.values());
        } else {
            distance = Collections.max(distanceMap.values());
        }

        String foundLocation = "";
        if (distanceMap.containsValue(distance)) {
            for (String locationName : distanceMap.keySet()) {
                if (distanceMap.get(locationName).equals(distance)) {
                    foundLocation = locationName;
                }
            }
        }
        return foundLocation;
    }
    public Map<String, Integer> getDistances() {
        return distanceMap;
    }

    /**
     * Name getter.
     * @return - the name of the location.
     */
    public String getName() {
        return name;
    }
}
