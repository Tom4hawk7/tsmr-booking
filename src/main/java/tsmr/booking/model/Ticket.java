package tsmr.booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer-tickets")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonBackReference("flight-tickets")
    private Flight flight;

    // ── getters / setters ──
    public Long getId() { return id; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Ticket() { }
}
