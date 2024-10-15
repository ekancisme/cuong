package ManageResortRequirement.controllers;

import java.util.Scanner;

public class FuramaController {
    private EmployeeManagementMenu employeeManagementMenu = new EmployeeManagementMenu();
    private CustomerManagementMenu customerManagementMenu = new CustomerManagementMenu();
    private FacilityManagementMenu facilityManagementMenu = new FacilityManagementMenu();
    private BookingController bookingController = new BookingController();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new FuramaController().displayMainMenu();
    }

    public void displayMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Employee Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Facility Management");
            System.out.println("4. Booking Management");
            System.out.println("5. Promotion Management");
            System.out.println("6. Exit");
            System.out.print("Please select a function: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        employeeManagementMenu.displayMenu();
                        break;
                    case 2:
                        customerManagementMenu.displayMenu();
                        break;
                    case 3:
                        facilityManagementMenu.displayMenu();
                        break;
                    case 4:
                        bookingController.displayMenu();
                        break;
                    case 5:
                        // Promotion management
                        break;
                    case 6:
                        isRunning = false;
                        System.out.println("Exiting system...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }
}
