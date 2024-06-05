package ee.taltech.iti0202.computerstore.components;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Component {
    private int id;
    private String name;
    private Type type;


    private BigDecimal price;
    private int amount = 1;
    private String manufacturer;
    private int performancePoints;
    private int powerConsumption;

    /**
     * Type of computer component.
     */
    public enum Type {
        CPU, GPU, RAM, MOTHERBOARD, HDD, SSD, PSU, KEYBOARD, TOUCHPAD, SCREEN, BATTERY, FAN
    }



    /**
     * Component constructor.
     * @param name - name of the component.
     * @param type - type of component.
     * @param price - price.
     * @param manufacturer - manufacturer.
     * @param performancePoints - performancePoints.
     * @param powerConsumption - powerConsumption.
     */
    public Component(String name, Type type, BigDecimal price, String manufacturer, int performancePoints, int powerConsumption) {
        this.id = -1;
        this.name = name;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
        this.performancePoints = performancePoints;
        this.powerConsumption = powerConsumption;
    }

    /**
     * How the class will be printed as string.
     * @return - string.
     */
    @Override
    public String toString() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", id);
        attributes.put("name", name);
        attributes.put("type", type);
        attributes.put("price", price);
        attributes.put("amount", amount);
        attributes.put("manufacturer", manufacturer);
        attributes.put("performancePoints", performancePoints);
        attributes.put("powerConsumption", powerConsumption);
        return attributes.toString();
    }

    /**
     * Name getter.
     * @return - name of the component.
     */
    public String getName() {
        return name;
    }

    /**
     * Type getter.
     * @return - component type.
     */
    public Type getType() {
        return type;
    }

    /**
     * Get component price.
     * @return - price of the component.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Amount getter.
     * @return - amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Increase the amount by amount.
     * @param amount - the amount by which to increase.
     */
    public void changeAmount(int amount) {
        this.amount += amount;
    }

    /**
     * Id getter.
     * @return - id as integer.
     */
    public int getId() {
        return id;
    }

}
