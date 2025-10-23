package tsmr.booking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tsmr.booking.model.Customer;
import tsmr.booking.model.Flight;
import tsmr.booking.repository.CustomerRepository;
import tsmr.booking.repository.FlightRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class BookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedCustomer(CustomerRepository customerRepo) {
        return args -> {
            String email = "matthewsteele05@gmail.com";
            if (customerRepo.findByEmail(email).isEmpty()) {
                Customer c = new Customer();
                c.setEmail(email);
                c.setName("Matthew Steele");
                c.setDob(LocalDate.of(2000, 1, 1));
                c.setPhone("0400000000");
                customerRepo.save(c);
                System.out.println("✅ Seeded customer: " + email);
            }
        };
    }

    @Bean
    public CommandLineRunner seedFlight(FlightRepository flightRepo) {
        return args -> {
            LocalDateTime departureTime = LocalDateTime.now().plusDays(1);

            boolean exists = flightRepo.findAll().stream().anyMatch(f ->
                    "Sydney".equals(f.getLocation()) &&
                            "Melbourne".equals(f.getDestination()) &&
                            f.getDepart().toLocalDate().equals(departureTime.toLocalDate())
            );

            if (!exists) {
                Flight flight = new Flight();
                flight.setAirline("Qantas");
                flight.setCapacity(180);
                flight.setPrice(299.99f);
                flight.setLocation("Sydney");
                flight.setDestination("Melbourne");
                flight.setDepart(departureTime);
                flight.setArrival(departureTime.plusHours(1));
                flightRepo.save(flight);
                System.out.println("✅ Seeded flight with generated ID: " + flight.getId());
            }
        };
    }
}