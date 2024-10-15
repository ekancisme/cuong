package ManageResortRequirement.repository;

import ManageResortRequirement.model.Booking;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.TreeSet;

public abstract class BookingRepositoryImpl implements BookingRepository {
    private static final String FILE_PATH = "src\\ManageResortRequirement\\data\\booking.csv";
    private TreeSet<Booking> bookingTreeSet = new TreeSet<>();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void loadBookings() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) {
                    System.out.println("Insufficient data for booking: " + line);
                    continue;
                }

                String bookingId = data[0].trim();
                LocalDate bookingDate = LocalDate.parse(data[1].trim(), formatter);
                LocalDate startDate = LocalDate.parse(data[2].trim(), formatter);
                LocalDate endDate = LocalDate.parse(data[3].trim(), formatter);
                String customerId = data[4].trim();
                String serviceId = data[5].trim();

                Booking booking = new Booking(bookingId, bookingDate, startDate, endDate, customerId, serviceId);
                bookingTreeSet.add(booking);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }

    @Override
    public void saveBookings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Booking booking : bookingTreeSet) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedBookingDate = booking.getBookingDate().format(dateFormatter);
                String formattedStartDate = booking.getStartDate().format(dateFormatter);
                String formattedEndDate = booking.getEndDate().format(dateFormatter);
                writer.write(String.format("%s,%s,%s,%s,%s,%s",
                        booking.getBookingId(),
                        formattedBookingDate,
                        formattedStartDate,
                        formattedEndDate,
                        booking.getCustomerId(),
                        booking.getServiceId()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public TreeSet<Booking> getAllBookings() {
        return bookingTreeSet;
    }
}
