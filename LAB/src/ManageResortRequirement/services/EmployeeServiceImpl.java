package ManageResortRequirement.services;

import ManageResortRequirement.model.Employee;
import ManageResortRequirement.repository.EmployeeRepository;
import ManageResortRequirement.repository.EmployeeRepositoryImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Scanner;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private Scanner scanner = new Scanner(System.in);

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public void addNewEmployee(Employee employee) throws Exception {
        String employeeId = scanner.nextLine();
        String name = scanner.nextLine();
        String dateOfBirth = scanner.nextLine();
        String gender = scanner.nextLine();
        String cmnd = scanner.nextLine();
        String phone = scanner.nextLine();
        String email = scanner.nextLine();
        String position = scanner.nextLine();
        double salary = scanner.nextDouble();

        // Kiểm tra xem có trường nào bị bỏ trống không
        if (employeeId.isEmpty() || name.isEmpty() || dateOfBirth.isEmpty() ||
                gender.isEmpty() || cmnd.isEmpty() || phone.isEmpty() ||
                email.isEmpty() || position.isEmpty()) {
            System.out.println("Fields cannot be empty.");
            return; // Thoát nếu có trường rỗng
        }

        // Tạo nhân viên mới
        try {
            Employee newEmployee = new Employee(employeeId, name, dateOfBirth, gender, cmnd, phone, email, position, salary);
            employeeRepository.saveEmployee(newEmployee); // Gọi hàm lưu nhân viên
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add employee: " + e.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        return employeeRepository.getAllEmployees()
                .stream()
                .filter(emp -> emp.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateEmployee(Employee employee) throws Exception {
        if (!isValidEmployee(employee)) {
            throw new Exception("Invalid Employee Data");
        }
        List<Employee> employees = employeeRepository.getAllEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId().equals(employee.getEmployeeId())) {
                employees.set(i, employee);
                break;
            }
        }
        employeeRepository.updateEmployee(employees);
    }

    private boolean isValidEmployee(Employee employee) {
        if (isNullOrEmpty(employee.getEmployeeId()) ||
                isNullOrEmpty(employee.getName()) ||
                isNullOrEmpty(employee.getDateOfBirth()) ||
                isNullOrEmpty(employee.getCmnd()) ||
                isNullOrEmpty(employee.getPhone()) ||
                isNullOrEmpty(employee.getEmail())) {
            System.out.println("Fields cannot be empty.");
            return false;
        }

        // Validate ID format
        if (!Pattern.matches("NV-\\d{4}", employee.getEmployeeId())) {
            System.out.println("Invalid employee ID format. It must be NV-YYYY.");
            return false;
        }

        // Validate name format
        if (!isValidName(employee.getName())) {
            System.out.println("Invalid employee name format. Each word must start with an uppercase letter.");
            return false;
        }

        // Validate age
        if (!isOldEnough(employee.getDateOfBirth())) {
            System.out.println("Employee must be at least 18 years old.");
            return false;
        }

        // Validate CMND
        if (!Pattern.matches("\\d{9}|\\d{12}", employee.getCmnd())) {
            System.out.println("Invalid CMND format. It must be 9 or 12 digits.");
            return false;
        }

        // Validate phone number
        if (!Pattern.matches("0\\d{9}", employee.getPhone())) {
            System.out.println("Invalid phone number format. It must start with 0 and be 10 digits long.");
            return false;
        }

        if (employee.getSalary() <= 0) {
            System.out.println("Salary must be greater than 0.");
            return false;
        }

        return true;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isValidName(String name) {
        for (String word : name.split(" ")) {
            if (word.isEmpty() || !Character.isUpperCase(word.charAt(0))) {
                return false;
            }
        }
        return true;
    }

    private boolean isOldEnough(String dateOfBirth) {
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return LocalDate.now().minusYears(18).isAfter(birthDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return false;
        }
    }

    @Override
    public void displayListEmployees() {
        List<Employee> employees = getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("Employee List:");
        employees.forEach(System.out::println);
    }

    @Override
    public String getValidEmployeeId() {
        while (true) {
            System.out.print("Enter employee ID: ");
            String employeeId = scanner.nextLine();
            if (Pattern.matches("NV-\\d{4}", employeeId)) {
                return employeeId;
            } else {
                System.out.println("Invalid employee ID. Please try again.");
            }
        }
    }

    @Override
    public String getValidName() {
        while (true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (isValidName(name)) {
                return name;
            } else {
                System.out.println("Invalid name format. Each word must start with an uppercase letter.");
            }
        }
    }

    @Override
    public String getValidDateOfBirth() {
        while (true) {
            System.out.print("Enter date of birth (dd/MM/yyyy): ");
            String dateOfBirth = scanner.nextLine();
            if (isOldEnough(dateOfBirth)) {
                return dateOfBirth;
            } else {
                System.out.println("Invalid date of birth or the employee is not old enough.");
            }
        }
    }

    @Override
    public String getValidGender() {
        while (true) {
            System.out.print("Enter gender (Male/Female/Other): ");
            String gender = scanner.nextLine().trim();
            if (gender.equalsIgnoreCase("Male") ||
                    gender.equalsIgnoreCase("Female") ||
                    gender.equalsIgnoreCase("Other")) {
                return gender;
            } else {
                System.out.println("Invalid gender. Please enter Male, Female, or Other.");
            }
        }
    }

    @Override
    public String getValidCMND() {
        while (true) {
            System.out.print("Enter CMND (9 or 12 digits): ");
            String cmnd = scanner.nextLine();
            if (Pattern.matches("\\d{9}|\\d{12}", cmnd)) {
                return cmnd;
            } else {
                System.out.println("Invalid CMND format. It must be 9 or 12 digits.");
            }
        }
    }

    @Override
    public String getValidPhone() {
        while (true) {
            System.out.print("Enter phone number (10 digits starting with 0): ");
            String phone = scanner.nextLine();
            if (Pattern.matches("0\\d{9}", phone)) {
                return phone;
            } else {
                System.out.println("Invalid phone number format. It must start with 0 and be 10 digits long.");
            }
        }
    }

    @Override
    public String getValidEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            if (Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
                return email;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }
    }

    @Override
    public String getValidPosition() {
        while (true) {
            System.out.print("Enter position: ");
            String position = scanner.nextLine().trim();
            if (!isNullOrEmpty(position)) {
                return position;
            } else {
                System.out.println("Position cannot be empty. Please try again.");
            }
        }
    }

    @Override
    public double getValidSalary() {
        while (true) {
            try {
                System.out.print("Enter salary: ");
                double salary = Double.parseDouble(scanner.nextLine());
                if (salary > 0) {
                    return salary;
                } else {
                    System.out.println("Salary must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format. Please enter a valid number.");
            }
        }
    }

}