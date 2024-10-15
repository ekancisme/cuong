package ManageResortRequirement.controllers;

import ManageResortRequirement.model.Employee;
import ManageResortRequirement.services.EmployeeService;
import ManageResortRequirement.services.EmployeeServiceImpl;
import java.util.Scanner;

public class EmployeeManagementMenu {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Display list employees");
            System.out.println("2. Add new employee");
            System.out.println("3. Edit employee");
            System.out.println("4. Return to main menu");
            System.out.print("Please select a function: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        displayListEmployees();
                        break;
                    case 2:
                        addNewEmployee();
                        break;
                    case 3:
                        editEmployee();
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

    private void displayListEmployees() {
        employeeService.displayListEmployees();
    }

    private void addNewEmployee() {
        try {
            String employeeId = employeeService.getValidEmployeeId();
            String name = employeeService.getValidName();
            String dateOfBirth = employeeService.getValidDateOfBirth();
            String gender = employeeService.getValidGender();
            String cmnd = employeeService.getValidCMND();
            String phone = employeeService.getValidPhone();
            String email = employeeService.getValidEmail();
            String position = employeeService.getValidPosition();
            double salary = employeeService.getValidSalary();

            Employee newEmployee = new Employee(employeeId, name, dateOfBirth, gender, cmnd, phone, email, position, salary);
            employeeService.addNewEmployee(newEmployee);
            System.out.println("Employee added successfully.");
        } catch (Exception e) {
            System.out.println("Failed to add employee: " + e.getMessage());
        }
    }

    private void editEmployee() {
        try {
            System.out.print("Enter employee ID to edit: ");
            String employeeId = scanner.nextLine();
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee == null) {
                System.out.println("Employee not found.");
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
                System.out.println("7. Update position");
                System.out.println("8. Update salary");
                System.out.println("9. Return to previous menu");
                System.out.print("Select the field to update: ");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            employee.setName(employeeService.getValidName());
                            break;
                        case 2:
                            employee.setDateOfBirth(employeeService.getValidDateOfBirth());
                            break;
                        case 3:
                            employee.setGender(employeeService.getValidGender());
                            break;
                        case 4:
                            employee.setCmnd(employeeService.getValidCMND());
                            break;
                        case 5:
                            employee.setPhone(employeeService.getValidPhone());
                            break;
                        case 6:
                            employee.setEmail(employeeService.getValidEmail());
                            break;
                        case 7:
                            employee.setPosition(employeeService.getValidPosition());
                            break;
                        case 8:
                            employee.setSalary(employeeService.getValidSalary());
                            break;
                        case 9:
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

            employeeService.updateEmployee(employee);
            System.out.println("Employee updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update employee: " + e.getMessage());
        }
    }
}
