package clienttest;

import java.time.LocalDate;
import java.util.ArrayList;

import ee.taltech.iti0202.exam.NotEnoughMoneyException;
import ee.taltech.iti0202.exam.Order;
import ee.taltech.iti0202.exam.customer.Customer;
import ee.taltech.iti0202.exam.dotkomm.DotKomm;
import ee.taltech.iti0202.exam.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {
    @Test
    void clientHasAName() {
        Customer customer = new Customer("Priit", 10000);

        String customerName = "Priit";

        Assertions.assertEquals(customerName, customer.getName());
    }

    @Test
    void clientHasABudget() {
        Customer customer = new Customer("Priit", 10000);

        float customerBudget = 10000f;

        Assertions.assertEquals(customerBudget, customer.getBudget());
    }

    @Test
    void clientHasHistory() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Customer customer = new Customer("Priit", 10000);
        Product product = new Product("meat", 10);
        dotKomm.addCustomer(customer);

        customer.buy(dotKomm, product, 100);

        Order order = new Order(product, 100);
        ArrayList<Order> excpectedHistory = new ArrayList<Order>();
        excpectedHistory.add(order);

        Assertions.assertEquals(customer.getHistory().size(), excpectedHistory.size());
    }

    @Test
    void clientCanOnlyBuyWithEnoughMoneyDoesNotHave() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Customer customer = new Customer("Priit", 100);
        Product product = new Product("meat", 10);
        dotKomm.addCustomer(customer);

        Assertions.assertThrows(NotEnoughMoneyException.class,
                () -> customer.buy(dotKomm, product, 100));}

    @Test
    void clientCanOnlyBuyWithEnoughMoneyDoesHaveEnough() throws NotEnoughMoneyException {
        DotKomm dotKomm = new DotKomm();
        Customer customer = new Customer("Priit", 1000);
        Product product = new Product("meat", 10);
        dotKomm.addCustomer(customer);

        customer.buy(dotKomm, product, 100);

        float excpectedMoney = 0;

        Assertions.assertEquals(excpectedMoney, customer.getBudget());}
}
