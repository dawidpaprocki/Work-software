package crud.repository;

import crud.model.BuySellConnection;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractCloseRepository extends JpaRepository<BuySellConnection, Long> {
    Optional<BuySellConnection> findByContractsOpenBuy(ContractsOpenBuy contractsOpenBuy);
    Optional<BuySellConnection> findByContractsOpenSell(ContractsOpenSell contractsOpenSell);
}
