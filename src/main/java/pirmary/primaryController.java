package pirmary;

import combo.ComboCustomers;
import combo.DataOperationAll;
import combo.SelectListOfThings;
import combo.SelectOneThing;
import connection.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class primaryController {


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
    private Label materiaID;

    @FXML
    private DatePicker datePickerChoice;

    @FXML
    private Label datapickererror;


    private String contractName;

    ObservableList ChoiceContractList = FXCollections.observableArrayList();


    public void initialize() {


        new ComboCustomers(choiceCustomerNameBox, "Select Name From Customers", "Name", "ComboBox");


    }

    public void addButton() {
        Object datapicker = datePickerChoice.getValue();
        String datapickerString = String.valueOf(datePickerChoice.getValue());

        String deliveryAmountText = deliveryAmount.getText();
        String deliveryToText = deliveryTo.getText();
        String deliveryPlateText = deliveryPlate.getText();

        if (deliveryPlate.getText().length() > 0) {
            if (deliveryTo.getText().length() > 0) {
                if (deliveryAmount.getText().length() > 0) {
                    if (((datapickerString.length()) > 0) && (datapickerString != "null")) {

                        System.out.println("sprawdzam środek" + datapickerString.length());

                        Object nameOfCompany = choiceCustomerNameBox.getValue();


                        String MaterialToString = String.valueOf(materiaID.getText());


                        Object NameOfContract = nrContractBuy.getValue();
                        String ContractToString = String.valueOf(nrContractBuy.getValue());

                        Object NameOfContractSell = nrContractSell.getValue();
                        String ContractSellToString = String.valueOf(NameOfContractSell);

                        System.out.println(ContractSellToString + "sell kontrakt");

                        if (ContractSellToString.length() > 4) { // sprawdzic czemu 4? co autor miał na myśli
                            ContractSellToString = contractName;

                        } else {
                            contractName = "dopisz";

                        }
                        System.out.println(contractName);

                        try {
                            Integer.parseInt(deliveryAmountText);

                            new DataOperationAll("INSERT INTO All_View (Data,Material,Truck,Amount,Final_Amount,Froms,Tos,Truck_Nr,Transport_Order,Vk,Ek,Ams_doc,color) " +
                                    "VALUES ('" + (String.valueOf(datePickerChoice.getValue())) + "','" + (String.valueOf(materiaID.getText())) + "','" + (deliveryPlate.getText()) +
                                    "','" + (deliveryAmountText) + "' , 'Do uzupełnienia!','" + (String.valueOf(choiceCustomerNameBox.getValue())) + "' ,'" + (deliveryTo.getText()) +
                                    "',1,'Do uzupełnienia!','" + (contractName) + "','" + (String.valueOf(nrContractBuy.getValue())) + "','Do uzupełnienia!','white' );");

                            new DataOperationAll("UPDATE ContractsOpenBuy set NrTruckContract = (NrTruckContract + 1) where ContractNAme = '" + (ContractToString) + "'");
                            new DataOperationAll("update ContractsOpenBuy set OpenClose = 1 WHERE  NrTruck = NrTruckContract");
                            new DataOperationAll("insert into ContractsClose select * From ContractsOpenBuy where openclose =1");
                            new DataOperationAll("delete From ContractsOpenBuy where openclose =1");


                            deliveryPlate.clear();
                            deliveryAmount.clear();
                            deliveryTo.clear();
                            materiaID.setText("");
                            datapickererror.setText("");
                            choiceCustomerNameBox.setValue(null);
                            nrContractBuy.setValue(null);
                            datePickerChoice.setValue(null);

                        } catch (Exception e) {
                            deliveryAmount.setText("Tylko liczby");
                            System.out.println("nie integer");
                        }
                    } else {
                        datapickererror.setText("Wymagana Data");
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

    public void Materialprepare() {



        System.out.println("--------------");
        String NameOfContract = String.valueOf(nrContractBuy.getValue());
        System.out.println("Materialprepare działa " + NameOfContract);
        System.out.println(NameOfContract);
        SelectOneThing selectOneThing = new SelectOneThing("SELECT idName FROM ContractsOpenBuy Where ContractName = '" + (String.valueOf(nrContractBuy.getValue())) + "'", "idName");
        System.out.println(selectOneThing.getSrting());
        materiaID.setText( new SelectOneThing("SELECT idName FROM ContractsOpenBuy Where ContractName = '" + (String.valueOf(nrContractBuy.getValue())) + "'", "idName").getSrting());

        System.out.println("Nazwa materiału" + materiaID.getText());
    }


    public int customerid;

    public void SelectComboBoxList() {
        String comboBoxList = String.valueOf(choiceCustomerNameBox.getValue());
        System.out.println(comboBoxList);
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;

        customerid = new SelectOneThing("SELECT id FROM Customers WHERE Name = '" + comboBoxList + "' ", "id").getId();
//        String query = "SELECT id FROM Customers WHERE Name = '" + comboBoxList + "' ";
//        System.out.println(query);
//        try {
//            //get connection
//            conn = DBconnection.getConnection();
//
//            //create preparedStatement
//            preparedStatement = conn.prepareStatement(query);
//
//            //execute query
//            ResultSet rs = preparedStatement.executeQuery(query);
//            while (rs.next()) {
//                System.out.println((rs.getInt("id")));
//                customerid = ((rs.getInt("id")));
//            }
//
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        ListOfChoiceCotract();


    }

    public void ListOfChoiceCotract() {
//        String comboBoxList = String.valueOf(choiceCustomerNameBox.getValue());
//
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//
//
//        String query = "SELECT ContractName FROM ContractsOpenBuy Where idCustomer = '" + customerid + "';";
        new SelectListOfThings("SELECT ContractName FROM ContractsOpenBuy Where idCustomer = '" + customerid + "';","ContractNAme",ChoiceContractList);
//        try {
//            //get connection
//            conn = DBconnection.getConnection();
//
//            //create preparedStatement
//            preparedStatement = conn.prepareStatement(query);
//
//            //execute query
//            ResultSet rs = preparedStatement.executeQuery(query);
//            while (rs.next()) {
//                ChoiceContractList.add(rs.getString("ContractName"));
//            }
//
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        nrContractBuy.setItems(ChoiceContractList);
        System.out.println("wywołanie listy kontraktów " + ChoiceContractList);
    }

    public ObservableList getChoiceContractList() {
        return ChoiceContractList;
    }
}
