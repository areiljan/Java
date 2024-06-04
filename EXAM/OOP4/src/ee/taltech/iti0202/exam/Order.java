package ee.taltech.iti0202.exam;

import ee.taltech.iti0202.exam.product.Product;

public class Order {
    private Product product;
    private Integer amount;

    /**
     * Order constructor.
     * @param product - product.
     * @param amount - amount of this product.
     */
    public Order(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getAmount() {
        return amount;
    }
}
