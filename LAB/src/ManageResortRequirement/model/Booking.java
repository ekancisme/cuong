package ManageResortRequirement.model;

import java.time.LocalDate;

public class Booking implements Comparable<Booking> {
    private String bookingId;
    private LocalDate bookingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String customerId;
    private String serviceId;

    public Booking(String bookingId, LocalDate bookingDate, LocalDate startDate, LocalDate endDate, String customerId, String serviceId) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.serviceId = serviceId;
    }

    // Getters and Setters

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public int compareTo(Booking o) {
        return this.bookingDate.compareTo(o.bookingDate);
    }

    @Override
    public String toString() {
        return bookingId + "," + bookingDate + "," + startDate + "," + endDate + "," + customerId + "," + serviceId;
    }
}
