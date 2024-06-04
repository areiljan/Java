package dotkommtest;

import ee.taltech.iti0202.exam.NotEnoughMoneyException;
import ee.taltech.iti0202.exam.bill.Bill;
import ee.taltech.iti0202.exam.customer.Customer;
import ee.taltech.iti0202.exam.dotkomm.DotKomm;
import ee.taltech.iti0202.exam.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DotKommTest {
    @Test
    void productHasAName() {
        Product product = new Product("Priit", 10000);

        String productName = "Priit";

        Assertions.assertEquals(productName, product.getName());
    }

    @Test
    void productHasAPrice() {
        Product product = new Product("Priit", 10000);

        float productPrice = 10000f;

        Assertions.assertEquals(productPrice, product.getPrice());
    }

    @Test
    void systemHasOverviewOfClients() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Customer customer1 = new Customer("Priit", 10000);
        Customer customer2 = new Customer("Priit", 10000);
        dotKomm.addCustomer(customer1);
        dotKomm.addCustomer(customer2);

        ArrayList<Customer> excpectedCustomers = new ArrayList<Customer>();
        excpectedCustomers.add(customer1);
        excpectedCustomers.add(customer2);

        Assertions.assertEquals(dotKomm.getCustomerMap().size(), excpectedCustomers.size());
    }

    @Test
    void systemHasOverviewOfProducts() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Product product1 = new Product("Priiti", 10000);
        Product product2 = new Product("Priit", 10000);
        dotKomm.addProduct(product1);
        dotKomm.addProduct(product2);

        ArrayList<Product> excpectedProducts = new ArrayList<Product>();
        excpectedProducts.add(product1);
        excpectedProducts.add(product2);

        Assertions.assertEquals(dotKomm.getProductList(), excpectedProducts);
    }

    @Test
    void hasClassToTieClientAndProductTogether() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Product product1 = new Product("Priiti", 1);
        Product product2 = new Product("Priit", 1);
        dotKomm.addProduct(product1);
        dotKomm.addProduct(product2);
        // each time a customer is added a bill is opened in the store.
        Customer customer1 = new Customer("Priit", 10000);
        Customer customer2 = new Customer("Priit", 10000);
        dotKomm.addCustomer(customer1);
        dotKomm.addCustomer(customer2);

        customer1.buy(dotKomm, product1, 100);
        customer1.buy(dotKomm, product2, 100);

        ArrayList<Customer> excpectedCustomers = new ArrayList<Customer>();
        excpectedCustomers.add(customer1);
        excpectedCustomers.add(customer2);

        Assertions.assertInstanceOf(Bill.class, dotKomm.getCustomerMap().get(customer1));
    }

    @Test
    void getSumFromBill() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Product product1 = new Product("Priiti", 1);
        Product product2 = new Product("Priit", 1);
        dotKomm.addProduct(product1);
        dotKomm.addProduct(product2);
        // each time a customer is added a bill is opened in the store.
        Customer customer1 = new Customer("Priit", 10000);
        Customer customer2 = new Customer("Priit", 10000);
        dotKomm.addCustomer(customer1);
        dotKomm.addCustomer(customer2);

        customer1.buy(dotKomm, product1, 100);
        customer1.buy(dotKomm, product2, 100);

        ArrayList<Customer> excpectedCustomers = new ArrayList<Customer>();
        excpectedCustomers.add(customer1);
        excpectedCustomers.add(customer2);

        float excpectedAmount = 200f;
        // bills have amounts and products.
        Assertions.assertEquals(excpectedAmount, dotKomm.getCustomerMap().get(customer1).sum());
    }

    @Test
    void itemSearchByName() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Product product1 = new Product("Priiti", 1);
        Product product2 = new Product("Priit", 1);
        dotKomm.addProduct(product1);
        dotKomm.addProduct(product2);
        // each time a customer is added a bill is opened in the store.
        Customer customer1 = new Customer("Priit", 10000);
        Customer customer2 = new Customer("Priit", 10000);
        dotKomm.addCustomer(customer1);
        dotKomm.addCustomer(customer2);

        customer1.buy(dotKomm, product1, 100);
        customer1.buy(dotKomm, product2, 100);

        ArrayList<Customer> excpectedCustomers = new ArrayList<Customer>();
        excpectedCustomers.add(customer1);
        excpectedCustomers.add(customer2);

        // bills have amounts and products.
        Assertions.assertEquals(dotKomm.itemSearchByName("priiti"), List.of(product1));
    }

    @Test
    void findMostPopularProducts() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Product product1 = new Product("Priiti", 1);
        Product product2 = new Product("Priit", 1);
        dotKomm.addProduct(product1);
        dotKomm.addProduct(product2);
        // each time a customer is added a bill is opened in the store.
        Customer customer1 = new Customer("Priit", 10000);
        Customer customer2 = new Customer("Priit", 10000);
        dotKomm.addCustomer(customer1);
        dotKomm.addCustomer(customer2);

        customer1.buy(dotKomm, product1, 101);
        customer1.buy(dotKomm, product2, 100);

        Map.Entry<Product, Integer> entry1 = new AbstractMap.SimpleEntry<>(product1, 101);
        Map.Entry<Product, Integer> entry2 = new AbstractMap.SimpleEntry<>(product2, 100);
        List<Map.Entry<Product, Integer>> expectedList = new ArrayList<>();
        expectedList.add(entry1);
        expectedList.add(entry2);

        // returns two different products, the more popular one in front.
        Assertions.assertEquals(dotKomm.itemSearchByPopularity(), expectedList);
    }
}
