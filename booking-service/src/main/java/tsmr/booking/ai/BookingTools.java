package tsmr.booking.ai;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import tsmr.booking.model.Flight;
import tsmr.booking.model.Ticket;
import tsmr.booking.repository.FlightRepository;
import tsmr.booking.repository.TicketRepository;

import java.util.List;

@Component
public class BookingTools {

    private final FlightRepository flightRepo;
    private final TicketRepository ticketRepo;

    public BookingTools(FlightRepository flightRepo, TicketRepository ticketRepo) {
        this.flightRepo = flightRepo;
        this.ticketRepo = ticketRepo;
    }

    @Tool("List all available flights")
    public List<Flight> listAllFlights() {
        return flightRepo.findAll();
    }

    @Tool("Find flights by destination city")
    public List<Flight> findFlightsByDestination(String destination) {
        return flightRepo.findAll()
                .stream()
                .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                .toList();
    }

    @Tool("List all bookings (tickets)")
    public List<Ticket> listAllTickets() {
        return ticketRepo.findAll();
    }
}
