package fxControllers.pirmary;

import crud.controller.controllers.*;
import crud.model.GenericDao;
import crud.model.GenericDaoImpl;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    private Label materialId;

    @FXML
    private DatePicker datePickerChoice;

    @FXML
    private Label dataPickError;

    private int materialPrepareType;

    ObservableList choiceContractListBuy = FXCollections.observableArrayList();
    ObservableList choiceContractListSell = FXCollections.observableArrayList();
    ObservableList customersList = FXCollections.observableArrayList();

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManagerCustomer = sessionFactory.createEntityManager();
    private final EntityManager entityManagerAllView = sessionFactory.createEntityManager();
    private final EntityManager entityManagerContractBuy = sessionFactory.createEntityManager();
    private final EntityManager entityManagerContractSell = sessionFactory.createEntityManager();
    private final EntityManager entityManagerMAterial = sessionFactory.createEntityManager();
    private final EntityManager entityManagerContractClose = sessionFactory.createEntityManager();

    private GenericDaoImpl genericDaoAllView = new GenericDaoImpl(entityManagerAllView, AllView.class);
    private GenericDaoImpl genericDaoCustomer = new GenericDaoImpl(entityManagerCustomer, Customer.class);
    private GenericDaoImpl genericDaoContractBuy = new GenericDaoImpl(entityManagerContractBuy, ContractsOpenBuy.class);
    private GenericDaoImpl genericDaoContractSell = new GenericDaoImpl(entityManagerContractSell, ContractsOpenSell.class);
    private GenericDaoImpl genericDaoMaterial = new GenericDaoImpl(entityManagerMAterial, Material.class);
    private GenericDaoImpl GenericDaoContractclose = new GenericDaoImpl(entityManagerContractClose, ContractsClose.class);

    DaoAllViewController daoAllViewController = DaoAllViewController.builder()
            .dao(genericDaoAllView)
            .build();

    DaoCustomerController daoCustomerController = DaoCustomerController.builder()
            .dao(genericDaoCustomer)
            .build();
    DaoContractsOpenBuyController daoContractBuyController = DaoContractsOpenBuyController.builder().dao(genericDaoContractBuy).build();
    DaoContractsOpenSellController daoContractSellController = DaoContractsOpenSellController.builder().dao(genericDaoContractSell).build();
    DaoMaterialController daoMaterialController =  DaoMaterialController.builder().dao(genericDaoMaterial).build();
    DaoContractsCloseController daoContractsCloseController = DaoContractsCloseController.builder().dao(GenericDaoContractclose).build();


    public void initialize() {

        customersList.setAll(daoCustomerController.selectList());
        choiceCustomerNameBox.setItems(customersList);


    }

    public void addButton() {

        String dataPickerString = String.valueOf(datePickerChoice.getValue());
        String deliveryAmountText = deliveryAmount.getText();
        if (deliveryPlate.getText().length() > 0) {
            if (deliveryTo.getText().length() > 0) {
                if (deliveryAmount.getText().length() > 0) {
                    if (((dataPickerString.length()) > 0) && (!dataPickerString.equals("Wybierz Datę"))) {


                        try {
                            Integer.parseInt(deliveryAmountText);

                            if (materialPrepareType == 0) {

                                daoAllViewController = DaoAllViewController.builder()
                                        .data(dataPickerString)
                                        .material(materialId.getText())
                                        .truck(deliveryPlate.getText())
                                        .amount(Integer.parseInt(deliveryAmountText))
                                        .finalAmount(0)
                                        .froms(choiceCustomerNameBox.getValue().toString())
                                        .tos(deliveryTo.getText())
                                        .truckNr("-")
                                        .transportOrder("=")
                                        .vk("to change")
                                        .ek(nrContractBuy.getValue().toString())
                                        .amsDoc("-")
                                        .color("white")
                                        .dao(genericDaoMaterial)
                                        .build();

                                daoAllViewController.add();

                                deliveryType(ContractsOpenBuy.class, genericDaoContractBuy);

                            } else {

                                daoAllViewController = DaoAllViewController.builder()
                                        .data(dataPickerString)
                                        .material(materialId.getText())
                                        .truck(deliveryPlate.getText())
                                        .amount(Integer.parseInt(deliveryAmountText))
                                        .finalAmount(0)
                                        .froms(choiceCustomerNameBox.getValue().toString())
                                        .tos(deliveryTo.getText())
                                        .truckNr("-")
                                        .transportOrder("=")
                                        .vk("to change")
                                        .ek(nrContractSell.getValue().toString())
                                        .amsDoc("-")
                                        .color("white")
                                        .dao(genericDaoMaterial)
                                        .build();

                                daoAllViewController.add();

                                deliveryType(ContractsOpenSell.class, genericDaoContractSell);

                            }


                            deliveryPlate.clear();
                            deliveryAmount.clear();
                            deliveryTo.clear();
                            materialId.setText("");
                            dataPickError.setText("");

                            choiceCustomerNameBox.setValue(null);
                            nrContractBuy.setValue(null);
                            datePickerChoice.setValue(null);
                            datePickerChoice.setPromptText("Wybierz date");


                        } catch (Exception e) {
                            deliveryAmount.setText("Tylko liczby");
                            e.printStackTrace();
                            System.out.println("nie integer");
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


    public void deliveryType(Class table, GenericDao dao) {
        //need to make refactor!!! - sick.
        ContractsOpenBuy contractsOpenBuy;
        ContractsOpenSell contractsOpenSell;

        if (table.getSimpleName().equals("ContractsOpenBuy")) {

            contractsOpenBuy = daoContractBuyController.find("ContractName", nrContractBuy.getValue().toString()).get(0);

            daoContractBuyController.updateRecord("nrTruckContract",
                    String.valueOf(contractsOpenBuy.getNrTruckContract() + 1),
                    contractsOpenBuy.getId());

            contractsOpenBuy = daoContractBuyController.find("ContractName", nrContractBuy.getValue().toString()).get(0);

            if (contractsOpenBuy.getNrTruckContract() == contractsOpenBuy.getNrTruck()) {
                daoContractBuyController.updateRecord("OpenClose",
                        "1",
                        contractsOpenBuy.getId());

                daoContractsCloseController.CheckStatusTransfer(contractsOpenBuy.getId(), table, dao);
            }


        } else if (table.getClass().getSimpleName().equals("ContractsOpenSell")) {
            contractsOpenSell = daoContractSellController.find("ContractName", nrContractSell.getValue().toString()).get(0);

            daoContractSellController.updateRecord("nrTruckContract",
                    String.valueOf(contractsOpenSell.getNrTruckContract() + 1),
                    contractsOpenSell.getId());

            contractsOpenSell = daoContractSellController.find("ContractName", nrContractSell.getValue().toString()).get(0);


            if (contractsOpenSell.getNrTruckContract() == contractsOpenSell.getNrTruck()) {
                daoContractSellController.updateRecord("OpenClose",
                        "1",
                        contractsOpenSell.getId());

                daoContractsCloseController.CheckStatusTransfer(contractsOpenSell.getId(), table, dao);

            }

        }

    }

    public void materialPrepareBuy() {

        String NrContractBuy = Optional.ofNullable(nrContractBuy.getValue()).orElse("Empty").toString();


        if (!(NrContractBuy.equals("Wybierz") || NrContractBuy.equals("Empty"))) {
            materialPrepareType = 0;
            String contractNumber = nrContractBuy
                    .getValue()
                    .toString();
            List<ContractsOpenBuy> contractName = daoContractBuyController.find("contractName", contractNumber);
            String contractId = contractName.get(0).getIdName();

            materialId.setText(contractId);

        }
    }


    public void materialPrepareSell() {
        materialPrepareType = 1;
        String contractNumber = nrContractSell
                .getValue()
                .toString();
        List<ContractsOpenSell> contractName = daoContractSellController.find("contractName",contractNumber);

        String materialName = contractName.get(0).getIdName();

        materialId.setText(materialName);

    }



    public void selectComboBoxList() {

        String customerIdValue = Optional.ofNullable(choiceCustomerNameBox.getValue()).orElse("Empty").toString();
        if (!(customerIdValue.equals("Wybierz") ||  customerIdValue.equals("Empty"))) {

            int customerId = daoCustomerController
                    .findByName(choiceCustomerNameBox
                            .getValue()
                            .toString())
                    .get(0).getId();

            listOfChoiceContract(customerId);

        }

    }

    public void listOfChoiceContract(int customerId) {

        List<ContractsOpenBuy>  contractsOpenBuy = daoContractBuyController.selectList();

        choiceContractListBuy.setAll(contractsOpenBuy.stream()
                .filter(e -> e.getIdCustomer() == customerId)
                .map(ContractsOpenBuy::getContractName)
                .collect(Collectors.toList()));

        List<ContractsOpenSell>    contractsOpenSell = daoContractSellController.selectList();

        choiceContractListSell.setAll(contractsOpenSell.stream()
                .filter(e -> e.getIdCustomer() == customerId)
                .map(ContractsOpenSell::getContractName)
                .collect(Collectors.toList()));

        nrContractBuy.setItems(choiceContractListBuy);
        nrContractSell.setItems(choiceContractListSell);

    }

}
