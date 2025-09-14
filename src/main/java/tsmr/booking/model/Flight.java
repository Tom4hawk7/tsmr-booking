package tsmr.booking.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Flight {
    // properties
    @Id @GeneratedValue
    private Long id;

    @Column private String airline;
    @Column private int capacity;
    @Column private float price;

    @Column private String location;
    @Column private String destination;
    @Column private LocalDateTime depart;
    @Column private LocalDateTime arrival;

    // getters
    public Long getId() { return id; }
    public String getAirline() { return airline; }
    public int getCapacity() { return capacity; }
    public float getPrice() { return price; }

    public String getLocation() { return location; }
    public String getDestination() { return destination; }
    public LocalDateTime getDepart() { return depart; }
    public LocalDateTime getArrival() { return arrival; }

    // setters
    public void setAirline(String airline) { this.airline = airline; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setPrice(float price) { this.price = price; }

    public void setLocation(String location) { this.location = location; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setDepart(LocalDateTime depart) { this.depart = depart; }
    public void setArrival(LocalDateTime arrival) { this.arrival = arrival; }

    public Flight() {}

}
