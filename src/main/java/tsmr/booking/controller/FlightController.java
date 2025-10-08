package tsmr.booking.controller;

import tsmr.booking.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/search")
    public List<Flight> searchFlights (
        @RequestParam String location,
        @RequestParam String destination) {
            return flightService.searchFlights(location, destination);
    }
}