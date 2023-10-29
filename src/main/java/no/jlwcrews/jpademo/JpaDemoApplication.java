package no.jlwcrews.jpademo;

import no.jlwcrews.jpademo.model.Address;
import no.jlwcrews.jpademo.model.Customer;
import no.jlwcrews.jpademo.repository.AddressRepository;
import no.jlwcrews.jpademo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            CustomerRepository customerRepository,
            AddressRepository addressRepository) {
        return args -> {
            Customer c = customerRepository.save(new Customer("Joe Bob Briggs", "joe@joebob.com"));
            Address a = addressRepository.save(new Address("1234 Place Street, #3"));
            c.getAddresses().add(a);
            customerRepository.save(c);
        };
    }

}
