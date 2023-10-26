package no.jlwcrews.jpademo.repository;

import no.jlwcrews.jpademo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
