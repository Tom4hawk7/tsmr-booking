package tsmr.booking.service;

import org.springframework.stereotype.Service;
import tsmr.booking.model.Customer;
import tsmr.booking.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Long findCustomerIdByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(Customer::getId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }
}