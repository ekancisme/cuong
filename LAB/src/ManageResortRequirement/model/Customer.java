package ManageResortRequirement.model;

public class Customer extends Person {
    private String customerType;

    public Customer(String id, String name, String dateOfBirth, String gender, String cmnd, String phone, String email, String customerType) {
        super(id, name, dateOfBirth, gender, cmnd, phone, email, "Customer");
        this.customerType = customerType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", dateOfBirth='" + getDateOfBirth() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", cmnd='" + getCmnd() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", customerType='" + customerType + '\'' +
                '}';
    }

    public String toCSVFormat() {
        return getId() + "," + getName() + "," + getDateOfBirth() + "," + getGender() + "," +
                getCmnd() + "," + getPhone() + "," + getEmail() + "," + customerType;
    }
}
