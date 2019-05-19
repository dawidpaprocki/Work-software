package fxControllers.primary;

import crud.model.ContractsOpenAbstract;
import crud.model.ContractsOpenBuy;
import crud.model.Customer;
import crud.model.Material;
import crud.services.interfaces.ContractsOpenService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import fxControllers.validation.ValidatorGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.NullCheckerJavaFX;
import tools.PropertiesReader;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContractListProvider {
    @Autowired
    private PropertiesReader propertiesFile;
    @Autowired
    private ValidatorGUI validatorGUI;
    @Autowired
    NullCheckerJavaFX nullChecker;

    ObservableList getContractsList(ComboBox comboBoxWithInformation,
                                    ContractsOpenService contractsOpenService) {
        ObservableList choiceContractList = FXCollections.observableArrayList();
        if (nullChecker.nullChecker(comboBoxWithInformation)) {
            Customer chosenCustomer = (Customer) comboBoxWithInformation.getValue();
            List<ContractsOpenAbstract> contractsOpen = contractsOpenService.selectList();
            contractsOpen = contractsOpen.stream()
                    .filter(e -> e.getCustomer().equals(chosenCustomer))
                    .collect(Collectors.toList());
            choiceContractList.setAll(contractsOpen);
            return choiceContractList;
        }
        return choiceContractList;
    }

    Material materialPrepareBuy(ComboBox comboBoxWithInformation, ContractsOpenAbstract contractsOpen) {
        if (nullChecker.nullChecker(comboBoxWithInformation)) {
            contractsOpen = (ContractsOpenBuy) comboBoxWithInformation.getValue();
            return contractsOpen.getMaterial();
        }
        return Material.builder().name(propertiesFile.getPropertiesFile().getProperty("choiceInformation")).build();
    }

}
