package fxControllers.purchase;

import crud.model.Material;
import crud.services.interfaces.ContractsOpenService;
import crud.services.interfaces.CustomerService;
import crud.services.interfaces.MaterialService;
import crud.model.ContractsOpenBuy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;
@Controller
public class PurchaseContractController {

    @FXML
    private ChoiceBox choiceCustomerNameBuy;

    @FXML
    private TextField nrBuyContract;

    @FXML
    private TextField amountContractBuy;

    @FXML
    private Button addBuy;

    @FXML
    private ChoiceBox choiceMaterialBuyContract;

    @FXML
    private TextField truckContractBuy;

    @FXML
    private ChoiceBox choiceCustomerNameSell;

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

        customersList.setAll(contractsOpenService.selectList());
        choiceCustomerNameBuy.setItems(customersList);
        choiceCustomerNameSell.setItems(customersList);
        materialList.setAll(materialService.selectList());
        choiceMaterialBuyContract.setItems(materialList);

    }

    public void addContractButton() {
        String companyName = choiceCustomerNameBuy.getValue().toString();
        Material chosenMaterial =(Material) choiceMaterialBuyContract.getValue();
        String nameOfCompanyBuyToSell = choiceCustomerNameSell.getValue().toString();
        Long idBuyer = contractsOpenService.findByContractNumber(companyName).getId();
        Long idSeller = customerService.findByName(nameOfCompanyBuyToSell).getId();
         ContractsOpenBuy contractsOpenBuy = ContractsOpenBuy.builder()
                .idSell(idSeller)
                .idCustomer(idBuyer)
                .customerName(companyName)
                .material(chosenMaterial)
                .nrTruck(Integer.parseInt(truckContractBuy.getText()))
                .nrTruckContract(0)
                .amount(Integer.parseInt(amountContractBuy.getText()))
                .contractName(nrBuyContract.getText())
                .build();
         contractsOpenService.addOrUpdate(contractsOpenBuy);
        choiceMaterialBuyContract.setValue(null);
        choiceCustomerNameSell.setValue(null);
        choiceCustomerNameBuy.setValue(null);
        nrBuyContract.clear();
        amountContractBuy.clear();
        truckContractBuy.clear();


    }


}
