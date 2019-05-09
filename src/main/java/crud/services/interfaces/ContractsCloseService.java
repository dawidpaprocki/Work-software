package crud.services.interfaces;

import crud.model.ContractsClose;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;

import java.util.List;
import java.util.Optional;

public interface ContractsCloseService {
    void addUpdate(ContractsClose contractsClose);
    void deleteContract(Long id);
    Optional<ContractsClose> findById(Long id);
    Optional<ContractsClose> findByContractBuy(ContractsOpenBuy contractsOpenBuy);
    Optional<ContractsClose> findByContractSell(ContractsOpenSell contractsOpenSell);
    List<ContractsClose> findAll();



}
