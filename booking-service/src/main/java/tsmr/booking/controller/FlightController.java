package tsmr.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsmr.booking.model.Flight;
import tsmr.booking.repository.FlightRepository;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightRepository flightRepo;

    public FlightController(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight saved = flightRepo.save(flight);
        return ResponseEntity.ok(saved);
    }
}
