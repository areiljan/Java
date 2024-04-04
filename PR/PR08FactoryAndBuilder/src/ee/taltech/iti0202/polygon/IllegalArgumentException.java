package ee.taltech.iti0202.polygon;

public class IllegalArgumentException extends Exception {
    public IllegalArgumentException() {
        super("The factory cannot produce polygons with such amount of sides");
    }
}
