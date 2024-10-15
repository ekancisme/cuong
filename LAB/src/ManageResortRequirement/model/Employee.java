package ManageResortRequirement.model;

public class Employee extends Person {
    private String employeeId;
    private double salary;

    // Updated constructor with the correct number of parameters
    public Employee(String employeeId, String name, String dateOfBirth, String gender, String cmnd, String phone, String email, String position, double salary) {
        super(employeeId, name, dateOfBirth, gender, cmnd, phone, email, position); // Pass employeeId as ID
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toCsv() {
        return employeeId + "," + getName() + "," + getDateOfBirth() + "," + getGender() + "," +
                getCmnd() + "," + getPhone() + "," + getEmail() + "," + getPosition() + "," + salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + getName() + '\'' +
                ", dateOfBirth='" + getDateOfBirth() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", cmnd='" + getCmnd() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", position='" + getPosition() + '\'' +
                ", salary=" + salary +
                '}';
    }
}
