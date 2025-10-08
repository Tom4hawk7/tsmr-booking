package tsmr.booking.controller;

import tsmr.booking.service.TicketService;

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