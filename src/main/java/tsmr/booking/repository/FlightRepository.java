package tsmr.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tsmr.booking.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, String> {
    Optional<Flight> findById(Long id);
    List<Flight> findByLocationAndDestination(String location, String destination);
}