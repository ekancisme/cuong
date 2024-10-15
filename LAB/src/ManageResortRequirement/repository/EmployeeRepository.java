package ManageResortRequirement.repository;

import ManageResortRequirement.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    void updateEmployee(List<Employee> employees);
}
