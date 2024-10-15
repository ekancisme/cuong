package ManageResortRequirement.services;

import ManageResortRequirement.model.Customer;

public interface CustomerService {
    void addCustomer(Customer customer);
    void editCustomer(String customerId, Customer updatedCustomer);
    void displayCustomers();

    String getValidCustomerId();
    String getValidName();
    String getValidDateOfBirth();
    String getValidGender();
    String getValidCMND();
    String getValidPhone();
    String getValidEmail();
    String getValidCustomerType();
    Customer getCustomerById(String customerId);
    void updateCustomer(Customer customer);


}

