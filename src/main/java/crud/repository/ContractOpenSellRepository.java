package crud.repository;

import crud.model.ContractsOpenSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractOpenSellRepository extends JpaRepository<ContractsOpenSell, Long> {
    Optional<ContractsOpenSell> findByContractName(String contractName);
}
