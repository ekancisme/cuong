package ManageResortRequirement.repository;

import ManageResortRequirement.model.Contract;

import java.util.Set;

public interface ContractRepository {
    Set<Contract> getContracts(); // Lấy danh sách hợp đồng
    void loadContracts(); // Tải hợp đồng từ file
    void saveContracts(); // Lưu hợp đồng vào file
}
