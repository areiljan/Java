package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private String name;
    private BigDecimal balance;
    private BigDecimal profitMargin;

    /**
     * ComputerStore constructor.
     * @param name - name of the ComputerStore.
     * @param balance - balance as BigDecimal.
     * @param profitMargin - profitMargin as BigDecimal.
     */
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

    /**
     * Purchase a component from the computerStore.
     * @param id - id of the component.
     * @param customer - customer that buys the component.
     * @return - component.
     * @throws OutOfStockException - if component out of stock.
     * @throws ProductNotFoundException - if the product is not to be found.
     * @throws NotEnoughMoneyException - if the customer does not have enough money.
     */
    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        Database database = Database.getInstance();
        Component component = database.getComponents().get(id);
        if (component == null) {
            throw new ProductNotFoundException();
        } else if (component.getAmount() == 0) {
            throw new OutOfStockException();
        }
        BigDecimal finalPrice = component.getPrice().multiply(profitMargin);
        if (customer.getBalance().compareTo(finalPrice) < 0) {
            throw new NotEnoughMoneyException();
        }

        balance = balance.add(finalPrice);
        customer.decreaseBalance(finalPrice);
        database.decreaseComponentStock(id, 1);
        return component;
    }

    /**
     * Get a list of all available components.
     * @return - list of components.
     */
    public List<Component> getAvailableComponents() {
        Database database = Database.getInstance();
        List<Component> components = new ArrayList<>();
        for (Component component : database.getComponents().values()) {
            if (component.getAmount() > 0) {
                components.add(component);
            }
        }
        return components;
    }

    /**
     * Sort the components by amount in stock.
     * @return - list of components.
     */
    public List<Component> getComponentsSortedByAmount() {
        return getAvailableComponents().stream()
                .sorted(Comparator.comparingInt(Component::getAmount))
                .collect(Collectors.toList());
    }

    /**
     * Sort the components by name.
     * @return - list of components.
     */
    public List<Component> getComponentsSortedByName() {
        return getAvailableComponents().stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    /**
     * Sort the components by price.
     * @return - list of components by price.
     */
    public List<Component> getComponentsSortedByPrice() {
        return getAvailableComponents().stream()
                .sorted(Comparator.comparing(Component::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Filter the components by type.
     * @param type - which type of components are needed.
     * @return - list of components that match the criteria.
     */
    public List<Component> filterByType(Component.Type type) {
        return getAvailableComponents().stream()
                .filter(component -> component.getType() == type)
                .collect(Collectors.toList());
    }

    /**
     * Return the total sum of stores inventory value.
     * @return - inventory value as BigDecimal.
     */
    public BigDecimal getInventoryValue() {
        BigDecimal inventoryValue = BigDecimal.ZERO;
        MathContext mc = new MathContext(2);
        for (Component component : getAvailableComponents()) {
            inventoryValue.add(component.getPrice().multiply(BigDecimal.valueOf(component.getAmount())));
        }
        return inventoryValue.round(mc);
    }

    /**
     * Name getter.
     * @return - string of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * @param name - string to set as name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Balance getter.
     * @return - balance as BigDecimal.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Balance setter.
     * @param balance - balance as BigDecimal.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * ProfitMargin getter.
     * @return - BigDecimal of ProfitMargin.
     */
    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    /**
     * ProfitMargin setter.
     * @param profitMargin - BigDecimal of ProfitMargin.
     */
    public void setProfitMargin(BigDecimal profitMargin) {
        this.profitMargin = profitMargin;
    }
}