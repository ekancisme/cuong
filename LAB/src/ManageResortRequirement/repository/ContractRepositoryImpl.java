package ManageResortRequirement.repository;

import ManageResortRequirement.model.Contract;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

public class ContractRepositoryImpl implements ContractRepository {
    private static final String FILE_PATH = "src/ManageResortRequirement/data/contract.csv";
    private Set<Contract> contractSet;

    public ContractRepositoryImpl() {
        contractSet = new LinkedHashSet<>();
        loadContracts();
    }

    @Override
    public Set<Contract> getContracts() {
        return contractSet;
    }

    @Override
    public void loadContracts() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) continue;

                String contractId = data[0].trim();
                String bookingId = data[1].trim();
                String customerId = data[2].trim();
                double depositAmount = Double.parseDouble(data[3].trim());
                double totalPayment = Double.parseDouble(data[4].trim());
                LocalDate contractDate = LocalDate.parse(data[5].trim(), formatter);

                Contract contract = new Contract(contractId, bookingId, customerId, depositAmount, totalPayment, contractDate);
                contractSet.add(contract);
            }
        } catch (IOException e) {
            System.err.println("Error reading contracts: " + e.getMessage());
        }
    }

    @Override
    public void saveContracts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contract contract : contractSet) {
                String formattedContractDate = contract.getContractDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                writer.write(String.format("%s,%s,%s,%.2f,%.2f,%s",
                        contract.getContractId(),
                        contract.getBookingId(),
                        contract.getCustomerId(),
                        contract.getDepositAmount(),
                        contract.getTotalPayment(),
                        formattedContractDate));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing contracts: " + e.getMessage());
        }
    }
}
