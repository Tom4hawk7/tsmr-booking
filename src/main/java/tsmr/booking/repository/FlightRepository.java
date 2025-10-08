package tsmr.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tsmr.booking.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByLocationAndDestination(String location, String destination);
}