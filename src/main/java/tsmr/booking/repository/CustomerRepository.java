package tsmr.booking.repository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {
    Optional<Customer> findById(Long id);
    Optional<Customer> findByEmail(String email);
}