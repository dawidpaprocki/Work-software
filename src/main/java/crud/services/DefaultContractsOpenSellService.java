package crud.services;

import crud.model.ContractsOpenSell;
import crud.repository.ContractOpenSellRepository;
import crud.services.interfaces.ContractsOpenService;
import org.springframework.stereotype.Service;
import tools.PropertiesReader;

import java.util.List;

@Service
public class DefaultContractsOpenSellService implements ContractsOpenService<ContractsOpenSell> {

    private ContractOpenSellRepository contractOpenSellRepository;

    private PropertiesReader propertiesReader;

    public DefaultContractsOpenSellService(ContractOpenSellRepository contractOpenSellRepository, PropertiesReader propertiesReader) {
        this.contractOpenSellRepository = contractOpenSellRepository;
        this.propertiesReader = propertiesReader;
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
    public void remove(ContractsOpenSell contractsOpenSell) {
        contractOpenSellRepository.delete(contractsOpenSell);
    }

    @Override
    public ContractsOpenSell findById(Long id) {
        return contractOpenSellRepository.findById(id).orElse((ContractsOpenSell)ContractsOpenSell.builder()
                .contractName(propertiesReader.getPropertiesFile().getProperty("lackOfOpenContract"))
                .build());
    }

    @Override
    public ContractsOpenSell findByContractNumber(String name) {
       return contractOpenSellRepository.findByContractName(name).orElse((ContractsOpenSell)
                ContractsOpenSell.builder()
                        .contractName(propertiesReader.getPropertiesFile().getProperty("lackOfOpenContract"))
                        .build());
    }
}
