package ee.taltech.iti0202.stock.product;

import ee.taltech.iti0202.stock.exceptions.StockException;

public class Product {
    private static int idToIncrement = 0;
    protected final String name;
    protected final int price;
    private int id = 0;

    /**
     * Create a new product with the given name and price.
     * <p>
     * If the price is negative, throw a StockException with a NEGATIVE_PRICE reason.
     *
     * @param name Name of the product.
     * @param price Price of the product.
     * @throws StockException NEGATIVE_PRICE
     */
    public Product(String name, int price) throws StockException {
        this.name = name;
        if (price < 0) {
            throw new StockException(StockException.Reason.STOCK_IS_FULL);
        } else {
            this.price = price;
        }
        this.id = getNextId();
    }


    /**
     * Returns the next id.
     *
     * This value has to increment with every call.
     *
     * @return The next id.
     */
    public static int getNextId() {
        idToIncrement++;
        return idToIncrement;
    }

    /**
     * Returns id of the product.
     * 
     * @return id of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the product.
     * 
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the product.
     * 
     * @return the price of the product.
     */
    public int getPrice() {
        return price;
    }
}