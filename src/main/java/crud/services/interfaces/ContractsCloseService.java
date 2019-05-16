package crud.services.interfaces;

import crud.model.BuySellConnection;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;

import java.util.List;
import java.util.Optional;

public interface ContractsCloseService {
    void addUpdate(BuySellConnection buySellConnection);
    void deleteContract(Long id);
    Optional<BuySellConnection> findById(Long id);
    Optional<BuySellConnection> findByContractBuy(ContractsOpenBuy contractsOpenBuy);
    Optional<BuySellConnection> findByContractSell(ContractsOpenSell contractsOpenSell);
    List<BuySellConnection> findAll();



}
