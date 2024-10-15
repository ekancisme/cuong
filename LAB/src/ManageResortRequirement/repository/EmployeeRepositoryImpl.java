package ManageResortRequirement.repository;

import ManageResortRequirement.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final String EMPLOYEE_FILE = "src\\ManageResortRequirement\\data\\employee.csv";

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] employeeData = line.split(",");
                Employee employee = new Employee(employeeData[0], employeeData[1], employeeData[2],
                        employeeData[3], employeeData[4], employeeData[5],
                        employeeData[6], employeeData[7], Double.parseDouble(employeeData[8]));
                employeeList.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void saveEmployee(Employee employee) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE_FILE, true))) {
                bw.write(employee.toCsv());
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void updateEmployee(List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            for (Employee employee : employees) {
                bw.write(employee.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
