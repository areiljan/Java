package ee.taltech.iti0202.computerstore;

import ee.taltech.iti0202.computerstore.components.Component;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Customer {
    private String name;
    private BigDecimal balance;
    private final List<Component> components = new ArrayList<>();

    /**
     * Customer constructor.
     * @param name - name of the customer.
     * @param balance - customers balance.
     */
    public Customer(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * Customers balance.
     * @return - balance as BigDecimal.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Name getter.
     * @return - get name as string.
     */
    public String getName() {
        return name;
    }

    /**
     * Components getter.
     * @return - components as list.
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Name setter.
     * @param name - to set as name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Balance setter.
     * @param balance - to set as balance.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Decrease balance
     * @param balance - BigDecimal.
     */
    public void decreaseBalance(BigDecimal balance) {
        balance.subtract(balance);
    }
}