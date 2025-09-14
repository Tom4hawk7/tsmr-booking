package tsmr.booking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Entity
public class User {
    // properties
    @Id @GeneratedValue
    private Long id;

    @Column private String name;
    @Column private LocalDate dob;
    @Column private String phone;

    // getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDob() { return dob; }
    public String getPhone() { return phone; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setPhone(String phone) { this.phone = phone; }

    public User() {}
}
