package crud.repository;

import crud.model.ContractsClose;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractCloseRepository extends JpaRepository<ContractsClose, Long> {
    Optional<ContractsClose> findByContractsOpenBuy(ContractsOpenBuy contractsOpenBuy);
    Optional<ContractsClose> findByContractsOpenSell(ContractsOpenSell contractsOpenSell);
}
