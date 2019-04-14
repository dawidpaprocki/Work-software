package crud.services;

import crud.repository.ContractOpenSellRepository;
import entity.ContractsOpenSell;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultDaoContactsOpenSellService implements DaoContactsOpenService {

    private ContractOpenSellRepository contractOpenSellRepository;

    public DefaultDaoContactsOpenSellService(ContractOpenSellRepository contractOpenSellRepository) {
        this.contractOpenSellRepository = contractOpenSellRepository;
    }

    @Override
    public void add(ContractsOpenSell contractsOpenSell) {
        contractOpenSellRepository.save(contractsOpenSell);
    }

    @Override
    public void update(Long idSellAfterChange, Long idCustomerAfterChange, String customerNameAfterChange, String materialNameAfterChange,
                       int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange, String contractNameAfterChange) {
        ContractsOpenSell contractsOpenSell =  ContractsOpenSell.builder()
                .idSell(idSellAfterChange)
                .idCustomer(idCustomerAfterChange)
                .customerName(customerNameAfterChange)
                .customerName(materialNameAfterChange)
                .nrTruck(nrTruckContractAfterChange)
                .materialName(materialNameAfterChange)
                .nrTruckContract(nrTruckAfterChange)
                .amount(amountAfterChange)
                .contractName(contractNameAfterChange)
                .build();
        contractOpenSellRepository.save(contractsOpenSell);
    }

    @Override
    public void remove(Long id) {
        Optional<ContractsOpenSell> byId = contractOpenSellRepository.findById(id);
        byId.ifPresent(contractsOpenSell -> contractOpenSellRepository.delete(contractsOpenSell));
    }

    @Override
    public Object findById(Long id) {
        return contractOpenSellRepository.findById(id);
    }
}
