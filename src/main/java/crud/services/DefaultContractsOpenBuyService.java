package crud.services;

import crud.repository.ContractOpenBuyRepository;
import crud.model.ContractsOpenBuy;
import crud.services.interfaces.ContractsOpenService;
import crud.services.interfaces.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultContractsOpenBuyService implements ContractsOpenService<ContractsOpenBuy> {

    private ContractOpenBuyRepository contractOpenBuyRepository;
    private MaterialService materialService;

    public DefaultContractsOpenBuyService(ContractOpenBuyRepository contractOpenBuyRepository, MaterialService materialService) {
        this.contractOpenBuyRepository = contractOpenBuyRepository;
        this.materialService = materialService;
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

    @Override
    public String getMaterialName(Long contractId) {
//        materialService.findById(findById(contractId).getMaterialName())
        return "";
    }

}
