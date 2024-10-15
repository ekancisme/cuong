package ManageResortRequirement.model;

import java.time.LocalDate;

public class Contract {
    private String contractId;
    private String bookingId;
    private String customerId;
    private double depositAmount;
    private double totalPayment;
    private LocalDate contractDate;

    public Contract(String contractId, String bookingId, String customerId, double depositAmount, double totalPayment, LocalDate contractDate) {
        this.contractId = contractId;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.depositAmount = depositAmount;
        this.totalPayment = totalPayment;
        this.contractDate = contractDate;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%.2f,%.2f,%s",
                contractId, bookingId, customerId, depositAmount, totalPayment, contractDate);
    }
}