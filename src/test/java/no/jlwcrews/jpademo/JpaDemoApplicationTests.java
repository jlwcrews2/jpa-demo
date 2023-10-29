package no.jlwcrews.jpademo;

import no.jlwcrews.jpademo.model.Address;
import no.jlwcrews.jpademo.model.Customer;
import no.jlwcrews.jpademo.model.Item;
import no.jlwcrews.jpademo.model.Order;
import no.jlwcrews.jpademo.repository.AddressRepository;
import no.jlwcrews.jpademo.repository.CustomerRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JpaDemoApplicationTests {

    private Order getOrders() {
        return new Order();
    }

    private Item getItem(String name, Integer qty) {
        return new Item(name, qty);
    }

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Test
    @org.junit.jupiter.api.Order(1)
    void testCustomerOrdersAndItems() {

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

    @Test
    @org.junit.jupiter.api.Order(2)
    void testAddresses() {
        Address shippingAddress = new Address("123 Street Place");
        Address billingAddress = new Address("999 Uptown Blvd");
        Address a1 = addressRepository.save(shippingAddress);
        Address a2 = addressRepository.save(billingAddress);

        assert a1.getAddress().contains("123 Street Place");
        assert a2.getAddress().contains("999 Uptown Blvd");
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void testAddingAddressesToCustomers() {
        Customer joeBob = customerRepository.findById(1L).orElse(null);
        Address shippingAddress = addressRepository.findById(1L).orElse(null);
        Address billingAddress = addressRepository.findById(2L).orElse(null);
        joeBob.getAddresses().add(shippingAddress);
        joeBob.getAddresses().add(billingAddress);
        Customer c1 = customerRepository.save(joeBob);
        assert c1.getAddresses().size() == 2;
        assert c1.getAddresses().get(0).getAddress().equals("123 Street Place");

        Customer jimbob = customerRepository.save(new Customer("Jim Bob Luke", "jim@bob.com"));

        jimbob.getAddresses().add(shippingAddress);
        customerRepository.save(jimbob);

    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void addressThing() {
        addressRepository.findById(1L).ifPresent(address -> address.getCustomers().forEach(customer -> System.out.println(customer.getCustomerName())));
    }
}
