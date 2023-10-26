package no.jlwcrews.jpademo.controller;

import jakarta.servlet.http.HttpServletRequest;
import no.jlwcrews.jpademo.model.Customer;
import no.jlwcrews.jpademo.model.Item;
import no.jlwcrews.jpademo.model.NewOrderDTO;
import no.jlwcrews.jpademo.model.Order;
import no.jlwcrews.jpademo.service.CustomerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(HttpServletRequest request, @PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PostMapping("/{id}")
    public Customer createCustomerOrders(@PathVariable Long id, @RequestBody NewOrderDTO newOrderDTO) {

        Customer customer = customerService.getCustomerById(id);
        Order order = new Order();
        for (Item item : newOrderDTO.getItems()) {
            order.getItems().add(new Item(item.getItemName(), item.getItemQuantity()));
        }
        customer.getOrders().add(order);
        return customerService.addCustomer(customer);
    }
}
