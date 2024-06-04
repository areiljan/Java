package ee.taltech.iti0202.exam.cafe;

public class Food {
    private final String name;
    private final double price;

    /**
     * Constructor for Foo class.
     * Creates a new food item with a name and price.
     * @param name the name of the food.
     * @param price the price of the food.
     */
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the name of the food.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the food.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Checks if the food is expensive compared to a threshold price.
     * @param thresholdPrice the threshold price to compare with.
     * @return true if the food's price is greater than the threshold price.
     */
    public boolean isExpensive(double thresholdPrice) {
        return price > thresholdPrice;
    }
}
