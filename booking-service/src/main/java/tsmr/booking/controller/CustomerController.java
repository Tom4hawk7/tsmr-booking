package tsmr.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsmr.booking.model.Customer;
import tsmr.booking.repository.CustomerRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepo;

    public CustomerController(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    // Create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer saved = customerRepo.save(customer);
        return ResponseEntity
                .created(URI.create("/api/customers/" + saved.getId()))
                .body(saved);
    }

    // List all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    // Get a specific customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return ResponseEntity.of(customerRepo.findById(id));
    }

    // Delete a customer (optional)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
