package tsmr.booking.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tsmr.booking.model.Customer;
import tsmr.booking.model.Flight;
import tsmr.booking.model.Ticket;
import tsmr.booking.payload.BookingRequest;
import tsmr.booking.repository.CustomerRepository;
import tsmr.booking.repository.FlightRepository;
import tsmr.booking.repository.TicketRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final CustomerRepository customerRepo;
    private final FlightRepository flightRepo;
    private final TicketRepository ticketRepo;

    public BookingService(CustomerRepository customerRepo,
                          FlightRepository flightRepo,
                          TicketRepository ticketRepo) {
        this.customerRepo = customerRepo;
        this.flightRepo = flightRepo;
        this.ticketRepo = ticketRepo;
    }

    public List<Flight> listFlights() {
        return flightRepo.findAll();
    }

    @Transactional
    public Ticket bookFlight(BookingRequest request, Long flightId, String customerEmail) {
        Customer customer = customerRepo.findByEmail(customerEmail)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        long bookedCount = ticketRepo.countByFlight_Id(flightId);
        if (bookedCount >= flight.getCapacity()) {
            throw new IllegalStateException("Flight is full");
        }

        Ticket ticket = new Ticket();
        ticket.setBookingDate(LocalDate.now());
        ticket.setSeatNumber(request.getSeatNumber());   // ✅ Set seat number
        ticket.setClassType(request.getClassType());     // ✅ Set class type
        ticket.setCustomer(customer);
        ticket.setFlight(flight);

        return ticketRepo.save(ticket);
    }
}