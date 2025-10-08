package tsmr.booking.controller;

import tsmr.booking.service.TicketService;
import tsmr.booking.model.Ticket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // create
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }

    // read
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Ticket>> getTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Ticket>> getTicketsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(ticketService.getTicketsByCustomer(customerId));
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<List<Ticket>> getTicketsByFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(ticketService.getTicketsByFlight(flightId));
    }

    @GetMapping("/customer/{id}/flight/{id}")
    public ResponseEntity<Optional<Ticket>> getTicketByCustomerAndFlight(@PathVariable Long customerId, @PathVariable Long flightId) {
        return ResponseEntity.ok(ticketService.getTicketByCustomerAndFlight(customerId, flightId));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        try {
            return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/customer/{id}/flight/{id}")
    public ResponseEntity<Ticket> updateTicketByCustomerAndFlight(@PathVariable Long customerId, @PathVariable Long flightId, @RequestBody Ticket ticket) {
        try {
            return ResponseEntity.ok(ticketService.updateTicketByCustomerAndFlight(customerId, flightId, ticket));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

}