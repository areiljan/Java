package ee.taltech.iti0202.delivery;

import java.util.*;

public class Location {
    private final Map<String, Integer> distanceMap = new HashMap<>();
    private final Map<String, Packet> packets = new HashMap<>();
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
        packets.put(packet.getName(), packet);
    }

    /**
     * Take the packet from the location, confusing name required.
     * Not to be mistaken with getPackets.
     * @param name - name of the packet.
     * @return - optional of the packet.
     */
    public Optional<Packet> getPacket(String name) {
        if (this.packets.containsKey(name)) {
            Packet packet = this.packets.get(name);
            packets.remove(name);
            return Optional.ofNullable(packet);
        }
        return Optional.empty();
    }

    /**
     * Get list of packets to get from.
     * @return - packets in the location.
     */
    public List<Packet> getPackets() {
        return packets.values().stream().toList();
    }


    /**
     * Add a distance to the speficied location.
     * @param location - to where is the distance.
     * @param distance - distance.
     */
    public void addDistance(String location, int distance) {
        distanceMap.put(location, distance);
    }

    /**
     * Distance getter.
     * @param name - name of the location.
     * @return - the distance.
     */
    public int getDistanceTo(String name) {
        if (distanceMap.containsKey(name)) {
            return distanceMap.get(name);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Helper method to get the farthest or closest distance.
     * @param closest - is the distance closest.
     * @return - location.
     */
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

    /**
     * Get the distancemap.
     * @return
     */
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
