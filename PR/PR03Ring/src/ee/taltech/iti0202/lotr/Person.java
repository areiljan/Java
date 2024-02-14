package ee.taltech.iti0202.lotr;

public class Person {
    private String race;
    private String name = null;
    private Ring ring = null;

    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public String isSauron() {
        if (!this.ring.equals(null)) {
            if (this.name.equals("Sauron") && this.ring.type.equals(Ring.Type.THE_ONE) && this.ring.material.equals(Ring.Material.GOLD)) {
                return "Affirmative.";
            } else if (this.name.equals("Sauron") && this.ring.type.equals(Ring.Type.THE_ONE)) {
                return "No, the ring is fake!";
            } else if (this.name.equals("Sauron")) {
                return "No, but he's claiming to be.";
            } else if (this.ring.type.equals(Ring.Type.THE_ONE) && this.ring.material.equals(Ring.Material.GOLD)) {
                return "No, he just stole the ring.";
            } else {
                return "No.";
            }
        } else {
            if (this.name.equals("Sauron")) {
                return "No, but he's claiming to be.";
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