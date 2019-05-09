package crud.services;

import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;
import crud.repository.ContractCloseRepository;
import crud.model.ContractsClose;
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
    public void addUpdate(ContractsClose contractsClose) {
        contractCloseRepository.save(contractsClose);

    }

    @Override
    public void deleteContract(Long id) {
        Optional<ContractsClose> foundContract = contractCloseRepository.findById(id);
        foundContract.ifPresent(contractCloseRepository::delete);
    }

    @Override
    public Optional<ContractsClose> findById(Long id) {
        return contractCloseRepository.findById(id);
    }

    @Override
    public Optional<ContractsClose> findByContractBuy(ContractsOpenBuy contractsOpenBuy) {
        return contractCloseRepository.findByContractsOpenBuy(contractsOpenBuy);
    }

    @Override
    public Optional<ContractsClose> findByContractSell(ContractsOpenSell contractsOpenSell) {
        return contractCloseRepository.findByContractsOpenSell(contractsOpenSell);
    }



    @Override
    public List<ContractsClose> findAll() {
        return contractCloseRepository.findAll();
    }

}
