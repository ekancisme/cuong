package ManageResortRequirement.repository;

import ManageResortRequirement.model.Customer;
import java.util.List;

public interface CustomerRepository {
    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    void updateCustomers(List<Customer> customers);
    void add(Customer customer);
}
