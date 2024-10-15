package ManageResortRequirement.services;

import ManageResortRequirement.model.Booking;

import java.util.Queue;
import java.util.TreeSet;

public interface BookingService {
    void displayList();
    void addNewBooking(Booking newBooking);
    void createContract();
}
