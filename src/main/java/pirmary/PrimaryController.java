package pirmary;

import combo.ComboCustomers;
import combo.DataOperationAll;
import combo.SelectListOfThings;
import combo.SelectOneThing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


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

    ObservableList choiceContractList = FXCollections.observableArrayList();


    public void initialize() {


        new ComboCustomers(choiceCustomerNameBox, "Select Name From Customers", "Name", "ComboBox");


    }

    public void addButton() {
//        Object datapicker = datePickerChoice.getValue();
        String dataPickerString = String.valueOf(datePickerChoice.getValue());

        String deliveryAmountText = deliveryAmount.getText();
//        String deliveryToText = deliveryTo.getText();
//        String deliveryPlateText = deliveryPlate.getText();

        if (deliveryPlate.getText().length() > 0) {
            if (deliveryTo.getText().length() > 0) {
                if (deliveryAmount.getText().length() > 0) {
                    if (((dataPickerString.length()) > 0) && (dataPickerString != "null")) {

//                        System.out.println("sprawdzam środek" + datapickerString.length());

//                        Object nameOfCompany = choiceCustomerNameBox.getValue();
//
//
//                        String MaterialToString = String.valueOf(materialID.getText());
//
//
//                        Object NameOfContract = nrContractBuy.getValue();
//                        String ContractToString = String.valueOf(nrContractBuy.getValue());

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

                            new DataOperationAll("INSERT INTO All_View (data,material,truck,amount,finalAmount,froms,tos,truckNr,Transport_Order,vk,ek,amsDoc,color) " +
                                    "VALUES ('" + (String.valueOf(datePickerChoice.getValue())) + "','" + (String.valueOf(materialID.getText())) + "','" + (deliveryPlate.getText()) +
                                    "','" + (deliveryAmountText) + "' , 'Do uzupełnienia!','" + (String.valueOf(choiceCustomerNameBox.getValue())) + "' ,'" + (deliveryTo.getText()) +
                                    "',1,'Do uzupełnienia!','" + (contractName) + "','" + (String.valueOf(nrContractBuy.getValue())) + "','Do uzupełnienia!','white' );");

                            new DataOperationAll("UPDATE ContractsOpenBuy set nrTruckContract = (nrTruckContract + 1) where ContractNAme = '" + (String.valueOf(nrContractBuy.getValue())) + "'");
                            new DataOperationAll("update ContractsOpenBuy set OpenClose = 1 WHERE  nrTruck = nrTruckContract");
                            new DataOperationAll("insert into ContractsClose select * From ContractsOpenBuy where openclose =1");
                            new DataOperationAll("delete From ContractsOpenBuy where openclose =1");


                            deliveryPlate.clear();
                            deliveryAmount.clear();
                            deliveryTo.clear();
                            materialID.setText("");
                            dataPickError.setText("");
                            choiceCustomerNameBox.setValue(null);
                            nrContractBuy.setValue(null);
                            datePickerChoice.setValue(null);

                        } catch (Exception e) {
                            deliveryAmount.setText("Tylko liczby");
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

    public void materialPrepare() {

//
//
//        System.out.println("--------------");
//        String NameOfContract = String.valueOf(nrContractBuy.getValue());
//        System.out.println("materialPrepare działa " + NameOfContract);
//        System.out.println(NameOfContract);
//        SelectOneThing selectOneThing = new SelectOneThing("SELECT idName FROM ContractsOpenBuy Where contractName = '" + (String.valueOf(nrContractBuy.getValue())) + "'", "idName");
//        System.out.println(selectOneThing.getString());
        materialID.setText( new SelectOneThing("SELECT idName FROM ContractsOpenBuy Where contractName = '" + (String.valueOf(nrContractBuy.getValue())) + "'", "idName").getString());
//
//        System.out.println("Nazwa materiału" + materialID.getText());
    }


    public int customerId;

    public void selectComboBoxList() {
//        String comboBoxList = String.valueOf(choiceCustomerNameBox.getValue());
//        System.out.println(comboBoxList);
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;

        customerId = new SelectOneThing("SELECT id FROM Customers WHERE Name = '" + (String.valueOf(choiceCustomerNameBox.getValue())) + "' ", "id").getId();
//        String query = "SELECT id FROM Customers WHERE Name = '" + comboBoxList + "' ";
//        System.out.println(query);
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

    public void listOfChoiceCotract() {
//        String comboBoxList = String.valueOf(choiceCustomerNameBox.getValue());
//
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//
//
//        String query = "SELECT contractName FROM ContractsOpenBuy Where idCustomer = '" + customerId + "';";
        new SelectListOfThings("SELECT contractName FROM ContractsOpenBuy Where idCustomer = '" + customerId + "';","ContractNAme", choiceContractList);
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
//                choiceContractList.add(rs.getString("contractName"));
//            }
//
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        nrContractBuy.setItems(choiceContractList);
//        System.out.println("wywołanie listy kontraktów " + choiceContractList);
    }

    public ObservableList getChoiceContractList() {
        return choiceContractList;
    }
}
