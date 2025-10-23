package tsmr.booking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    private int capacity;
    private float price;
    private String location;
    private String destination;
    private LocalDateTime depart;
    private LocalDateTime arrival;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("flight-tickets")
    private Set<Ticket> tickets = new HashSet<>();

    // ── getters / setters ──
    public Long getId() { return id; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDateTime getDepart() { return depart; }
    public void setDepart(LocalDateTime depart) { this.depart = depart; }

    public LocalDateTime getArrival() { return arrival; }
    public void setArrival(LocalDateTime arrival) { this.arrival = arrival; }

    public Set<Ticket> getTickets() { return tickets; }
    public void setTickets(Set<Ticket> tickets) { this.tickets = tickets; }

    // ── helper methods ──
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setFlight(this);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        ticket.setFlight(null);
    }

    public Flight() { }
}