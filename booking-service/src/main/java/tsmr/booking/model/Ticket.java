package tsmr.booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate bookingDate;

    private String seatNumber;     
    private String classType;      

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

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Ticket() { }
}