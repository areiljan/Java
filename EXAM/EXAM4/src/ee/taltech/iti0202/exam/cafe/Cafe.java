package ee.taltech.iti0202.exam.cafe;

import java.util.List;
import java.util.Optional;

public class Cafe {

    /**
     * Constructor for Cafe class.
     * Creates a new cafe with a name.
     * @param name the name of the cafe.
     */
    public Cafe(String name) {
    }

    /**
     * Gets the name of the cafe.
     */
    public String getName() {
        return null;
    }

    /**
     * Gets the list of orders in the cafe.
     */
    public List<Order> getOrders() {
        return null;
    }

    /**
     * Takes a new order and adds it to the list of orders.
     * If order was already taken, when nothing happens.
     * @param order the order to take.
     */
    public void takeOrder(Order order) {
    }

  /**
   * Finds an order by the customer's name.
   * Return the order, or Optional.empty() if no order with given client´s name exists.
   */
  public Optional<Order> findOrderByName(String customerName) {
        return Optional.empty();
    }

    /**
     * Calculates the total income of the cafe by summing total income of all orders in the cafe.
     * @return the total income from all orders.
     */
    public double calculateTotalIncome() {
        return 0;
    }

    /**
     * Calculates the average price of the orders.
     * @return the average order price.
     */
    public double getAverageOrderPrice() {
        return 0;
    }

  /**
   * Finds the most popular food item name in the cafe.
   * Returns name of the most popular food item name from all the orders in the cafe.
   * If no orders have been made returns Optional.empty().
   * @return the most popular food.
   */
  public Optional<String> findMostPopularFood() {
        return Optional.empty();
    }
}
