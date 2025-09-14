package tsmr.booking.model;

import jakarta.persistence.Column;

public class UserFlight {
    // properties
    @Column private Long userId;
    @Column private Long flightId;

    // getters
    public Long getUserId() { return userId; }
    public Long getFlightId() { return flightId; }

    // setters
    public void setUserId(Long userId) { this.userId = userId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }

    // definition
    public UserFlight(Long userId, Long flightId) {
        this.userId = userId;
        this.flightId = flightId;
    }
}
