package fxControllers.purchase;

import annotations.ChoiceBoxNoEmpty;
import annotations.TextFieldNoEmpty;
import crud.model.ContractsOpenBuy;
import crud.model.Customer;
import crud.model.Material;
import crud.services.interfaces.ContractsOpenService;
import crud.services.interfaces.CustomerService;
import crud.services.interfaces.MaterialService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import main.ValidatorGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PurchaseContractController {

    /**
     * <h1>The PurchaseContractController Class.</h1>
     * <p>
     * Controller for JavaFX panel responsible for adding Purchase contract to Data Base.
     * <p>
     * choiceContractPurchase - Choice Box where choose name of company to make contract.
     * nrPurchaseContract - Text Field for set number of contract.
     * amountContractPurchase - Text Field for set amount of bought material.
     * choiceMaterialPurchaseContract - Choice Box for choose material type.
     * truckContractPurchase - Text Field for set amount of bought truck.
     * materialList - temporary list made for fill  choiceMaterialPurchaseContract.
     */

    @FXML
    @ChoiceBoxNoEmpty(message = "Wybierz użytkownika")
    public ChoiceBox choiceCustomerNamePurchase;

    @FXML
    @TextFieldNoEmpty(message = "Podaj nazwę kontraktu")
    public TextField nrPurchaseContract;

    @FXML
    @TextFieldNoEmpty(message = "Podaj ilość ton")
    public TextField amountContractPurchase;

    @FXML
    @ChoiceBoxNoEmpty(message = "Wybierz rodzaj materiału")
    public ChoiceBox choiceMaterialPurchaseContract;

    @FXML
    @TextFieldNoEmpty(message = "Podaj ilość aut")
    public TextField truckContractPurchase;

    @FXML
    public Button addBuy;

    @Autowired
    ValidatorGUI validateObject;
    private ObservableList materialList = FXCollections.observableArrayList();
    private ObservableList customersList = FXCollections.observableArrayList();
    private ContractsOpenService<ContractsOpenBuy> contractsOpenService;
    private MaterialService materialService;
    private CustomerService customerService;

    public PurchaseContractController(ContractsOpenService<ContractsOpenBuy> contractsOpenService, MaterialService materialService, CustomerService customerService) {
        this.contractsOpenService = contractsOpenService;
        this.materialService = materialService;
        this.customerService = customerService;
    }


    public void initialize() {
        customersList.setAll(customerService.selectList());
        choiceCustomerNamePurchase.setItems(customersList);
        materialList.setAll(materialService.selectList());
        choiceMaterialPurchaseContract.setItems(materialList);
    }

    /**
     * Method responsible for insert full details of contract to Data Base.
     */
    public void addContractButton() {
        if (validateObject.validateObject(this) &&
                (Optional.ofNullable(choiceCustomerNamePurchase).isPresent() &&
                        Optional.ofNullable(choiceMaterialPurchaseContract).isPresent())) {

            ContractsOpenBuy contractsOpenBuy = ContractsOpenBuy.builder()
                    .customer((Customer) choiceCustomerNamePurchase.getValue())
                    .contractName(nrPurchaseContract.getText())
                    .material((Material) choiceMaterialPurchaseContract.getValue())
                    .nrTruck(Integer.parseInt(truckContractPurchase.getText()))
                    .nrTruckContract(0)
                    .amount(Integer.parseInt(amountContractPurchase.getText()))
                    .contractName(nrPurchaseContract.getText())
                    .build();
            contractsOpenService.addOrUpdate(contractsOpenBuy);
            choiceMaterialPurchaseContract.setValue(null);
            choiceCustomerNamePurchase.setValue(null);
            nrPurchaseContract.clear();
            amountContractPurchase.clear();
            truckContractPurchase.clear();
        }
    }
}
