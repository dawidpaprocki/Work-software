package crud.services;

import crud.repository.ContractOpenBuyRepository;
import crud.model.ContractsOpenBuy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultContractsOpenBuyService implements ContractsOpenService<ContractsOpenBuy> {

    private ContractOpenBuyRepository contractOpenBuyRepository;

    public DefaultContractsOpenBuyService(ContractOpenBuyRepository contractOpenBuyRepository) {
        this.contractOpenBuyRepository = contractOpenBuyRepository;
    }

    @Override
    public void addOrUpdate(ContractsOpenBuy contractsOpenBuy) {
        contractOpenBuyRepository.save(contractsOpenBuy);
    }

    @Override
    public List<ContractsOpenBuy> selectList() {
        return contractOpenBuyRepository.findAll();
    }


    @Override
    public void remove(Long id) {
        Optional<ContractsOpenBuy> byId = contractOpenBuyRepository.findById(id);
        byId.ifPresent(contractsOpenSell -> contractOpenBuyRepository.delete(contractsOpenSell));
    }

    @Override
    public ContractsOpenBuy findById(Long id) {
        return contractOpenBuyRepository.findById(id).get();
    }

    @Override
    public ContractsOpenBuy findByName(String name) {
        return contractOpenBuyRepository.findByContractName(name).get();
    }

}
