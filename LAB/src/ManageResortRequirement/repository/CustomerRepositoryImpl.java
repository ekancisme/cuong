package ManageResortRequirement.repository;

import ManageResortRequirement.model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String FILE_PATH = "src\\ManageResortRequirement\\data\\customer.csv";

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 8) {
                    Customer customer = new Customer(
                            fields[0], fields[1], fields[2], fields[3],
                            fields[4], fields[5], fields[6], fields[7]
                    );
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
                return;
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(customer.toCSVFormat());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void updateCustomers(List<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Customer customer : customers) {
                bw.write(customer.toCSVFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(Customer customer) {
        saveCustomer(customer);
    }
}
