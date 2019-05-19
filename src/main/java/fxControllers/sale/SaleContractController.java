package fxControllers.sale;

import annotations.ChoiceBoxNoEmpty;
import annotations.TextFieldNoEmpty;
import crud.model.ContractsOpenSell;
import crud.model.Customer;
import crud.model.Material;
import crud.services.interfaces.ContractsOpenService;
import crud.services.interfaces.CustomerService;
import crud.services.interfaces.MaterialService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import fxControllers.validation.ValidatorGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class SaleContractController {

    /**
     * <h1>The SaleContractController Class.</h1>
     * <p>
     * Controller for JavaFX panel responsible for adding Sale contract to Data Base.
     * <p>
     * choiceContractSale - Choice Box where we choose name of company to make contract.
     * nrSaleContract - Text Field for set number of contract.
     * amountContractSale - Text Field for set amount of sold material.
     * choiceMaterialSaleContract - Choice Box for select material.
     * truckContractSale - Text Field for set amount of sold truck.
     * materialList - temporary list made for fill choiceMaterialSaleContract.
     */
    @FXML
    @ChoiceBoxNoEmpty(message = "Wybierz użytkownika")
    public ChoiceBox choiceCustomerNameSale;

    @FXML
    @TextFieldNoEmpty(message = "Podaj nazwę kontraktu")
    public TextField nrSaleContract;

    @FXML
    @TextFieldNoEmpty(message = "Podaj ilość ton")
    public TextField amountContractSale;

    @FXML
    @TextFieldNoEmpty(message = "Podaj ilość aut")
    public TextField truckContractSale;

    @FXML
    @ChoiceBoxNoEmpty(message = "Wybierz rodzaj materiału")
    public ChoiceBox choiceMaterialSaleContract;

    @Autowired
    ValidatorGUI validateObject;
    private ObservableList materialList = FXCollections.observableArrayList();
    private ObservableList customersList = FXCollections.observableArrayList();
    private ContractsOpenService<ContractsOpenSell> contractsOpenService;
    private MaterialService materialService;
    private CustomerService customerService;

    public SaleContractController(ContractsOpenService<ContractsOpenSell> contractsOpenService, MaterialService materialService, CustomerService customerService) {
        this.contractsOpenService = contractsOpenService;
        this.materialService = materialService;
        this.customerService = customerService;
    }

    public void initialize() {
        customersList.setAll(customerService.selectList());
        choiceCustomerNameSale.setItems(customersList);
        materialList.setAll(materialService.selectList());
        choiceMaterialSaleContract.setItems(materialList);
    }

    /**
     * Method responsible for insert full details of contract to Data Base.
     */
    public void addContractButton() {
        if (validateObject.validateObject(this) &&
                (Optional.ofNullable(choiceCustomerNameSale).isPresent() &&
                        Optional.ofNullable(choiceMaterialSaleContract).isPresent())) {

            ContractsOpenSell contractsOpenSell = ContractsOpenSell.builder()
                    .customer((Customer) choiceCustomerNameSale.getValue())
                    .contractName(nrSaleContract.getText())
                    .material((Material) choiceMaterialSaleContract.getValue())
                    .nrTruck(Integer.parseInt(truckContractSale.getText()))
                    .nrTruckContract(0)
                    .amount(Integer.parseInt(amountContractSale.getText()))
                    .contractName(nrSaleContract.getText())
                    .build();
            contractsOpenService.addOrUpdate(contractsOpenSell);
            nrSaleContract.clear();
            amountContractSale.clear();
            truckContractSale.clear();
            choiceMaterialSaleContract.setValue(null);
            choiceCustomerNameSale.setValue(null);
        }
    }
}
