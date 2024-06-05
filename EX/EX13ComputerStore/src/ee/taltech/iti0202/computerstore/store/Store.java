package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.util.List;
import java.math.BigDecimal;

public class Store {
    private String name;
    private BigDecimal balance;
    private BigDecimal profitMargin;
    public Store(String name, BigDecimal balance, BigDecimal profitMargin) {
        this.name = name;
        this.balance = balance;
        // profit margin must be at least 1
        if (BigDecimal.ONE.compareTo(profitMargin) < 1) {
            this.profitMargin = profitMargin;
        } else {
            throw new IllegalArgumentException("Profit Margin must be greater than 1");
        }
    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        if ;
    }

    public List<Component> getAvailableComponents() {
        return null;
    }

    public List<Component> getComponentsSortedByAmount() {
        return null;
    }

    public List<Component> getComponentsSortedByName() {
        return null;
    }

    public List<Component> getComponentsSortedByPrice() {
        return null;
    }

    public List<Component> filterByType(Component.Type type) {
        return null;
    }

    public BigDecimal getInventoryValue() {
        return BigDecimal.ZERO;
    }

    public String getName() {
        return null;
    }

    public void setName(String name) {
    }

    public BigDecimal getBalance() {
        return -1;
    }

    public void setBalance(BigDecimal balance) {
    }

    public BigDecimal getProfitMargin() {
        return -1;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
    }
}