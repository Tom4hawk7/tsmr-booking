package tsmr.booking.repository;

public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByLocationAndDestination(String location, String destination);
}