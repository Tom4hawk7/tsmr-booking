package tsmr.booking.ai;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import tsmr.booking.model.Customer;
import tsmr.booking.model.Flight;
import tsmr.booking.model.Ticket;
import tsmr.booking.repository.CustomerRepository;
import tsmr.booking.repository.FlightRepository;
import tsmr.booking.repository.TicketRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class BookingTools {

    private final FlightRepository flightRepo;
    private final TicketRepository ticketRepo;
    private final CustomerRepository customerRepo;

    public BookingTools(FlightRepository flightRepo, TicketRepository ticketRepo, CustomerRepository customerRepo) {
        this.flightRepo = flightRepo;
        this.ticketRepo = ticketRepo;
        this.customerRepo = customerRepo;
    }

    // DTOs for json responses
    public record FlightSummary(Long id, String airline, String location,
                                String destination, String depart, String arrival, float price) {}

    public record TicketSummary(Long id, String customerName, String destination,
                                String bookingDate, String airline) {}

    // List all flights
    @Tool("List all available flights")
    public List<FlightSummary> listAllFlights() {
        return flightRepo.findAll().stream()
                .map(f -> new FlightSummary(
                        f.getId(),
                        f.getAirline(),
                        f.getLocation(),
                        f.getDestination(),
                        f.getDepart() != null ? f.getDepart().toString() : null,
                        f.getArrival() != null ? f.getArrival().toString() : null,
                        f.getPrice()
                ))
                .toList();
    }

    // Find flights by destination
    @Tool("Find flights by destination city")
    public List<FlightSummary> findFlightsByDestination(String destination) {
        return flightRepo.findAll().stream()
                .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                .map(f -> new FlightSummary(
                        f.getId(),
                        f.getAirline(),
                        f.getLocation(),
                        f.getDestination(),
                        f.getDepart() != null ? f.getDepart().toString() : null,
                        f.getArrival() != null ? f.getArrival().toString() : null,
                        f.getPrice()
                ))
                .toList();
    }

    // List all bookings (tickets)
    @Tool("List all existing bookings")
    public List<TicketSummary> listAllTickets() {
        return ticketRepo.findAll().stream()
                .map(t -> new TicketSummary(
                        t.getId(),
                        t.getCustomer() != null ? t.getCustomer().getName() : "Unknown",
                        t.getFlight() != null ? t.getFlight().getDestination() : "Unknown",
                        t.getBookingDate() != null ? t.getBookingDate().toString() : "Unknown",
                        t.getFlight() != null ? t.getFlight().getAirline() : "Unknown"
                ))
                .toList();
    }

    // Book a flight
    @Tool("Book a flight for a customer using customerId and flightId")
    public TicketSummary bookFlight(Long customerId, Long flightId) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID " + customerId));

        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found with ID " + flightId));

        Ticket ticket = new Ticket();
        ticket.setCustomer(customer);
        ticket.setFlight(flight);
        ticket.setBookingDate(LocalDate.now());

        Ticket saved = ticketRepo.save(ticket);

        return new TicketSummary(
                saved.getId(),
                customer.getName(),
                flight.getDestination(),
                saved.getBookingDate().toString(),
                flight.getAirline()
        );
    }
}
