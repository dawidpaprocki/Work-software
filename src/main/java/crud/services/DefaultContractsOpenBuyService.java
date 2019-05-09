package crud.services;

import crud.model.ContractsOpenBuy;
import crud.repository.ContractOpenBuyRepository;
import crud.services.interfaces.ContractsOpenService;
import org.springframework.stereotype.Service;
import tools.PropertiesReader;

import java.util.List;

@Service
public class DefaultContractsOpenBuyService implements ContractsOpenService<ContractsOpenBuy> {

    private ContractOpenBuyRepository contractOpenBuyRepository;
    private PropertiesReader propertiesReader;


    public DefaultContractsOpenBuyService(ContractOpenBuyRepository contractOpenBuyRepository, PropertiesReader propertiesReader) {
        this.contractOpenBuyRepository = contractOpenBuyRepository;
        this.propertiesReader = propertiesReader;
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
    public void remove(ContractsOpenBuy ContractOpen) {
       contractOpenBuyRepository.delete(ContractOpen);
    }

    @Override
    public ContractsOpenBuy findById(Long id) {
        return contractOpenBuyRepository.findById(id).get();
    }

    @Override
    public ContractsOpenBuy findByContractNumber(String name) {
        return contractOpenBuyRepository.findByContractName(name).orElse(
                ContractsOpenBuy.builder()
                        .contractName(propertiesReader.getPropertiesFile()
                                .getProperty("lackOfOpenContract"))
                        .build());
    }


}
