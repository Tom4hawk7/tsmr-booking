package tsmr.booking.controller;

import tsmr.booking.service.TicketService;
import tsmr.booking.model.Ticket;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/customer/{id}")
    public List<Ticket> getTickets(@PathVariable Long customerId) {
        return ticketService.getTicketsByCustomer(customerId);
    }
}