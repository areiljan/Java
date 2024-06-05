package ee.taltech.iti0202.delivery;

public class Packet {
    private final Location target;
    private final String name;

    /**
     * Packet constructor.
     * @param name - name of the packet.
     * @param target - target of the packet.
     */
    public Packet(String name, Location target) {
        this.name = name;
        this.target = target;
    }

    /**
     * How will the packet look like souted.
     * @return - string.
     */
    public String toString() {
        return "Packet [name=" + name + ", target=" + target + "]";
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
