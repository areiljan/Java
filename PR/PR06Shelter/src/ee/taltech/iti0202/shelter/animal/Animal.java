
package ee.taltech.iti0202.shelter.animal;

public abstract class Animal {
    public enum Type {
        HIROLA, TARANTULA, WOMBAT
    }
    private String color;

    /**
     * Animal initializer.
     * This is a superclass for the Hirola, Tarantula and Wombat.
     * @param color - what color is the specimen.
     */
    public Animal(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
