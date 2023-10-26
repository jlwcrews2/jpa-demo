package no.jlwcrews.jpademo;

import no.jlwcrews.jpademo.model.Customer;
import no.jlwcrews.jpademo.model.Item;
import no.jlwcrews.jpademo.model.Order;
import no.jlwcrews.jpademo.repository.CustomerRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaDemoApplicationTests {

    private Order getOrders() {
        return new Order();
    }

    private Item getItem(String name, Integer qty) {
        return new Item(name, qty);
    }

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void initCustomerEntity() {

        Customer customer = new Customer("Joe Bob Briggs", "joe@thedrivein.com");

        Order order1 = getOrders();
        Item wheel = getItem("wheel", 3);
        Item gear = getItem("gear", 10);
        order1.getItems().add(wheel);
        order1.getItems().add(gear);

        Order order2 = getOrders();
        Item piston = getItem("piston", 5);
        Item cog = getItem("cog", 29);
        order2.getItems().add(piston);
        order2.getItems().add(cog);

        customer.getOrders().add(order1);
        customer.getOrders().add(order2);
        Customer c = customerRepository.save(customer);
        assert c.getOrders().size() == 2;
        assert c.getOrders().get(0).getItems().size() == 2;

    }

}
