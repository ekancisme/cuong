package ManageResortRequirement.services;

import ManageResortRequirement.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void addNewEmployee(Employee employee) throws Exception;
    Employee getEmployeeById(String employeeId);
    void updateEmployee(Employee employee) throws Exception;
    void displayListEmployees();
    String getValidEmployeeId();
    String getValidName();
    String getValidDateOfBirth();
    String getValidGender();
    String getValidCMND();
    String getValidPhone();
    String getValidEmail();
    String getValidPosition();
    double getValidSalary();
}
