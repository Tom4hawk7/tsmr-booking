package tsmr.booking.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Ticket {
    // properties
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference("customer-tickets")
    private Customer customer;

    @ManyToOne @JoinColumn(name = "flight_id", nullable = false)
    @JsonBackReference("flight-tickets")
    private Flight flight;

    private String seat;
    private LocalDate bookingDate;


    // getters and setters
    public Long getId() { return id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; } 


    // constructors
    Ticket() {}

    Ticket(Customer customer, Flight flight) {
        this.customer = customer;
        this.flight = flight;
    }

    Ticket(Customer customer, Flight flight, String seat, LocalDate bookingDate) {
        this.customer = customer;
        this.flight = flight;
        this.seat = seat;
        this.bookingDate = bookingDate;
    }
}
