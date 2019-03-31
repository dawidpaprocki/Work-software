package crud.services;

import crud.repository.ContractCloseRepository;
import entity.ContractsClose;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultContractsCloseService implements ContractsCloseService {

    private final ContractCloseRepository contractCloseRepository;

    public DefaultContractsCloseService(ContractCloseRepository contractCloseRepository) {
        this.contractCloseRepository = contractCloseRepository;
    }

    private Boolean checkIfPresent(ContractsClose contractsClose) {

        if (contractsClose.getContractName() != null) {
            Optional<ContractsClose> foundCar = contractCloseRepository.findByContractName(contractsClose.getContractName());
            return !foundCar.isPresent();
        } else {
            return true;
        }
    }


    @Override
    public void addUpdateContract(ContractsClose contractsClose) {
        if (checkIfPresent(contractsClose)) {
            contractCloseRepository.save(contractsClose);
        }
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
    public List<ContractsClose> findAll() {
        return contractCloseRepository.findAll();
    }
}
