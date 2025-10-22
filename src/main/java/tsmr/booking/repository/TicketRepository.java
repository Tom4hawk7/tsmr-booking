package tsmr.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tsmr.booking.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    long countByFlight_Id(Long flightId);

    List<Ticket> findByCustomer_Id(Long customerId);

    List<Ticket> findByFlight_Id(Long flightId);
}
