package tsmr.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tsmr.booking.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}