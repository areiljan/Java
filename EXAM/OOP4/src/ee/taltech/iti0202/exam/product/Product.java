package ee.taltech.iti0202.exam.product;

public class Product {
    private String name;
    private float price;


    /**
     * Name getter.
     * @return name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Product class.
     * @param name - name of the product.
     * @param price - price of the product.
     */
    public Product (String name, float price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Price getter.
     * @return price as integer.
     */
    public float getPrice() {
        return price;
    }

}
