package ee.taltech.iti0202.exam.dotkomm;

import ee.taltech.iti0202.exam.Order;
import ee.taltech.iti0202.exam.bill.Bill;
import ee.taltech.iti0202.exam.customer.Customer;
import ee.taltech.iti0202.exam.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DotKomm {

    private List<Product> productList;
    private Map<Customer, Bill> customerMap;
    private List<Order> orderHistory;


    /**
     * Constructor.
     */
    public DotKomm() {
        this.customerMap = new HashMap<>();
        orderHistory = new ArrayList<>();
        productList = new ArrayList<>();
    }

    /**
     * Get products as list.
     * @return - list of products.
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Customer getter.
     * @return customer map.
     */
    public Map<Customer, Bill> getCustomerMap() {
        return customerMap;
    }

    /**
     * Add a customer.
     */
    public void addCustomer(Customer customer) {
        customerMap.put(customer, new Bill());
    }

    /**
     * Add a product.
     */
    public void addProduct(Product product) {
        if (!productList.contains(product)) {
            productList.add(product);
        }
    }

    /**
     * Search for items based on name.
     * @param itemName - name to base search on.
     * @return - list of matching items.
     */
    public List<Product> itemSearchByName(String itemName) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(itemName)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    /**
     * Search for items based on popularity.
     *
     * @return - descending List of items.
     */
    public List<Map.Entry<Product, Integer>> itemSearchByPopularity() {
        Map<Product, Integer> allFoodsMap = new HashMap<>();
        for (Bill bill : customerMap.values()) {
            Map<Product, Integer> invoice = bill.getInVoice();
            for (Product product : invoice.keySet()) {
                if (allFoodsMap.containsKey(product)) {
                    allFoodsMap.put(product, invoice.get(product) + allFoodsMap.get(product));
                } else {
                    allFoodsMap.put(product, invoice.get(product));
                }
            }
        }
        return allFoodsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    /**
     * Add an order to the orderhistory.
     * @param order - order to add.
     */
    public void addOrder(Order order, Customer customer) {
        customerMap.get(customer).addEntry(order); // filing the bill for the customer.
        orderHistory.add(order);
    }
}
