package no.jlwcrews.jpademo;

import com.github.javafaker.Faker;
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

        Faker faker = new Faker();
        return args -> {
            for (int i = 0; i < 100; i++) {
                Customer c = customerRepository.save(new Customer(faker.name().fullName(), faker.internet().emailAddress()));
                Address a = addressRepository.save(new Address(faker.address().fullAddress()));
                c.getAddresses().add(a);
                customerRepository.save(c);
            }
        };
    }
}
