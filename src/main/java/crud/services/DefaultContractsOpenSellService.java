package crud.services;

import crud.repository.ContractOpenSellRepository;
import crud.model.ContractsOpenSell;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultContractsOpenSellService implements ContractsOpenService<ContractsOpenSell> {

    private ContractOpenSellRepository contractOpenSellRepository;

    public DefaultContractsOpenSellService(ContractOpenSellRepository contractOpenSellRepository) {
        this.contractOpenSellRepository = contractOpenSellRepository;
    }

    @Override
    public void addOrUpdate(ContractsOpenSell contractsOpenSell) {
        contractOpenSellRepository.save(contractsOpenSell);
    }

    @Override
    public List<ContractsOpenSell> selectList() {
        return contractOpenSellRepository.findAll();
    }


    @Override
    public void remove(Long id) {
        Optional<ContractsOpenSell> byId = contractOpenSellRepository.findById(id);
        byId.ifPresent(contractsOpenSell -> contractOpenSellRepository.delete(contractsOpenSell));
    }

    @Override
    public ContractsOpenSell findById(Long id) {
        return contractOpenSellRepository.findById(id).get();
    }

    @Override
    public ContractsOpenSell findByName(String name) {
        return contractOpenSellRepository.findByContractName(name).get();
    }
}
