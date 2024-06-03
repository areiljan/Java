package ee.taltech.iti0202.generics.food;

public abstract class Food {
    protected String name;

    /**
     * Food constructor.
     * @param name - name of the food.
     */
    public Food(String name) {
        this.name = name;
    }

    /**
     * Name getter.
     * @return - name of the food.
     */
    public String getName() {
        return name;
    }
}
