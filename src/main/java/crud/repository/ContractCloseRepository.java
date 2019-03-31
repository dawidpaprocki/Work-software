package crud.repository;

import entity.ContractsClose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractCloseRepository extends JpaRepository<ContractsClose, Long> {
    Optional<ContractsClose> findByContractName(String contractName);
}
