package fxControllers.sale;

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
import org.springframework.stereotype.Controller;

@Controller
public class SaleContractController {

    /**
     *  <h1>The SaleContractController Class.</h1>
     *
     *  Controller for JavaFX panel responsible for adding sell contract to Data Base.
     *
     *  choiceContractSell - Choice Box where we choice name of company to make contract.
     *  nrSellContract - Text Field for set number of contract.
     *  amountContractSell - Text Field for set amount of sold material.
     *  choiceMaterialSellContract - Choice Box for select material.
     *  truckContractSell - Text Field for set amount of sold truck.
     *  materialList - temporary list made for fill  choiceMaterialSellContract.
     *
     */
    @FXML
    private ChoiceBox choiceCustomer;

    @FXML
    private TextField nrSellContract;

    @FXML
    private TextField amountContractSell;

    @FXML
    private TextField truckContractSell;

    @FXML
    private ChoiceBox choiceMaterialSellContract;


    private ContractsOpenService<ContractsOpenSell> contractsOpenService;
    private MaterialService materialService;
    private CustomerService customerService;

    public SaleContractController(ContractsOpenService<ContractsOpenSell> contractsOpenService, MaterialService materialService, CustomerService customerService) {
        this.contractsOpenService = contractsOpenService;
        this.materialService = materialService;
        this.customerService = customerService;
    }
 private ObservableList materialList = FXCollections.observableArrayList();
    private ObservableList customersList = FXCollections.observableArrayList();

    public void initialize() {

        customersList.setAll(customerService.selectList());
        choiceCustomer.setItems(customersList);

        materialList.setAll( materialService.selectList());

        choiceMaterialSellContract.setItems(materialList);
    }


    /**
     * Method responsible for insert full details of contract to Data Base.
     */
    public void addContractButton() {

        ContractsOpenSell contractsOpenSell=  ContractsOpenSell.builder()
                .customer((Customer)choiceCustomer.getValue())
                .contractName(nrSellContract.getText())
                .material((Material)choiceMaterialSellContract.getValue())
                .nrTruck(Integer.parseInt(truckContractSell.getText()))
                .nrTruckContract(0)
                .amount(Integer.parseInt(amountContractSell.getText()))
                .contractName(nrSellContract.getText())
                .build();
        contractsOpenService.addOrUpdate(contractsOpenSell);
        nrSellContract.clear();
        amountContractSell.clear();
        truckContractSell.clear();
    }


}
