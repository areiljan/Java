package ee.taltech.iti0202.lotr;

public class Person {
    private String race;
    private String name = null;
    private Ring ring = null;

    /**
     * This method constructs the Person object.
     *
     * @param race is a string.
     * @param name is a string.
     * @param ring is a Ring object.
     */
    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     * This method constructs the Person object.
     *
     * @param race is a string.
     * @param name is a string.
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * This method assigns a ring to the Person object.
     *
     * @param ring is a Ring object.
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }

    /**
     * Is the given Person object with the assigned ring sauron or not.
     *
     @return The result of the operation. Possible return values:
      *         <ul>
      *             <li>returns "Affirmative"</li>
      *             <li>returns "No, the ring is fake!"</li>
      *             <li>returns "No, but he's claiming to be"</li>
      *             <li>returns "No. he just stole the ring"</li>
      *             <li>returns "No"</li>
      *         </ul>
     */
    public String isSauron() {
        if (ring != null) {
            if (this.name.equals("Sauron") && this.ring.getMaterial().equals(Ring.Material.GOLD)
                    && this.ring.getType().equals(Ring.Type.THE_ONE)) {
                return "Affirmative";
            } else if (this.name.equals("Sauron") && this.ring.getType().equals(Ring.Type.THE_ONE)) {
                return "No, the ring is fake!";
            } else if (this.name.equals("Sauron")) {
                return "No, but he's claiming to be";
            } else if (this.ring.getType().equals(Ring.Type.THE_ONE)
                    && this.ring.getMaterial().equals(Ring.Material.GOLD)) {
                return "No, he just stole the ring";
            } else {
                return "No";
            }
        } else {
            if (this.name.equals("Sauron")) {
                return "No, but he's claiming to be";
            } else {
                return "No";
            }
        }
        }
    String getRace() {
        return race;
    }
    String getName() {
        return name;
    }
    Ring getRing() {
        return ring;
    }
}
