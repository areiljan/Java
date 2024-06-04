package ee.taltech.iti0202.exam.customer;

import ee.taltech.iti0202.exam.NotEnoughMoneyException;
import ee.taltech.iti0202.exam.Order;
import ee.taltech.iti0202.exam.bill.Bill;
import ee.taltech.iti0202.exam.dotkomm.DotKomm;
import ee.taltech.iti0202.exam.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private float money;
    private ArrayList<Bill> bills;
    private ArrayList<Order> history;


    /**
     * Customer is always king.
     * @param name - name.
     * @param budget - budget.
     */
    public Customer(String name, Integer budget) {
        this.name = name;
        this.money = budget;
        this.bills = new ArrayList<>(); // all my bills paid
        this.history = new ArrayList<>();
    }

    /**
     * Name getter.
     * @return - name as string.
     */
    public String getName() {
        return name;
    }

    /**
     * Budget getter.
     * @return - budget as Integer.
     */
    public float getBudget() {
        return money;
    }

    /**
     * Get history of purchases.
     */
    public List<Order> getHistory() {
        return history;
    }

    /**
     * Buy produce from store.
     * @param dotKomm - which store.
     * @param product - which product.
     * @param amount - what amount.
     */
    public void buy(DotKomm dotKomm, Product product, Integer amount) throws NotEnoughMoneyException {
        float cost = product.getPrice() * amount;
        if (cost <= money) {
            Order order = new Order(product, amount);
            history.add(order);
            dotKomm.addOrder(order, this);
            money -= cost;
        } else {
            throw new NotEnoughMoneyException();
        }
    }

}
