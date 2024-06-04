package ee.taltech.iti0202.exam.bill;

import ee.taltech.iti0202.exam.Order;
import ee.taltech.iti0202.exam.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Bill {
    /**
     * Invoice Getter.
     * @return invoice.
     */
    public Map<Product, Integer> getInVoice() {
        return inVoice;
    }

    private Map<Product, Integer> inVoice;

    /**
     * Bill.
     */
    public Bill() {
        this.inVoice = new HashMap<>();
    }

    /**
     * Add an entry to the bill.
     * @param order
     */
    public void addEntry(Order order) {
        if (inVoice.containsKey(order.getProduct())) {
            inVoice.get(order.getProduct());
            inVoice.put(order.getProduct(), inVoice.get(order.getProduct()) + order.getAmount());
        } else {
            inVoice.put(order.getProduct(), order.getAmount());
        }
    }

    /**
     * Sum of the items on the bill.
     * @return float of all items on the bill.
     */
    public float sum() {
        float sum = 0;
        for (Product product : inVoice.keySet()) {
            sum += product.getPrice() * inVoice.get(product); // price times amount of product on the bill.
        }
        return sum;
    }
}
