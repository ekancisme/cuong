package ManageResortRequirement.services;

import ManageResortRequirement.model.Booking;
import ManageResortRequirement.model.Contract;
import ManageResortRequirement.model.Facility;
import ManageResortRequirement.repository.BookingRepository;
import ManageResortRequirement.repository.BookingRepositoryImpl;
import ManageResortRequirement.repository.ContractRepositoryImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private TreeSet<Booking> bookingTreeSet;
    private Queue<Booking> bookingQueue;
    Scanner scanner = new Scanner(System.in);
    public BookingServiceImpl() {
        bookingRepository = new BookingRepositoryImpl() {
            @Override
            public void save() {

            }

            @Override
            public void findBookingByCustomerId() {

            }

            @Override
            public void readFromFile() {

            }

            @Override
            public TreeSet<Booking> getTreeBooking() {
                return null;
            }
        };
        bookingRepository.loadBookings();
        bookingTreeSet = bookingRepository.getAllBookings();
        bookingQueue = new LinkedList<>(bookingTreeSet);
    }

    @Override
    public void displayList() {
        if (bookingTreeSet.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Booking booking : bookingTreeSet) {

                String formattedBookingDate = booking.getBookingDate().format(dateFormatter);
                String formattedStartDate = booking.getStartDate().format(dateFormatter);
                String formattedEndDate = booking.getEndDate().format(dateFormatter);

                System.out.printf("Booking ID: %s, Booking Date: %s, Start Date: %s, End Date: %s, Customer ID: %s, Service ID: %s%n",
                        booking.getBookingId(),
                        formattedBookingDate,
                        formattedStartDate,
                        formattedEndDate,
                        booking.getCustomerId(),
                        booking.getServiceId());
            }
        }
    }


    @Override
    public void addNewBooking(Booking newBooking) {
        if (checkIDBooking(newBooking.getBookingId())) {
            bookingTreeSet.add(newBooking);
            bookingQueue.offer(newBooking);
            bookingRepository.saveBookings();
            System.out.println("New booking added successfully!");
        } else {
            System.err.println("Booking ID already exists.");
        }
    }

    @Override
    public void createContract() {
        if (bookingQueue.isEmpty()) {
            System.out.println("No bookings available for contract.");
            return;
        }

        System.out.println("Current bookings in queue:");
        for (Booking booking : bookingQueue) {
            System.out.println("BookingID: " + booking.getBookingId() + ", CustomerID: " + booking.getCustomerId());
        }
        System.out.print("Enter Customer ID for contract: ");
        String customerId = scanner.nextLine();

        // Tìm booking có CustomerID khớp
        Booking matchingBooking = null;
        for (Booking booking : bookingQueue) {
            if (booking.getCustomerId().equals(customerId)) {
                matchingBooking = booking;
                break;
            }
        }

        if (matchingBooking == null) {
            System.out.println("No matching booking found for Customer ID: " + customerId);
            return;
        }

        bookingQueue.remove(matchingBooking);

        double depositAmount = 0;
        double totalPayment = 0;

        while (true) {
            try {
                System.out.print("Enter deposit amount: ");
                depositAmount = scanner.nextDouble();
                if (depositAmount < 0) {
                    System.out.println("Deposit amount must be non-negative. Please try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        while (true) {
            try {
                System.out.print("Enter total payment: ");
                totalPayment = scanner.nextDouble();
                if (totalPayment < 0) {
                    System.out.println("Total payment must be non-negative. Please try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        LocalDate contractDate = LocalDate.now();

        ContractRepositoryImpl contractRepo = new ContractRepositoryImpl();
        String contractId = "C" + (contractRepo.getContracts().size() + 1);

        Contract contract = new Contract(contractId, matchingBooking.getBookingId(), customerId, depositAmount, totalPayment, contractDate);
        contractRepo.getContracts().add(contract);
        contractRepo.saveContracts();

        System.out.println("Contract created successfully for booking: " + matchingBooking.getBookingId());
    }
    public boolean checkIDBooking(String id) {
        return bookingTreeSet.stream().noneMatch(booking -> booking.getBookingId().equals(id));
    }

}
