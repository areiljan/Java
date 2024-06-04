package ee.taltech.iti0202.exam.bill;

import ee.taltech.iti0202.exam.customer.Customer;
import ee.taltech.iti0202.exam.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Bill {
    private Map<Product, Integer> inVoice;

    /**
     * Bill.
     */
    public Bill () {
        this.inVoice = new HashMap<>();
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
