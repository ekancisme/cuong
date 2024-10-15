package ManageResortRequirement.controllers;

import ManageResortRequirement.model.Customer;
import ManageResortRequirement.services.CustomerService;
import ManageResortRequirement.services.CustomerServiceImpl;
import java.util.Scanner;

public class CustomerManagementMenu {
    private CustomerService customerService = new CustomerServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Display list customers");
            System.out.println("2. Add new customer");
            System.out.println("3. Edit customer");
            System.out.println("4. Return to main menu");
            System.out.print("Please select a function: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        displayListCustomers();
                        break;
                    case 2:
                        addNewCustomer();
                        break;
                    case 3:
                        editCustomer();
                        break;
                    case 4:
                        isRunning = false;
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

    private void displayListCustomers() {
        customerService.displayCustomers();
    }

    private void addNewCustomer() {
        try {
            String customerId = customerService.getValidCustomerId();
            String name = customerService.getValidName();
            String dateOfBirth = customerService.getValidDateOfBirth();
            String gender = customerService.getValidGender();
            String cmnd = customerService.getValidCMND();
            String phone = customerService.getValidPhone();
            String email = customerService.getValidEmail();
            String customerType = customerService.getValidCustomerType();

            Customer newCustomer = new Customer(customerId, name, dateOfBirth, gender, cmnd, phone, email, customerType);
            customerService.addCustomer(newCustomer);
            System.out.println("Customer added successfully.");
        } catch (Exception e) {
            System.out.println("Failed to add customer: " + e.getMessage());
        }
    }

    private void editCustomer() {
        try {
            System.out.print("Enter customer ID to edit: ");
            String customerId = scanner.nextLine();
            Customer customer = customerService.getCustomerById(customerId);
            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            boolean isUpdating = true;
            while (isUpdating) {
                System.out.println("1. Update name");
                System.out.println("2. Update date of birth");
                System.out.println("3. Update gender");
                System.out.println("4. Update CMND");
                System.out.println("5. Update phone number");
                System.out.println("6. Update email");
                System.out.println("7. Update customer type");
                System.out.println("8. Return to previous menu");
                System.out.print("Select the field to update: ");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            customer.setName(customerService.getValidName());
                            break;
                        case 2:
                            customer.setDateOfBirth(customerService.getValidDateOfBirth());
                            break;
                        case 3:
                            customer.setGender(customerService.getValidGender());
                            break;
                        case 4:
                            customer.setCmnd(customerService.getValidCMND());
                            break;
                        case 5:
                            customer.setPhone(customerService.getValidPhone());
                            break;
                        case 6:
                            customer.setEmail(customerService.getValidEmail());
                            break;
                        case 7:
                            customer.setCustomerType(customerService.getValidCustomerType());
                            break;
                        case 8:
                            isUpdating = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine();
                }
            }

            customerService.updateCustomer(customer);
            System.out.println("Customer updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update customer: " + e.getMessage());
        }
    }
}
