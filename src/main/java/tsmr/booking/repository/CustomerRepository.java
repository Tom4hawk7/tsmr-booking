package tsmr.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tsmr.booking.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
