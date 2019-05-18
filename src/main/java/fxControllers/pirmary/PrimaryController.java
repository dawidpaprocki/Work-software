package fxControllers.pirmary;

import annotations.ComboBoxNoEmpty;
import annotations.DatePickerNoEmpty;
import annotations.TextFieldNoEmpty;
import crud.model.*;
import crud.services.interfaces.AllTruckService;
import crud.services.interfaces.ContractsCloseService;
import crud.services.interfaces.ContractsOpenService;
import crud.services.interfaces.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.ValidatorGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tools.PropertiesReader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private ObservableList<ContractsOpenBuy> choiceContractListBuy = FXCollections.observableArrayList();
    private ObservableList<ContractsOpenSell> choiceContractListSell = FXCollections.observableArrayList();
    private ObservableList customersList = FXCollections.observableArrayList();

    private CustomerService customerService;
    private AllTruckService allTruckService;
    private ContractsOpenService<ContractsOpenSell> contractsOpenSellService;
    private ContractsOpenService<ContractsOpenBuy> contractsOpenBuyService;
    private ContractsCloseService contractsCloseService;
    private Material materialChosen;
    private PropertiesReader propertiesFile;


    @Autowired
    ValidatorGUI validateObject;

    String choiceInformation;

    ContractsOpenBuy contractsOpenBuy;
    ContractsOpenSell contractsOpenSell;

    public PrimaryController(CustomerService customerService, AllTruckService allTruckService,
                             ContractsOpenService<ContractsOpenSell> contractsOpenSell, ContractsOpenService<ContractsOpenBuy> contractsOpenBuy,
                             ContractsCloseService contractsCloseService, PropertiesReader propertiesFile) {
        this.customerService = customerService;
        this.allTruckService = allTruckService;
        this.contractsOpenSellService = contractsOpenSell;
        this.contractsOpenBuyService = contractsOpenBuy;
        this.contractsCloseService = contractsCloseService;
        this.propertiesFile = propertiesFile;
    }

    public void initialize() {
        customersList.setAll(customerService.selectList());
        customer.setItems(customersList);
        customer.setItems(customersList);
        choiceInformation = propertiesFile.getPropertiesFile().getProperty("choiceInformation");
    }

    AllTruck newTruckToSave;

    public void addButton() {
        try {
            if (validateObject.validateObject(this) && (
                    Optional.ofNullable(nrContractBuy.getValue()).isPresent() &&
                            Optional.ofNullable(nrContractSell.getValue()).isPresent()
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
            } else {
                nrContractSell.setPromptText(choiceInformation);
                nrContractBuy.setPromptText(choiceInformation);
            }
        } catch (NumberFormatException e) {
            deliveryAmount.setText("Tylko liczby");
            e.printStackTrace();
        }

    }

    private void setTruckContract() {
        if (typeOfTransaction == 0) {
            newTruckToSave.setContractsOpenBuy(contractsOpenBuy);
            updateTruckAmountCompleted(contractsOpenBuy);
            contractsOpenBuyService.addOrUpdate(contractsOpenBuy);
        } else {
            newTruckToSave.setContractsOpenSell(contractsOpenSell);
            updateTruckAmountCompleted(contractsOpenSell);
            contractsOpenSellService.addOrUpdate(contractsOpenSell);
        }
    }

    private void updateTruckAmountCompleted(ContractsOpenAbstract chosenContract) {
        chosenContract.setNrTruck(chosenContract.getNrTruck() + 1);
        if (chosenContract.getNrTruckContract() == chosenContract.getNrTruck()) {
            chosenContract.setOpenClose(1);
        }
    }

    public void materialPrepareBuy() {
        if (Optional.ofNullable(nrContractBuy.getValue()).isPresent()) {
            typeOfTransaction = 0;
            contractsOpenBuy = (ContractsOpenBuy) nrContractBuy.getValue();
            materialChosen = contractsOpenBuy.getMaterial();
            materialName.setText(contractsOpenBuy.getMaterial().toString());
        }
    }

    public void materialPrepareSell() {
        if (Optional.ofNullable(nrContractSell.getValue()).isPresent()) {
            typeOfTransaction = 1;
            contractsOpenSell = (ContractsOpenSell) nrContractSell.getValue();
            materialChosen = contractsOpenSell.getMaterial();
            materialName.setText(contractsOpenSell.getMaterial().toString());
        }
    }


    public void selectComboBoxList() {
        if (Optional.ofNullable(customer.getValue()).isPresent() &&
                !(customer.getValue().toString().equals(propertiesFile.getPropertiesFile().getProperty("choiceInformation")))) {
            Long customerId = customerService
                    .findByName(customer.getValue().toString())
                    .getId();
            listOfContractsBaseOnCustomer(customerId);
        }
    }

    private void listOfContractsBaseOnCustomer(Long customerId) {
        List<ContractsOpenBuy> contractsOpenBuy = contractsOpenBuyService.selectList();
        choiceContractListBuy.setAll(contractsOpenBuy.stream()
                .filter(e -> e.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList()));
        nrContractBuy.setItems(choiceContractListBuy);

        List<ContractsOpenSell> contractsOpenSell = contractsOpenSellService.selectList();
        choiceContractListSell.setAll(contractsOpenSell.stream()
                .filter(e -> e.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList()));
        nrContractSell.setItems(choiceContractListSell);
    }
}
