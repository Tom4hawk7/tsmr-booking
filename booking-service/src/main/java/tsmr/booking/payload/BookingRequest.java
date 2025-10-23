package tsmr.booking.payload;

public class BookingRequest {
    private String seatNumber;
    private String classType;

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }
}
