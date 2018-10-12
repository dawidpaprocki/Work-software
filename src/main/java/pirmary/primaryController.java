package pirmary;

import combo.ComboCustomers;
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
    private TextField deliveryAmountTruck;

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
    private String MaterialName;
    ObservableList ChoiceContractList = FXCollections.observableArrayList();


 public void initialize(){


     new ComboCustomers(choiceCustomerNameBox, "Select Name From Customers","Name","ComboBox");


 }

    public void addButton() {
        Object datapicker = datePickerChoice.getValue();
        String datapickerString = String.valueOf(datapicker);

        String deliveryAmountText = deliveryAmount.getText();
        String deliveryToText = deliveryTo.getText();
        String deliveryPlateText = deliveryPlate.getText();

        if (deliveryPlate.getText().length() > 0) {
            if (deliveryTo.getText().length() > 0) {
                if (deliveryAmount.getText().length() > 0) {
                    if (((datapickerString.length()) > 0) && (datapickerString != "null")) {

                        System.out.println("sprawdzam środek" + datapickerString.length());

                        Object nameOfCompany = choiceCustomerNameBox.getValue();
                        String NameToString = String.valueOf(nameOfCompany);

                        Object NameOfMaterial = materiaID.getText();
                        String MaterialToString = String.valueOf(NameOfMaterial);

                        Object NameOfContract = nrContractBuy.getValue();
                        String ContractToString = String.valueOf(NameOfContract);

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

                            System.out.println("działa button");

                            Connection conn = null;
                            PreparedStatement preparedStatement = null;
                            try {


                                try {
                                    conn = DBconnection.getConnection();
                                    String sql = "INSERT INTO All_View (Data,Material,Truck,Amount,Final_Amount,Froms,Tos,Truck_Nr,Transport_Order,Vk,Ek,Ams_doc) " +
                                            "VALUES ('" + (datapickerString) + "','" + (MaterialToString) + "','" + (deliveryPlateText) + "','" + (deliveryAmountText) + "' , '" + (deliveryAmountText) + "','" + (NameToString) + "' ,'" + (deliveryToText) + "',1,'Do uzupełnienia!','" + (contractName) + "','" + (ContractToString) + "','nic' );";

                                    String sql1 = "UPDATE ContractsOpenBuy set NrTruckContract = (NrTruckContract + 1) where ContractNAme = '" + (ContractToString) + "'";
                                    String sql2 = "update ContractsOpenBuy set OpenClose = 1 WHERE  NrTruck = NrTruckContract";
                                    String sql3 = "insert into ContractsClose select * From ContractsOpenBuy where openclose =1";
                                    String sql4 = "delete From ContractsOpenBuy where openclose =1";

                                    preparedStatement = conn.prepareStatement(sql);
                                    preparedStatement.execute(sql);
                                    preparedStatement = conn.prepareStatement(sql1);
                                    preparedStatement.execute(sql1);
                                    preparedStatement = conn.prepareStatement(sql2);
                                    preparedStatement.execute(sql2);
                                    preparedStatement = conn.prepareStatement(sql3);
                                    preparedStatement.execute(sql3);
                                    preparedStatement = conn.prepareStatement(sql4);
                                    preparedStatement.execute(sql4);
                                } catch (Exception e) {
                                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                    System.exit(0);
                                }


                                preparedStatement.close();
                                conn.close();
                            } catch (Exception e) {
                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                            }


                            deliveryPlate.clear();
                            deliveryAmountTruck.clear();
                            deliveryAmount.clear();
                            deliveryTo.clear();
                            materiaID.setText("");
                            datapickererror.setText("");
                            choiceCustomerNameBox.setValue(null);
                            nrContractBuy.setValue(null);
                            datePickerChoice.setValue(null);

                        } catch (NumberFormatException e) {
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
        System.out.println("Materialprepare działa" + NameOfContract);


        System.out.println(NameOfContract);
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {

            String query = "SELECT idName FROM ContractsOpenBuy Where ContractName = '" + NameOfContract + "';";
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(query);
            while (rs.next()) {
                System.out.println((rs.getString("idName")));
                materiaID.setText((rs.getString("idName")));

            }

            //close connection
            preparedStatement.close();
            conn.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Nazwa materiału" + MaterialName);
    }


    public int Customerid;

    public  void  SelectComboBoxList (){
        String comboBoxList = String.valueOf(choiceCustomerNameBox.getValue());
        System.out.println(comboBoxList);

        Connection conn = null;
        PreparedStatement preparedStatement = null;


        String query = "SELECT id FROM Customers WHERE Name = '" + comboBoxList + "' ";
        System.out.println(query);
        try{
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(query);
            while ( rs.next() )
            {
                System.out.println((rs.getInt("id")));
                Customerid = ((rs.getInt("id")));
            }

            //close connection
            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        ListOfChoiceCotract();



    }

    public void ListOfChoiceCotract(){
        String comboBoxList = String.valueOf(choiceCustomerNameBox.getValue());


        Connection conn = null;
        PreparedStatement preparedStatement = null;


        String query = "SELECT ContractName FROM ContractsOpenBuy Where idCustomer = '" + Customerid + "';";

        try{
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(query);
            while ( rs.next() )
            {
                ChoiceContractList.add(rs.getString("ContractName"));
            }

            //close connection
            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        nrContractBuy.setItems(ChoiceContractList);
        System.out.println("wywołanie listy kontraktów "  + ChoiceContractList);
    }

    public ObservableList getChoiceContractList() {
        return ChoiceContractList;
    }
}
