package ManageResortRequirement.repository;

import ManageResortRequirement.model.Booking;

import java.util.TreeSet;

public interface BookingRepository {
    void save();

    void findBookingByCustomerId();
    void loadBookings(); // Đọc dữ liệu từ file
    void saveBookings();
    void readFromFile();

    TreeSet<Booking> getTreeBooking();

    TreeSet<Booking> getAllBookings();
}
