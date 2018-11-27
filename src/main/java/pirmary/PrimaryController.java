package pirmary;

import combo.DataOperationAll;
import combo.SelectListOfThings;
import combo.SelectOneThing;
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
    private ChoiceBox choiceContractBuy;

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
    private ChoiceBox choiceContractBuyToSell;

    @FXML
    private ComboBox choiceCustomerNameBox;

    @FXML
    private Label materialID;

    @FXML
    private DatePicker datePickerChoice;

    @FXML
    private Label dataPickError;


    private String contractName;

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
    DaoMaterialController daoMaterialController = new DaoMaterialController(genericDaoMaterial);
    DaoContractsCloseController daoContractsCloseController = DaoContractsCloseController.builder().dao(GenericDaoContractclose).build();

    public void initialize() {

        customersList.setAll(daoCustomerController.selectList());
        choiceCustomerNameBox.setItems(customersList);
//        new ComboCustomers(choiceCustomerNameBox, "Select Name From Customers", "Name", "ComboBox");


    }

    public void addButton() {
//        Object datePicker = datePickerChoice.getValue();
        String dataPickerString = String.valueOf(datePickerChoice.getValue());

        String deliveryAmountText = deliveryAmount.getText();
        System.out.println(deliveryAmountText + " ilość ton ??");
//        String deliveryToText = deliveryTo.getText();
//        String deliveryPlateText = deliveryPlate.getText();

        if (deliveryPlate.getText().length() > 0) {
            if (deliveryTo.getText().length() > 0) {
                if (deliveryAmount.getText().length() > 0) {
                    if (((dataPickerString.length()) > 0) && (!dataPickerString.equals("Wybierz Datę"))) {

//                        System.out.println("sprawdzam środek" + datapickerString.length());

//                        Object nameOfCompany = choiceCustomerNameBox.getValue();
//
//
//                        String MaterialToString = String.valueOf(materialID.getText());
//
//
//                        Object NameOfContract = nrContractBuy.getValue();
//                        String ContractToString = String.valueOf(nrContractBuy.getValue());

//                        Object NameOfContractSell = nrContractSell.getValue();
//                        String ContractSellToString = String.valueOf(NameOfContractSell);
//
//                        System.out.println(ContractSellToString + "sell kontrakt");
//
//                        if (ContractSellToString.length() > 4) { // sprawdzic czemu 4? co autor miał na myśli
//                            ContractSellToString = contractName;
//
//                        } else {
//                            contractName = "dopisz";
//
//                        }
//                        System.out.println(contractName);

                        try {
                            Integer.parseInt(deliveryAmountText);

                            if (type == 0) {
                                System.out.println("type 0");


                                daoAllViewController = DaoAllViewController.builder()
                                        .data(dataPickerString)
                                        .material(materialID.getText())
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


//                                int id = contractsOpenBuy.stream().filter(e -> e.equals(nrBuyContract.getText())).findFirst().get().getId();
//                                System.out.println(id);

                                deliveryType(ContractsOpenBuy.class, genericDaoContractBuy);
//                                daoContractBuyController.updateRecord("NrTruckContract", "(nrTruckContract + 1) ", id);


//                                new DataOperationAll("INSERT INTO All_View (data,material,truck,amount,finalAmount,froms,tos,truckNr,transportOrder,vk,ek,amsDoc,color) " +
//                                        "VALUES ('" + (String.valueOf(datePickerChoice.getValue())) + "','" + (String.valueOf(materialID.getText())) + "','" + (deliveryPlate.getText()) +
//                                        "','" + (deliveryAmountText) + "' , 0 ,'" + (String.valueOf(choiceCustomerNameBox.getValue())) + "' ,'" + (deliveryTo.getText()) +
//                                        "',1,'Do uzupełnienia!','Do uzupełnienia!','" + (String.valueOf(nrContractBuy.getValue())) + "','Do uzupełnienia!','white' );");


                            } else {
                                System.out.println("type 1");

                                deliveryType(ContractsOpenSell.class, genericDaoContractSell);
                                System.out.println("tu");
                                new DataOperationAll("INSERT INTO All_View (data,material,truck,amount,finalAmount,froms,tos,truckNr,transportOrder,vk,ek,amsDoc,color) " +
                                        "VALUES ('" + (String.valueOf(datePickerChoice.getValue())) + "','" + (String.valueOf(materialID.getText())) + "','" + (deliveryPlate.getText()) +
                                        "','" + (deliveryAmountText) + "' , 0 ,'" + (String.valueOf(choiceCustomerNameBox.getValue())) + "' ,'" + (deliveryTo.getText()) +
                                        "',1,'Do uzupełnienia!','" + (String.valueOf(nrContractSell.getValue())) + "','Do uzupełnienia!','Do uzupełnienia!','white' );");
                            }
//                            new DataOperationAll("INSERT INTO All_View (data,material,truck,amount,finalAmount,froms,tos,truckNr,Transport_Order,vk,ek,amsDoc,color) " +
//                                    "VALUES ('" + (String.valueOf(datePickerChoice.getValue())) + "','" + (String.valueOf(materialID.getText())) + "','" + (deliveryPlate.getText()) +
//                                    "','" + (deliveryAmountText) + "' , 'Do uzupełnienia!','" + (String.valueOf(choiceCustomerNameBox.getValue())) + "' ,'" + (deliveryTo.getText()) +
//                                    "',1,'Do uzupełnienia!','" + (contractName) + "','" + (String.valueOf(nrContractBuy.getValue())) + "','Do uzupełnienia!','white' );");
//
//                            new DataOperationAll("UPDATE ContractsOpenBuy set nrTruckContract = (nrTruckContract + 1) where ContractNAme = '" + (String.valueOf(nrContractBuy.getValue())) + "'");
//                            new DataOperationAll("update ContractsOpenBuy set OpenClose = 1 WHERE  nrTruck = nrTruckContract");
//                            new DataOperationAll("insert into ContractsClose select * From ContractsOpenBuy where openclose =1");
//                            new DataOperationAll("delete From ContractsOpenBuy where openclose =1");


                            deliveryPlate.clear();
                            deliveryAmount.clear();
                            deliveryTo.clear();
                            materialID.setText("");
                            dataPickError.setText("");
                            choiceCustomerNameBox.setPromptText("Wybier");
                            nrContractBuy.setPromptText("Wybier");
                            datePickerChoice.setPromptText("Wybierz Datę");

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

    private int type;


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


//        new DataOperationAll("update " + contractType + " set OpenClose = 1 WHERE  nrTruck = nrTruckContract");

//        new DataOperationAll("insert into ContractsClose select * From " + contractType + " where openClose =1");
//        new DataOperationAll("delete From " + contractType + " where openclose =1");
//
//        daoContractsCloseController.CheckStatusTransfer(id,table,dao);
    }

    public void materialPrepareBuy() {
        if (!nrContractBuy.getValue().toString().equals("Wybierz")) {
            type = 0;
//
//
//        System.out.println("--------------");
//        String NameOfContract = String.valueOf(nrContractBuy.getValue());
//        System.out.println("materialPrepareBuy działa " + NameOfContract);
//        System.out.println(NameOfContract);
//        SelectOneThing selectOneThing = new SelectOneThing("SELECT idName FROM ContractsOpenBuy Where contractName = '" + (String.valueOf(nrContractBuy.getValue())) + "'", "idName");
//        System.out.println(selectOneThing.getString());
//        materialID.setText(new SelectOneThing("SELECT idName FROM ContractsOpenBuy Where contractName = '" + (String.valueOf(nrContractBuy.getValue())) + "'", "idName").getString());
            String s = nrContractBuy.getValue().toString();
            String s1 = "ContractName";
            List<ContractsOpenBuy> contractName = daoContractBuyController.find(s1, s);
            System.out.println("lista " + contractName);
            String idName = contractName.get(0).getIdName();

            materialID.setText(idName);


//        System.out.println("Nazwa materiału" + materialID.getText());}
        }
    }


    public void materialPrepareSell() {
        type = 1;
////
//
//        System.out.println("--------------");
//        String NameOfContract = String.valueOf(nrContractBuy.getValue());
//        System.out.println("materialPrepareBuy działa " + NameOfContract);
//        System.out.println(NameOfContract);
//        SelectOneThing selectOneThing = new SelectOneThing("SELECT idName FROM ContractsOpenSell Where contractName = '" + (String.valueOf(nrContractSell.getValue())) + "'", "idName");
//        System.out.println(selectOneThing.getString());
//        materialID.setText(String.valueOf(daoMaterialController.findByName(nrContractSell.getValue().toString()).get(0).getId()));

//        materialID.setText(new SelectOneThing("SELECT idName FROM ContractsOpenSell Where contractName = '" + (String.valueOf(nrContractSell.getValue())) + "'", "idName").getString());

        List<ContractsOpenSell> contractName = daoContractSellController.find("contractName", nrContractSell.getValue().toString());

        String idName = contractName.get(0).getIdName();

        materialID.setText(idName);
//        System.out.println("Nazwa materiału" + materialID.getText());
    }


    public int customerId;

    public void selectComboBoxList() {
        if (!choiceCustomerNameBox.getValue().toString().equals("Wybierz")) {
//        String comboBoxList = String.valueOf(choiceCustomerNameBox.getValue());
//        System.out.println(comboBoxList);
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
            customerId = daoCustomerController.findByName(choiceCustomerNameBox.getValue().toString()).get(0).getId();
//        customerId = new SelectOneThing("SELECT id FROM Customers WHERE Name = '" + (String.valueOf(choiceCustomerNameBox.getValue())) + "' ", "id").getId();

//        String query = "SELECT id FROM Customers WHERE Name = '" + comboBoxList + "' ";
//        System.out.println(query);
//        try {
//            //get connection
//            conn = DBConnection.getConnection();
//
//            //create preparedStatement
//            preparedStatement = conn.prepareStatement(query);
//
//            ResultSet rs = preparedStatement.executeQuery(query);
//            //execute query
//            while (rs.next()) {
//                System.out.println((rs.getInt("id")));
//                customerId = ((rs.getInt("id")));
//            }
//
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
            listOfChoiceCotract();

        }

    }

    List<ContractsOpenBuy> contractsOpenBuy;
    List<ContractsOpenSell> contractsOpenSell;

    public void listOfChoiceCotract() {
//        String comboBoxList = String.valueOf(choiceCustomerNameBox.getValue());
//
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//
//
//        String query = "SELECT contractName FROM ContractsOpenBuy Where idCustomer = '" + customerId + "';";
//        new SelectListOfThings("SELECT contractName FROM ContractsOpenBuy Where idCustomer = '" + customerId + "';", "ContractNAme", choiceContractListBuy);

        contractsOpenBuy = daoContractBuyController.selectList();

//        List<String> collect1 = contractsOpenBuy.stream().map(ContractsOpenBuy::getContractName).collect(Collectors.toList());
//        System.out.println(collect1);
        List<ContractsOpenBuy> collect = contractsOpenBuy.stream().filter(e -> e.getIdCustomer() == customerId).collect(Collectors.toList());

        choiceContractListBuy.setAll(contractsOpenBuy.stream().filter(e -> e.getIdCustomer() == customerId).map(ContractsOpenBuy::getContractName).collect(Collectors.toList()));

//        new SelectListOfThings("SELECT contractName FROM ContractsOpenSell Where idCustomer = '" + customerId + "';", "ContractNAme", choiceContractListSell);
        contractsOpenSell = daoContractSellController.selectList();
//        List<String> collect = contractsOpenBuy.stream().map(ContractsOpenBuy::getContractName).collect(Collectors.toList());
//        System.out.println(collect);
        choiceContractListSell.setAll(contractsOpenSell.stream().filter(e -> e.getIdCustomer() == customerId).map(ContractsOpenSell::getContractName).collect(Collectors.toList()));

//        try {
//            //get connection
//            conn = DBConnection.getConnection();
//
//            //create preparedStatement
//            preparedStatement = conn.prepareStatement(query);
//
//            //execute query
//            ResultSet rs = preparedStatement.executeQuery(query);
//            while (rs.next()) {
//                choiceContractListBuy.add(rs.getString("contractName"));
//            }
//
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        nrContractBuy.setItems(choiceContractListBuy);
        nrContractSell.setItems(choiceContractListSell);
//        System.out.println("wywołanie listy kontraktów " + choiceContractListBuy);
    }

}
