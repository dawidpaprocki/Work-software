package crud.repository;

import crud.model.ContractsOpenBuy;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EntityScan
public interface ContractOpenBuyRepository extends JpaRepository<ContractsOpenBuy, Long> {
    Optional<ContractsOpenBuy> findByContractName(String contractName);
//    String hql = "UPDATE " + clazz.getSimpleName() + " SET " + query + "  = :newValue where id = :idRow ";
//    @Query("UPDATE contractsOpenBuy SET ")
//    void updateRecord();
}
