package ee.taltech.iti0202.exam.cafe;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.time.chrono.JapaneseEra.values;

public class Cafe {
    private final String name;
    private final List<Order> orders;

    /**
     * Constructor for Cafe class.
     * Creates a new cafe with a name.
     * @param name the name of the cafe.
     */
    public Cafe(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }

    /**
     * Gets the name of the cafe.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of orders in the cafe.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Takes a new order and adds it to the list of orders.
     * If order was already taken, when nothing happens.
     * @param order the order to take.
     */
    public void takeOrder(Order order) {
        if (!orders.contains(order)) {
            orders.add(order);
        }
    }

    /**
    * Finds an order by the customer's name.
    * Return the order, or Optional.empty() if no order with given client´s name exists.
    */
    public Optional<Order> findOrderByName(String customerName) {
        for (Order order : orders) {
            if (order.getCustomerName().equals(customerName)) {
                return Optional.ofNullable(order);
            }
        }
        return Optional.empty();
    }

    /**
    * Calculates the total income of the cafe by summing total income of all orders in the cafe.
    * @return the total income from all orders.
    */
    public double calculateTotalIncome() {
        float totalIncome = 0;
        for (Order order : orders) totalIncome += order.getTotalPrice();
        return totalIncome;
    }

    /**
    * Calculates the average price of the orders.
    * @return the average orde price.
    */
    public double getAverageOrderPrice() {
        return getAverageOrderPrice() / calculateTotalIncome();
    }

    /**
    * Finds the most popular food item name in the cafe.
    * Returns name of the most popular food item name from all the orders in the cafe.
    * If no orders have been made returns Optional.empty().
    * @return the most popular food.
    */
    public Optional<String> findMostPopularFood() {
        ArrayList<Food> allFoods = new ArrayList<>();
        for (Order order : orders) {
            allFoods.addAll(order.getFoods());
        }
        if (allFoods.size() == 0) {
            return Optional.empty();
        }
        Map<Food, Long> foodCounts = allFoods.stream().
                collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        String mostPopularFood = "";
        Long foodCount = 0L;
        for (Food food : foodCounts.keySet()) {
            if (foodCounts.get(food) > foodCount) {
                mostPopularFood = food.getName();
            }
        }
        return Optional.ofNullable(mostPopularFood);
    }
}
