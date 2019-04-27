package fxControllers.pirmary;

import crud.services.*;
import crud.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PrimaryController {


    @FXML
    private TextField deliveryAmount;

    @FXML
    private TextField deliveryTo;

    @FXML
    private Button deliveryAdd;

    @FXML
    private TextField deliveryPlate;

    @FXML
    private ComboBox nrContractBuy;

    @FXML
    private ComboBox nrContractSell;


    @FXML
    private ComboBox choiceCustomerNameBox;

    @FXML
    private Label materialName;

    @FXML
    private DatePicker datePickerChoice;

    @FXML
    private Label dataPickError;

    private int materialPrepareType;

    ObservableList choiceContractListBuy = FXCollections.observableArrayList();
    ObservableList choiceContractListSell = FXCollections.observableArrayList();
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

    public PrimaryController(CustomerService customerService, AllTruckService allTruckService, MaterialService materialService,
                             ContractsOpenService<ContractsOpenSell> contractsOpenSell, ContractsOpenService<ContractsOpenBuy> contractsOpenBuy,
                             ContractsCloseService contractsCloseService) {
        this.customerService = customerService;
        this.allTruckService = allTruckService;
        this.materialService = materialService;
        this.contractsOpenSellService = contractsOpenSell;
        this.contractsOpenBuyService = contractsOpenBuy;
        this.contractsCloseService = contractsCloseService;

    }

    public void initialize() {
        customersList.setAll(customerService.selectList());
        choiceCustomerNameBox.setItems(customersList);
    }

    public void addButton() {
        String datePickerString = String.valueOf(datePickerChoice.getValue());
        String deliveryAmountText = deliveryAmount.getText();
        if (deliveryPlate.getText().length() > 0) {
            if (deliveryTo.getText().length() > 0) {
                if (deliveryAmount.getText().length() > 0) {
                    if (((datePickerString.length()) > 0) && (!datePickerString.equals("Wybierz Datę"))) {
                        try {
                            Integer.parseInt(deliveryAmountText);
                            if (materialPrepareType == 0) {
                                AllTruck addedTruck = AllTruck.builder()
                                        .date(datePickerString)
                                        .material(materialChosen)
                                        .truckNumber(deliveryPlate.getText())
                                        .amount(Integer.parseInt(deliveryAmountText))
                                        .finalAmount(0)
                                        .seller(choiceCustomerNameBox.getValue().toString())
                                        .buyer(deliveryTo.getText())
                                        .truckNr("-")
                                        .transportOrder("=")
                                        .salesContractNumber("to change")
                                        .purchaseContractNumber(nrContractBuy.getValue().toString())
                                        .documentName("-")
                                        .color("white")
                                        .build();
                                allTruckService.addOrUpdate(addedTruck);
                                updateOpenCloseStatus(ContractsOpenBuy.class, nrContractBuy.getValue().toString());
                            } else {
                                AllTruck addedTruck = AllTruck.builder()
                                        .date(datePickerString)
                                        .material(materialChosen)
                                        .truckNumber(deliveryPlate.getText())
                                        .amount(Integer.parseInt(deliveryAmountText))
                                        .finalAmount(0)
                                        .seller(choiceCustomerNameBox.getValue().toString())
                                        .buyer(deliveryTo.getText())
                                        .truckNr("-")
                                        .transportOrder("=")
                                        .salesContractNumber("to change")
                                        .purchaseContractNumber(nrContractSell.getValue().toString())
                                        .documentName("-")
                                        .color("white")
                                        .build();
                                allTruckService.addOrUpdate(addedTruck);
                                updateOpenCloseStatus(ContractsOpenSell.class, nrContractSell.getValue().toString());
                            }
                            deliveryPlate.clear();
                            deliveryAmount.clear();
                            deliveryTo.clear();
                            materialName.setText("");
                            dataPickError.setText("");
                            choiceCustomerNameBox.setValue(null);
                            nrContractBuy.setValue(null);
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
                deliveryTo.setText("Wpisz Odbiorce");
            }
        } else {
            deliveryPlate.setText("Wpisz numer rejestracyjny");
        }


    }


    public void updateOpenCloseStatus(Class clazz, String contractNumberReceivedFromFXML) {

        String nameOfEntity = clazz.getSimpleName();
        contractsOpenAbstract = contractsOpenBuyService.findByName(contractNumberReceivedFromFXML);
        contractsOpenAbstract.setNrTruck(contractsOpenAbstract.getNrTruck() + 1);
        if (contractsOpenAbstract.getNrTruckContract() == contractsOpenAbstract.getNrTruck()) {
            contractsOpenAbstract.setOpenClose(0);
        }
        if (nameOfEntity.equals("ContractsOpenBuy")) {
            contractsOpenBuyService.addOrUpdate((ContractsOpenBuy) contractsOpenAbstract);
            contractsClose.setContractsOpenBuy((ContractsOpenBuy) contractsOpenAbstract);
        } else {
            contractsOpenSellService.addOrUpdate((ContractsOpenSell) contractsOpenAbstract);
            contractsClose.setContractsOpenSell((ContractsOpenSell) contractsOpenAbstract);
        }

    }

    public void materialPrepareBuy() {
        String NrContractBuy = Optional.ofNullable(nrContractBuy.getValue()).orElse("Empty").toString();
        if (!(NrContractBuy.equals("Wybierz") || NrContractBuy.equals("Empty"))) {
            materialPrepareType = 0;
            String contractNumber = nrContractBuy
                    .getValue()
                    .toString();
            ContractsOpenBuy contractName = contractsOpenBuyService.findByName(contractNumber);
            materialChosen = materialService.findById(contractName.getMaterial().getId());
            materialName.setText(materialChosen.toString());
        }
    }


    public void materialPrepareSell() {
        materialPrepareType = 1;
        String contractNumber = nrContractSell
                .getValue()
                .toString();
        ContractsOpenSell contractName = contractsOpenSellService.findByName(contractNumber);
        materialChosen = materialService.findById(contractName.getMaterial().getId());
        materialName.setText(materialChosen.toString());
    }


    public void selectComboBoxList() {

        String customerIdValue = Optional.ofNullable(choiceCustomerNameBox.getValue()).orElse("Empty").toString();
        if (!(customerIdValue.equals("Wybierz") || customerIdValue.equals("Empty"))) {

            Long customerId = customerService
                    .findByName(choiceCustomerNameBox
                            .getValue()
                            .toString())
                    .get(0).getId();
            listOfChoiceContract(customerId);
        }
    }

    public void listOfChoiceContract(Long customerId) {
        List<ContractsOpenBuy> contractsOpenBuy = contractsOpenBuyService.selectList();
        choiceContractListBuy.setAll(contractsOpenBuy.stream()
                .filter(e -> e.getIdCustomer().equals(customerId))
                .map(ContractsOpenBuy::getContractName)
                .collect(Collectors.toList()));
        List<ContractsOpenSell> contractsOpenSell = contractsOpenSellService.selectList();
        choiceContractListSell.setAll(contractsOpenSell.stream()
                .filter(e -> e.getIdCustomer().equals(customerId))
                .map(ContractsOpenSell::getContractName)
                .collect(Collectors.toList()));
        nrContractBuy.setItems(choiceContractListBuy);
        nrContractSell.setItems(choiceContractListSell);
    }
}
