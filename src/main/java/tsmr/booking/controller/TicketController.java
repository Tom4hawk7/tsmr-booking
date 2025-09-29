package tsmr.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsmr.booking.model.Ticket;
import tsmr.booking.repository.TicketRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketRepository ticketRepo;

    public TicketController(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    // List all tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    // Get a ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        return ResponseEntity.of(ticketRepo.findById(id));
    }

    // Find all tickets for a specific customer
    @GetMapping("/by-customer/{customerId}")
    public List<Ticket> getTicketsByCustomer(@PathVariable Long customerId) {
        return ticketRepo.findByCustomer_Id(customerId);
    }

    // Find all tickets for a specific flight
    @GetMapping("/by-flight/{flightId}")
    public List<Ticket> getTicketsByFlight(@PathVariable Long flightId) {
        return ticketRepo.findByFlight_Id(flightId);
    }
}
