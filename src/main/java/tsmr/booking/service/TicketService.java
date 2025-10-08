package tsmr.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tsmr.booking.model.Ticket;
import tsmr.booking.repository.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    // create
    public Ticket createTicket(Ticket ticket) {
        return repository.save(ticket);
    }

    // read
    public Optional<Ticket> getTicket(Long id) {
        return repository.findById(id);
    }

    public List<Ticket> getAllTickets() {
        return repository.findAll();
    }

    public Optional<Ticket> getTicketByCustomerAndFlight(Long customerId, Long flightId) {
        return repository.findByCustomerIdAndFlightId(customerId, flightId);
    }

    public List<Ticket> getTicketsByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public List<Ticket> getTicketsByFlight(Long flightId) {
        return repository.findByFlightId(flightId);
    }

    // update
    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        return repository.findById(id)
                .map(ticket -> applyUpdates(ticket, updatedTicket))
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));
    }

    public Ticket updateTicketByCustomerAndFlight(Long customerId, Long flightId, Ticket updatedTicket) {
        return repository.findByCustomerIdAndFlightId(customerId, flightId)
                .map(ticket -> applyUpdates(ticket, updatedTicket))
                .orElseThrow(() -> new RuntimeException("Ticket not found with customer id " + customerId + " and flight id " + flightId)); 
    }

    private Ticket applyUpdates(Ticket existingTicket, Ticket updatedTicket) {
        existingTicket.setSeat(updatedTicket.getSeat());
        existingTicket.setBookingDate(updatedTicket.getBookingDate());
        return repository.save(existingTicket);
    }

    // delete
    public void deleteTicket(Long id) {
        repository.deleteById(id);
    }

}