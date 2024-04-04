package ee.taltech.iti0202.factory;

import ee.taltech.iti0202.polygon.*;
import ee.taltech.iti0202.polygon.IllegalArgumentException;

public class PolygonFactory {

    /**
     * Factory makes a new Polygon with given amount of sides.
     * @param numberOfSides number of sides on the polygon
     * @return new Polygon class with correct number of sides ( numberOfSides = 4 -> new Square() )
     */
    public static Polygon getPolygon(int numberOfSides) throws IllegalArgumentException {
        switch (numberOfSides) {
            case 3:
                return new Triangle();
            case 4:
                return new Square();
            case 5:
                return new Pentagon();
            case 6:
                return new Hexagon();
            default:
                throw new IllegalArgumentException();
        }

    }
}