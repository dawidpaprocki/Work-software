package fxControllers.primary;

import annotations.ComboBoxNoEmpty;
import annotations.DatePickerNoEmpty;
import annotations.TextFieldNoEmpty;
import crud.model.*;
import crud.services.interfaces.AllTruckService;
import crud.services.interfaces.ContractsOpenService;
import crud.services.interfaces.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import fxControllers.validation.ValidatorGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tools.NullCheckerJavaFX;
import tools.PropertiesReader;

@Controller
public class PrimaryController {


    @FXML
    @TextFieldNoEmpty(message = "Podaj ilość materiału")
    public TextField deliveryAmount;

    @FXML
    @ComboBoxNoEmpty(message = "Wybierz klienta")
    public ComboBox customer;

    @FXML
    public Button deliveryAdd;

    @FXML
    @TextFieldNoEmpty(message = "Podaj numer rejestracyjny")
    public TextField deliveryPlate;

    @FXML
    public ComboBox nrContractBuy;

    @FXML
    public ComboBox nrContractSell;

    @FXML
    public Label materialName;

    @FXML
    @DatePickerNoEmpty(message = "Wybiez datę")
    public DatePicker datePickerChoice;

    @FXML
    public Label dataPickError;

    private int typeOfTransaction;

    private ObservableList<ContractsOpenSell> choiceContractListSell = FXCollections.observableArrayList();
    private ObservableList customersList = FXCollections.observableArrayList();

    private CustomerService customerService;
    private AllTruckService allTruckService;
    private ContractsOpenService<ContractsOpenSell> contractsOpenSellService;
    private ContractsOpenService<ContractsOpenBuy> contractsOpenBuyService;
    private Material materialChosen;
    private PropertiesReader propertiesFile;
    private ContractListProvider contractListProvider;
    private TruckAmountUpdate truckAmountUpdate;
    private NullCheckerJavaFX nullCheckerJavaFX;

    @Autowired
    ValidatorGUI validateObject;
    private ContractsOpenBuy contractsOpenBuy;
    private ContractsOpenSell contractsOpenSell;

    public PrimaryController(CustomerService customerService, AllTruckService allTruckService,
                             ContractsOpenService<ContractsOpenSell> contractsOpenSell, ContractsOpenService<ContractsOpenBuy> contractsOpenBuy,
                             PropertiesReader propertiesFile, ContractListProvider contractListProvider, TruckAmountUpdate truckAmountUpdate) {
        this.customerService = customerService;
        this.allTruckService = allTruckService;
        this.contractsOpenSellService = contractsOpenSell;
        this.contractsOpenBuyService = contractsOpenBuy;
        this.propertiesFile = propertiesFile;
        this.contractListProvider = contractListProvider;
        this.truckAmountUpdate = truckAmountUpdate;
    }

    public void initialize() {
        customersList.setAll(customerService.selectList());
        customer.setItems(customersList);
        customer.setItems(customersList);
    }

    AllTruck newTruckToSave;

    public void addButton() {
        try {
            if (validateObject.validateObject(this) && (
                    nullCheckerJavaFX.nullChecker(nrContractBuy) && nullCheckerJavaFX.nullChecker(nrContractSell)
            )) {
                newTruckToSave = AllTruck.builder()
                        .date(String.valueOf(datePickerChoice.getValue()))
                        .material(materialChosen)
                        .truckNumber(deliveryPlate.getText())
                        .amount(Integer.parseInt(deliveryAmount.getText()))
                        .finalAmount(0)
                        .transportOrder("Do uzupełnienia")
                        .documentName("-")
                        .color("white")
                        .customer((Customer) customer.getValue())
                        .build();
                setTruckContract();
                allTruckService.addOrUpdate(newTruckToSave);
                deliveryPlate.clear();
                deliveryAmount.clear();
                materialName.setText("");
                dataPickError.setText("");
                customer.setValue(null);
                nrContractBuy.setValue(null);
                nrContractSell.setValue(null);
                datePickerChoice.setValue(null);
            }
        } catch (NumberFormatException e) {
            deliveryAmount.setText("Tylko liczby");
            e.printStackTrace();
        }

    }

    private void setTruckContract() {
        truckAmountUpdate.updateTruckContract(typeOfTransaction, contractsOpenBuyService,
                contractsOpenSellService, contractsOpenBuy, contractsOpenSell, newTruckToSave);
    }

    public void materialPrepareBuy() {
        typeOfTransaction = 0;
        materialChosen = contractListProvider.materialPrepareBuy(nrContractBuy, contractsOpenBuy);
        materialName.setText(materialChosen.toString());
    }

    public void materialPrepareSell() {
        typeOfTransaction = 1;
        materialChosen = contractListProvider.materialPrepareBuy(nrContractSell, contractsOpenSell);
        materialName.setText(materialChosen.toString());
    }

    public void selectComboBoxList() {
        nrContractBuy.setItems(contractListProvider.getContractsList(customer, contractsOpenBuyService));
        nrContractSell.setItems(contractListProvider.getContractsList(customer, contractsOpenSellService));
    }

}
