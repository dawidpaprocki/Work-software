package fxControllers.pirmary;

import crud.model.*;
import crud.services.interfaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;
import tools.PropertiesReader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PrimaryController {


    @FXML
    private TextField deliveryAmount;

    @FXML
    private ComboBox customer;

    @FXML
    private Button deliveryAdd;

    @FXML

    private TextField deliveryPlate;

    @FXML
    private ComboBox nrContractBuy;

    @FXML
    private ComboBox nrContractSell;

    @FXML
    private Label materialName;

    @FXML
    private DatePicker datePickerChoice;

    @FXML
    private Label dataPickError;

    private int typeOfTransaction;

    ObservableList<ContractsOpenBuy> choiceContractListBuy = FXCollections.observableArrayList();
    ObservableList<ContractsOpenSell> choiceContractListSell = FXCollections.observableArrayList();
    ObservableList customersList = FXCollections.observableArrayList();

    private CustomerService customerService;
    private AllTruckService allTruckService;
    private MaterialService materialService;
    private ContractsOpenService<ContractsOpenSell> contractsOpenSellService;
    private ContractsOpenService<ContractsOpenBuy> contractsOpenBuyService;
    private ContractsCloseService contractsCloseService;
    private ContractsOpenAbstract contractsOpenAbstract;
    private ContractsClose contractsClose;
    private Material materialChosen;
    private PropertiesReader propertiesFile;

    ContractsOpenBuy contractsOpenBuy;
    ContractsOpenSell contractsOpenSell;

    public PrimaryController(CustomerService customerService, AllTruckService allTruckService, MaterialService materialService,
                             ContractsOpenService<ContractsOpenSell> contractsOpenSell, ContractsOpenService<ContractsOpenBuy> contractsOpenBuy,
                             ContractsCloseService contractsCloseService, PropertiesReader propertiesFile) {
        this.customerService = customerService;
        this.allTruckService = allTruckService;
        this.materialService = materialService;
        this.contractsOpenSellService = contractsOpenSell;
        this.contractsOpenBuyService = contractsOpenBuy;
        this.contractsCloseService = contractsCloseService;
        this.propertiesFile = propertiesFile;
    }

    public void initialize() {
        customersList.setAll(customerService.selectList());
        customer.setItems(customersList);
        customer.setItems(customersList);
    }

    public void addButton() {
        String datePickerString = String.valueOf(datePickerChoice.getValue());
        String deliveryAmountText = deliveryAmount.getText();
        Customer chosenReceiver = (Customer) customer.getValue();
        if (deliveryPlate.getText().length() > 0) {
            if (deliveryAmount.getText().length() > 0) {
                if (((datePickerString.length()) > 0) && (!datePickerString.equals("Wybierz Datę"))) {
                    try {
                        Integer.parseInt(deliveryAmountText);
                        AllTruck addedTruck = AllTruck.builder()
                                .date(datePickerString)
                                .material(materialChosen)
                                .truckNumber(deliveryPlate.getText())
                                .amount(Integer.parseInt(deliveryAmountText))
                                .finalAmount(0)
                                .transportOrder("Do uzupełnienia")
                                .documentName("-")
                                .color("white")
                                .customer(chosenReceiver)
                                .build();
                        if (typeOfTransaction == 0) {
                            addedTruck.setContractsOpenBuy(contractsOpenBuy);
                            contractsOpenBuyService.addOrUpdate(contractsOpenBuy);
                            contractsCloseService.findByContractBuy(contractsOpenBuy)
                                    .orElse(new ContractsClose()).setContractsOpenBuy(contractsOpenBuy);
                            updateTruckAmountCompleted(contractsOpenBuy);
                        } else {
                            addedTruck.setContractsOpenSell(contractsOpenSell);
                            contractsOpenSellService.addOrUpdate(contractsOpenSell);
                            contractsCloseService.findByContractSell(contractsOpenSell)
                                    .orElse(new ContractsClose()).setContractsOpenSell(contractsOpenSell);
                            updateTruckAmountCompleted(contractsOpenSell);
                        }
                        allTruckService.addOrUpdate(addedTruck);
                        deliveryPlate.clear();
                        deliveryAmount.clear();
                        materialName.setText("");
                        dataPickError.setText("");
                        customer.setValue(null);
                        nrContractBuy.setValue(null);
                        nrContractSell.setValue(null);
                        datePickerChoice.setValue(null);
                        datePickerChoice.setPromptText("Wybierz date");
                    } catch (Exception e) {
                        deliveryAmount.setText("Tylko liczby");
                        e.printStackTrace();
                    }
                } else {
                    dataPickError.setText("Wymagana data");
                }
            } else {
                deliveryAmount.setText("Wpisz ilość");
            }
        } else {
            deliveryPlate.setText("Wpisz numer rejestracyjny");
        }
    }

    private void updateTruckAmountCompleted(ContractsOpenAbstract chosenContract) {
        chosenContract.setNrTruck(chosenContract.getNrTruck() + 1);
        if (chosenContract.getNrTruckContract() == chosenContract.getNrTruck()) {
            chosenContract.setOpenClose(1);
        }
    }

    public void materialPrepareBuy() {
        typeOfTransaction = 0;
        contractsOpenBuy = (ContractsOpenBuy) nrContractBuy.getValue();
        materialChosen = contractsOpenBuy.getMaterial();
        materialName.setText(contractsOpenBuy.getMaterial().toString());
    }

    public void materialPrepareSell() {
        typeOfTransaction = 1;
        contractsOpenSell = (ContractsOpenSell) nrContractSell.getValue();
        materialChosen = contractsOpenSell.getMaterial();
        materialName.setText(contractsOpenSell.getMaterial().toString());
    }


    public void selectComboBoxList() {
        if(Optional.ofNullable(customer.getValue()).isPresent()  ||
                !(customer.getValue().toString().equals(propertiesFile.getPropertiesFile().getProperty("choiceInformation")))){
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
