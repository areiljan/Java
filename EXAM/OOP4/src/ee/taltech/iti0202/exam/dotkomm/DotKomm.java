package ee.taltech.iti0202.exam.dotkomm;

import ee.taltech.iti0202.exam.bill.Bill;
import ee.taltech.iti0202.exam.customer.Customer;
import ee.taltech.iti0202.exam.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotKomm {
    private List<Product> productList;
    private Map<Customer, Bill> customerMap;
    public DotKomm() {
        this.customerMap = new HashMap<>();
    }


    /**
     * Add a customer.
     */
    public void addCustomer(Customer customer) {
        customerMap.put(customer, new Bill());
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
     * @return - list of the three most popular items.
     */
    public List<Product> itemSearchByPopularity() {
        return new ArrayList<>();
    }

}
