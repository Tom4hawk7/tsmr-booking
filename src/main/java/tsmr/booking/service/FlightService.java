package tsmr.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tsmr.booking.model.Flight;
import tsmr.booking.repository.FlightRepository;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String location, String destination) {
        return flightRepository.findByLocationAndDestination(location, destination);
    }
}