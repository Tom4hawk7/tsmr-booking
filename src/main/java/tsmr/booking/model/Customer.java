package tsmr.booking.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Customer {
    // properties
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate dob;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets = new HashSet<>();

    // getters and setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Ticket> getTickets() { return tickets; }
    public void setTickets(Set<Ticket> tickets) { this.tickets = tickets;  }

    // helper functions
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setCustomer(this);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        ticket.setCustomer(null);
    }

    // constructors
    public Customer() {}

    public Customer(String name, LocalDate dob, String phone, String email) {
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
    }
}
