package no.jlwcrews.jpademo;

import no.jlwcrews.jpademo.model.Customer;
import no.jlwcrews.jpademo.repository.CustomerRepository;
import no.jlwcrews.jpademo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class DBUnitTesting {

    @Autowired
    CustomerRepository customerRepository;


    @Test
    void shouldFetchCustomers(){
        List<Customer> customers = customerRepository.findAll();
        assert customers.size() == 100;
    }

    @Test
    void customerShouldHaveAddress(){
        Customer customer = customerRepository.findById(1L).get();
        assert customer.getAddresses().size() == 1;

    }
}
