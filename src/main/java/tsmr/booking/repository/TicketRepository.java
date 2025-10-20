package tsmr.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tsmr.booking.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomerId(Long customerId);
    List<Ticket> findByFlightId(Long flightId);
    Optional<Ticket> findByCustomerIdAndFlightId(Long customerId, Long flightId);
}