package ee.taltech.iti0202.stock.stock;

import ee.taltech.iti0202.stock.exceptions.StockException;
import ee.taltech.iti0202.stock.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


/**
 * The stock class.
 * <p>
 * Each stock has a name, list of products that are currently stored in it
 * and the maximum amount of products that stock can store.
 * <p>
 * If adding a product to the stock is not possible, due to exceeding the maximum limit of products
 * OR stock already contains a product, a StockException must be thrown,
 * otherwise product must be added to the stock.
 * <p>
 * When getting (not removing) a product from our stock,
 * it is important to find the product with the lowest price first.
 */
public class Stock {

    private final int maxCapacity;
    private final String name;
    private List<Product> productList;

    /**
     * Create a new stock with the given name and the max capacity for the products.
     *
     * @param name the name of the stock.
     * @param maxCapacity max amount of products allowed in the stock.
     */
    public Stock(String name, int maxCapacity) {
        this.productList = new ArrayList<>();
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Add a product to the stock, if stock does not contain the product and is not full yet.
     * <p>
     * Check in following order:
     * If stock already contains a product, throw an StockException with a STOCK_ALREADY_CONTAINS_PRODUCT reason.
     * If stock is full, throw a StockException with a STOCK_IS_FULL reason.
     *
     * @param product to be added
     * @throws StockException STOCK_ALREADY_CONTAINS_PRODUCT, STOCK_IS_FULL
     */
    public void addProduct(Product product) throws StockException {
        if (this.isFull()) {
            throw new StockException(StockException.Reason.STOCK_IS_FULL);
        } else if (productList.contains(product)) {
            throw new StockException(StockException.Reason.STOCK_ALREADY_CONTAINS_PRODUCT);
        } else {
            productList.add(product);
        }
    }

    /**
     * Get a product from a stock by name with the lowest price.
     * <p>
     * If there are several products with the same name and price,
     * returns the product with the lowest id.
     *
     * @param name the product's name
     * @return Optional
     */
    public Optional<Product> getProduct(String name) {
        Product cheapestProduct = null;
        for (Product product : productList) {
            if (product.getName().equals(name) && (cheapestProduct == null
                    || product.getPrice() < cheapestProduct.getPrice()
                    || (product.getPrice() == cheapestProduct.getPrice()
                    && product.getId() < cheapestProduct.getId()))) {
                cheapestProduct = product;
            }
        }
        return Optional.ofNullable(cheapestProduct);
    }

    /**
     * Remove and return a product from a stock,
     * if stock has a given product.
     * Use getProduct() method to get the product.
     * If there is nothing to remove, return Optional.empty()
     *
     * @param name Name of the product to be removed
     * @return Optional
     */
    public Optional<Product> removeProduct(String name) {
        Optional<Product> productToRemove = getProduct(name);
        if (productToRemove.isPresent()) {
            productList.remove(productToRemove.get());
        }
        return productToRemove;
    }

    /**
     * Get a list of current products in the stock.
     *
     * @return List
     */
    public List<Product> getProducts() {
        return productList;
    }

    /**
     * Get a list of current products in the stock filtered by name.
     *
     * Order the products by price ascending. In case of the same price, by id ascending.
     *
     * @param name Name to be filtered.
     * @return List
     */
    public List<Product> getProducts(String name) {
        List<Product> filteredProducts = new ArrayList<>();
        if (name != null && !productList.isEmpty()) {
            for (Product product : productList) {
                if (name.equals(product.getName())) {
                    filteredProducts.add(product);
                }
            }
            for (int i = 0; i < filteredProducts.size() - 1; i++) {
                for (int j = i + 1; j < filteredProducts.size(); j++) {
                    Product productI = filteredProducts.get(i);
                    Product productJ = filteredProducts.get(j);

                    // First, compare by price
                    if (productI.getPrice() > productJ.getPrice()) {
                        // Swap the elements
                        Product temp = filteredProducts.get(i);
                        filteredProducts.set(i, filteredProducts.get(j));
                        filteredProducts.set(j, temp);
                    } else if (productI.getPrice() == productJ.getPrice()
                            && productI.getId() > productJ.getId()) {
                        Product temp = filteredProducts.get(i);
                        filteredProducts.set(i, filteredProducts.get(j));
                        filteredProducts.set(j, temp);
                    }
                }
            }

        }
        return filteredProducts;
    }

    /**
     * Get total price of all the products.
     *
     * @return Total price.
     */
    public int getTotalPrice() {
        int sumOfPrices = 0;
        for (Product product : productList) {
            sumOfPrices += product.getPrice();
        }
        return sumOfPrices;
    }

    /**
     * Check if stock is full.
     *
     * @return boolean
     */
    public boolean isFull() {
        return productList.size() >= maxCapacity;
    }
}
