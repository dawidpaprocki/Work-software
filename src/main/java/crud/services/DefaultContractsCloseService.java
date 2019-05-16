package crud.services;

import crud.model.BuySellConnection;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;
import crud.repository.ContractCloseRepository;
import crud.services.interfaces.ContractsCloseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultContractsCloseService implements ContractsCloseService {

    private final ContractCloseRepository contractCloseRepository;

    public DefaultContractsCloseService(ContractCloseRepository contractCloseRepository) {
        this.contractCloseRepository = contractCloseRepository;
    }

    @Override
    public void addUpdate(BuySellConnection buySellConnection) {
        contractCloseRepository.save(buySellConnection);

    }

    @Override
    public void deleteContract(Long id) {
        Optional<BuySellConnection> foundContract = contractCloseRepository.findById(id);
        foundContract.ifPresent(contractCloseRepository::delete);
    }

    @Override
    public Optional<BuySellConnection> findById(Long id) {
        return contractCloseRepository.findById(id);
    }

    @Override
    public Optional<BuySellConnection> findByContractBuy(ContractsOpenBuy contractsOpenBuy) {
        return contractCloseRepository.findByContractsOpenBuy(contractsOpenBuy);
    }

    @Override
    public Optional<BuySellConnection> findByContractSell(ContractsOpenSell contractsOpenSell) {
        return contractCloseRepository.findByContractsOpenSell(contractsOpenSell);
    }



    @Override
    public List<BuySellConnection> findAll() {
        return contractCloseRepository.findAll();
    }

}
