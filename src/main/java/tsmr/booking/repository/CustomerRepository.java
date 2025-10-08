package tsmr.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tsmr.booking.model.Customer;



public interface CustomerRepository extends JpaRepository <Customer, Long> {
    Optional<Customer> findById(Long id);
    Optional<Customer> findByEmail(String email);
}