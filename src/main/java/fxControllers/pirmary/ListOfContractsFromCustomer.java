package fxControllers.pirmary;

import crud.model.ContractsOpenAbstract;
import crud.model.ContractsOpenBuy;
import crud.model.Customer;
import crud.services.interfaces.ContractsOpenService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.PropertiesReader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ListOfContractsFromCustomer {
    Customer chosenCustomer;
    @Autowired
    private PropertiesReader propertiesFile;
    private ObservableList choiceContractList = FXCollections.observableArrayList();
    public ObservableList getContractsList(ComboBox comboBoxWithInformation,
                          ContractsOpenService contractsOpenService) {

        if (nullChecker(comboBoxWithInformation)) {
            List<ContractsOpenAbstract> contractsOpenBuy = contractsOpenService.selectList();
            contractsOpenBuy = contractsOpenBuy.stream()
                    .filter(e -> e.getCustomer().equals(chosenCustomer))
                    .collect(Collectors.toList());
            choiceContractList.setAll(contractsOpenBuy);
            return  choiceContractList;
        }
        return choiceContractList;
    }

    private boolean nullChecker(ComboBox customer) {
        if (Optional.ofNullable(customer.getValue()).isPresent() &&
                !(customer.getValue().toString().equals(propertiesFile.getPropertiesFile().getProperty("choiceInformation")))) {
            chosenCustomer = (Customer) customer.getValue();
            return true;
        }
        return false;
    }
}
