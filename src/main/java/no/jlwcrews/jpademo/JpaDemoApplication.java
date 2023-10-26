package no.jlwcrews.jpademo;

import no.jlwcrews.jpademo.model.Customer;
import no.jlwcrews.jpademo.model.Order;
import no.jlwcrews.jpademo.repository.CustomerRepository;
import no.jlwcrews.jpademo.repository.OrderRepository;
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
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer("Joe Bob Briggs", "joe@joebob.com"));
        };
    }

}
