package tsmr.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tsmr.booking.model.Ticket;
import tsmr.booking.repository.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTicketsByCustomer(Long customerId) {
        return ticketRepository.findByCustomerId(customerId);
    }
}