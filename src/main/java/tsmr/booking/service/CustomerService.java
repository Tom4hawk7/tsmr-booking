package tsmr.booking.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tsmr.booking.model.Customer;
import tsmr.booking.repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // create
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // read
    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // update

   



    // delete



}