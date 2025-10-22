package tsmr.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tsmr.booking.model.Flight;
import tsmr.booking.model.Ticket;
import tsmr.booking.service.BookingService;


@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Book a flight for a customer
    @PostMapping("/bookings")
    public ResponseEntity<Ticket> bookFlight(@RequestParam Long customerId,
                                             @RequestParam Long flightId) {
        Ticket ticket = bookingService.bookFlight(customerId, flightId);
        return ResponseEntity.ok(ticket);
    }

        @GetMapping("/flights")
        public List<Flight> getFlights() {
        return bookingService.listFlights();
    }
}
