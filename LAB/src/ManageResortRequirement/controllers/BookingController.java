package ManageResortRequirement.controllers;

import ManageResortRequirement.model.Booking;
import ManageResortRequirement.model.Contract;
import ManageResortRequirement.repository.ContractRepositoryImpl;
import ManageResortRequirement.services.BookingServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BookingController {
    private BookingServiceImpl bookingService;
    private Scanner scanner;

    public BookingController() {
        bookingService = new BookingServiceImpl();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Booking Management Menu ---");
            System.out.println("1. Display All Bookings");
            System.out.println("2. Add New Booking");
            System.out.println("3. Create Contract");
            System.out.println("4. Display All Contracts");
            System.out.println("5. Edit Contract");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookingService.displayList();
                    break;
                case 2:
                    addNewBooking();
                    break;
                case 3:
                    bookingService.createContract();
                    break;
                case 4:
                    displayFullContracts();
                    break;
                case 5:
                    editContract();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
    }

    private void addNewBooking() {
        System.out.print("Enter Booking ID (format: BKxxxx): ");
        String bookingId = scanner.nextLine();
        while (!isValidBookingId(bookingId)) {
            System.out.println("Invalid Booking ID format. Please enter again (format: BKxxxx): ");
            bookingId = scanner.nextLine();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate bookingDate = null;
        LocalDate startDate = null;
        LocalDate endDate = null;


        while (bookingDate == null) {
            System.out.print("Enter Booking Date (dd/MM/yyyy): ");
            try {
                bookingDate = LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date as dd/MM/yyyy.");
            }
        }

        while (startDate == null || (bookingDate != null && !startDate.isAfter(bookingDate))) {
            System.out.print("Enter Start Date (dd/MM/yyyy): ");
            try {
                startDate = LocalDate.parse(scanner.nextLine(), formatter);
                if (startDate != null && !startDate.isAfter(bookingDate)) {
                    System.out.println("Start Date must be after Booking Date. Please enter again.");
                    startDate = null; // Reset startDate
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date as dd/MM/yyyy.");
            }
        }


        while (endDate == null || (startDate != null && !endDate.isAfter(startDate))) {
            System.out.print("Enter End Date (dd/MM/yyyy): ");
            try {
                endDate = LocalDate.parse(scanner.nextLine(), formatter);
                if (endDate != null && !endDate.isAfter(startDate)) {
                    System.out.println("End Date must be after Start Date. Please enter again.");
                    endDate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date as dd/MM/yyyy.");
            }
        }

        System.out.print("Enter Customer ID (format: CUS-xxxx): ");
        String customerId = scanner.nextLine();
        while (!isValidCustomerId(customerId)) {
            System.out.println("Invalid Customer ID format. Please enter again (format: CUS-xxxx): ");
            customerId = scanner.nextLine();
        }

        System.out.print("Enter Service ID (format: SVRO-xxxx, SVVL-xxxx or SVHO-xxxx): ");
        String serviceId = scanner.nextLine();
        while (!isValidServiceId(serviceId)) {
            System.out.println("Invalid Service ID format. Please enter again (format: SVRO-xxxx, SVVL-xxxx, SVHO-xxxx): ");
            serviceId = scanner.nextLine();
        }

        Booking newBooking = new Booking(bookingId, bookingDate, startDate, endDate, customerId, serviceId);
        bookingService.addNewBooking(newBooking);
        System.out.println("Booking added successfully!");
    }

    private boolean isValidBookingId(String bookingId) {
        return bookingId.matches("BK\\d{1,4}");
    }

    private boolean isValidCustomerId(String customerId) {
        return customerId.matches("CUS-\\d{4}");
    }

    private boolean isValidServiceId(String serviceId) {
        return serviceId.matches("SVRO-\\d{4}|SVVL-\\d{4}|SVHO-\\d{4}");
    }

    public void displayFullContracts() {
        ContractRepositoryImpl contractRepo = new ContractRepositoryImpl();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Contract contract : contractRepo.getContracts()) {

            String formattedContractDate = contract.getContractDate().format(dateFormatter);

            System.out.printf("Contract ID: %s, Booking ID: %s, Customer ID: %s, Deposit Amount: %.2f, Total Payment: %.2f, Contract Date: %s%n",
                    contract.getContractId(),
                    contract.getBookingId(),
                    contract.getCustomerId(),
                    contract.getDepositAmount(),
                    contract.getTotalPayment(),
                    formattedContractDate);
        }
    }

    public void editContract() {
        ContractRepositoryImpl contractRepo = new ContractRepositoryImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter contract ID to edit: ");
        String contractId = scanner.nextLine();

        for (Contract contract : contractRepo.getContracts()) {
            if (contract.getContractId().equals(contractId)) {
                System.out.println("Editing contract: " + contract);
                System.out.println("1. Update Deposit Amount");
                System.out.println("2. Update Total Payment");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Enter new deposit amount: ");
                        double newDepositAmount = scanner.nextDouble();
                        contract.setDepositAmount(newDepositAmount);
                        break;
                    case 2:
                        System.out.print("Enter new total payment: ");
                        double newTotalPayment = scanner.nextDouble();
                        contract.setTotalPayment(newTotalPayment);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }

                contractRepo.saveContracts();
                System.out.println("Contract updated successfully.");
                return;
            }
        }

        System.out.println("Contract ID not found.");
    }
}
