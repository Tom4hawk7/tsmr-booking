package tsmr.booking.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import tsmr.booking.model.Customer;
import tsmr.booking.repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository repository;


    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    // create
    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    // read
    public Optional<Customer> getCustomer(Long id) {
        return repository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    // update
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return repository.findById(id).map(customer -> {
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhone(updatedCustomer.getPhone());
            return repository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    // delete
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }


    // delete



}