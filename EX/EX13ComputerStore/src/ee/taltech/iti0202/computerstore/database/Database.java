package ee.taltech.iti0202.computerstore.database;

import com.google.gson.Gson;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private final Gson gson = new Gson();
    private static Database instance;

    /**
     * Constructor set to private.
     */
    private Database() {
    }

    /**
     * Singleton implementation.
     * @return - database.
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Save the component to the database.
     * @param component - component to save.
     * @throws ProductAlreadyExistsException - if the component is already added.
     */
    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (components.containsKey(component.getId())) {
            throw new ProductAlreadyExistsException();
        }
        components.put(component.getId(), component);
    }

    /**
     * Delete a component from the database.
     * @param id - which id component to remove.
     * @throws ProductNotFoundException - if the component does not exist in the database.
     */
    public void deleteComponent(int id) throws ProductNotFoundException {
        if (components.containsKey(id)) {
            components.remove(id);
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Increase the component stock by set amount.
     * @param id - id of the component.
     * @param amount - amount to increase the stock by.
     * @throws ProductNotFoundException - if the product was not found.
     */
    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (components.containsKey(id)) {
            components.get(id).changeAmount(amount);
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Decrease the component stock by one.
     * @param id - if of component.
     * @param amount - amount to decrease by.
     * @throws OutOfStockException - if decreased to under 0.
     * @throws ProductNotFoundException - if the product was not found.
     */
    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        if (components.get(id).getAmount() < amount) {
            throw new OutOfStockException();
        } else {
            components.get(id).changeAmount(-amount);
        }
    }

    /**
     * Get the component map.
     * @return - component map.
     */
    public Map<Integer, Component> getComponents() {
        return components;
    }

    /**
     * Reset the database.
     */
    public void resetEntireDatabase() {
        Component.setNextId(0);
        components.clear();
    }

    /**
     * Convert the current objects to string and save it to file.
     * @param location - location to save to.
     */
    public void saveToFile(String location) {
        Map<String, Map<Integer, Component>> bigData = new HashMap<>();
        bigData.put("components", components);
        try (FileWriter writer = new FileWriter(location)) {
            gson.toJson(bigData, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load a json file from that location.
     * @param location - location to download from.
     */
    public void loadFromFile(String location) {
        try (FileReader reader = new FileReader(location)) {
            DatabaseJson databaseJson = gson.fromJson(reader, DatabaseJson.class);

            if (databaseJson != null && databaseJson.jsonComponents != null) {
                Database newInstance = new Database();
                for (Map<Integer, Component> componentMap : databaseJson.jsonComponents.values()) {
                    for (Component component : componentMap.values()) {
                        if (component != null) {
                            newInstance.components.put(component.getId(), component);
                        }
                    }
                }
                instance = newInstance;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper class.
     */
    private final class DatabaseJson {
        Map<String, Map<Integer, Component>> jsonComponents;
    }
}
