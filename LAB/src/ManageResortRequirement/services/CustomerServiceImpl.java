package ManageResortRequirement.services;

import ManageResortRequirement.model.Customer;
import ManageResortRequirement.repository.CustomerRepository;
import ManageResortRequirement.repository.CustomerRepositoryImpl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addCustomer(Customer customer) {
        if (validateCustomer(customer)) {
            customerRepository.saveCustomer(customer);
        } else {
            System.out.println("Invalid customer data.");
        }
    }

    @Override
    public void editCustomer(String customerId, Customer updatedCustomer) {
        List<Customer> customers = customerRepository.getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                customers.set(customers.indexOf(customer), updatedCustomer);
                customerRepository.updateCustomers(customers);
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    @Override
    public void displayCustomers() {
        List<Customer> customers = customerRepository.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Override
    public String getValidCustomerId() {
        System.out.print("Enter customer ID (format KH-xxxx): ");
        String customerId;
        while (true) {
            customerId = scanner.nextLine();
            if (Pattern.matches("KH-\\d{4}", customerId)) {
                break;
            } else {
                System.out.print("Invalid ID. Please enter again: ");
            }
        }
        return customerId;
    }

    @Override
    public String getValidName() {
        System.out.print("Enter customer name: ");
        String name;
        while (true) {
            name = scanner.nextLine();
            if (Pattern.matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", name)) {
                break;
            } else {
                System.out.print("Invalid name format. Please enter again: ");
            }
        }
        return name;
    }

    @Override
    public String getValidDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();
        String dateOfBirth;
        while (true) {
            System.out.print("Enter date of birth (dd/MM/yyyy): ");
            dateOfBirth = scanner.nextLine();
            try {
                LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
                int age = Period.between(birthDate, currentDate).getYears();

                if (age < 18 || (age == 18 && (birthDate.plusYears(18).isAfter(currentDate)))) {
                    System.out.println("Customer must be at least 18 years old. Please enter a valid date of birth.");
                } else if (birthDate.isAfter(currentDate)) {
                    System.out.println("Invalid date of birth. Date of birth cannot be in the future.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date of birth format. Please use dd/MM/yyyy.");
            }
        }
        return dateOfBirth;
    }


    @Override
    public String getValidGender() {
        System.out.print("Enter gender (Male/Female): ");
        return scanner.nextLine();
    }

    @Override
    public String getValidCMND() {
        System.out.print("Enter ID card number (9 or 12 digits): ");
        String cmnd;
        while (true) {
            cmnd = scanner.nextLine();
            if (Pattern.matches("\\d{9}|\\d{12}", cmnd)) {
                break;
            } else {
                System.out.print("Invalid ID card format. Please enter again: ");
            }
        }
        return cmnd;
    }

    @Override
    public String getValidPhone() {
        System.out.print("Enter phone number (10 digits starting with 0): ");
        String phone;
        while (true) {
            phone = scanner.nextLine();
            if (Pattern.matches("0\\d{9}", phone)) {
                break;
            } else {
                System.out.print("Invalid phone number. Please enter again: ");
            }
        }
        return phone;
    }

    @Override
    public String getValidEmail() {
        String emailPattern = "^[\\w-\\.]+@gmail\\.com$";
        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            if (Pattern.matches(emailPattern, email)) {
                break;
            } else {
                System.out.println("Invalid email format. Email should end with '@gmail.com'. Please enter again.");
            }
        }
        return email;
    }


    @Override
    public String getValidCustomerType() {
        System.out.print("Enter customer type (Regular/VIP): ");
        return scanner.nextLine();
    }

    private boolean validateCustomer(Customer customer) {
        // Định dạng mã khách hàng: KH-YYYY
        String idPattern = "KH-\\d{4}";

        // Định dạng tên khách hàng: Viết hoa ký tự đầu của mỗi từ
        String namePattern = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";

        // Định dạng số điện thoại: Bắt đầu bằng 0 và đủ 10 số
        String phonePattern = "^0\\d{9}$";

        // Định dạng CMND: Phải là 9 hoặc 12 số
        String idCardPattern = "\\d{9}|\\d{12}";

        // Định dạng ngày sinh (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Kiểm tra mã khách hàng
        if (!Pattern.matches(idPattern, customer.getId())) {
            System.out.println("Invalid ID format. Format should be KH-xxxx.");
            return false;
        }

        // Kiểm tra tên khách hàng
        if (!Pattern.matches(namePattern, customer.getName())) {
            System.out.println("Invalid name format. Each word should start with a capital letter.");
            return false;
        }

        // Kiểm tra số điện thoại
        if (!Pattern.matches(phonePattern, customer.getPhone())) {
            System.out.println("Invalid phone number format. It should contain 10 digits and start with 0.");
            return false;
        }

        // Kiểm tra CMND
        if (!Pattern.matches(idCardPattern, customer.getCmnd())) {
            System.out.println("Invalid ID card number format. It should contain 9 or 12 digits.");
            return false;
        }

        // Kiểm tra ngày sinh và đảm bảo đủ 18 tuổi
        try {
            LocalDate birthDate = LocalDate.parse(customer.getDateOfBirth(), formatter);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate, currentDate).getYears();

            // Nếu chưa đủ 18 tuổi
            if (age < 18 || (age == 18 && (birthDate.plusYears(18).isAfter(currentDate)))) {
                System.out.println("Customer must be at least 18 years old.");
                return false;
            }

            // Kiểm tra ngày sinh không thể là tương lai
            if (birthDate.isAfter(currentDate)) {
                System.out.println("Invalid date of birth. Date of birth cannot be in the future.");
                return false;
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date of birth format. Please use dd/MM/yyyy.");
            return false;
        }


        return true;
    }
    @Override
    public Customer getCustomerById(String customerId) {
        List<Customer> customers = customerRepository.getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
    @Override
    public void updateCustomer(Customer customer) {
        List<Customer> customers = customerRepository.getAllCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customer.getId())) {
                customers.set(i, customer);
                customerRepository.updateCustomers(customers);
                return;
            }
        }
        System.out.println("Customer not found.");
    }


}

