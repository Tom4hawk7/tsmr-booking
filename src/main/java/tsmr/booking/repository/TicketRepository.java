package tsmr.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tsmr.booking.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomerId(Long customerId);
}