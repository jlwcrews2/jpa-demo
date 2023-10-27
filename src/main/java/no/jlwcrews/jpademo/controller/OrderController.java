package no.jlwcrews.jpademo.controller;

import no.jlwcrews.jpademo.model.Order;
import no.jlwcrews.jpademo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
