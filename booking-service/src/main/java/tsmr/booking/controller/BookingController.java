package tsmr.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import tsmr.booking.model.Flight;
import tsmr.booking.model.Ticket;
import tsmr.booking.payload.BookingRequest;
import tsmr.booking.service.BookingService;
import tsmr.booking.service.CustomerService;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;
    private final CustomerService customerService;

    public BookingController(BookingService bookingService, CustomerService customerService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<Ticket> bookFlight(@RequestBody BookingRequest request,
                                             @RequestParam Long flightId,
                                             Authentication authentication) {
        String email = authentication.getName(); // Extracted from JWT
        Ticket ticket = bookingService.bookFlight(request, flightId, email);
        return ResponseEntity.ok(ticket);
    }

    // List available flights
    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return bookingService.listFlights();
    }
}