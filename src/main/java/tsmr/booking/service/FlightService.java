package tsmr.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tsmr.booking.model.Flight;
import tsmr.booking.repository.FlightRepository;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // create
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // read
    public Optional<Flight> getFlight(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> searchFlights(String location, String destination) {
        return flightRepository.findByLocationAndDestination(location, destination);
    }

    // update
     public Flight updateFlight(Long id, Flight updatedFlight) {
        return repository.findById().map(flight -> {
            flight.setLocation(updatedFlight.getLocation());
            flight.setDestination(updatedFlight.getLocation());
            flight.setDepart(updatedFlight.getDepart());
            flight.setArrival(updatedFlight.getArrival());
            return repository.save(flight);
        }).orElseThrow(() -> new RuntimeException("Flight not found with id " + id));
    }


    // delete
}