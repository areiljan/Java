package ee.taltech.iti0202.delivery.packet;

import ee.taltech.iti0202.delivery.location.Location;

public class Packet {
    private final Location target;
    private final String name;
    public Packet(String name, Location target) {
        this.name = name;
        this.target = target;
    }

    /**
     * Getter for name.
     * @return - name of the packet.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for target.
     * @return - name of the target.
     */
    public Location getTarget() {
        return target;
    }
}
