package sample.pirmary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.combo.ComboCustomersName;
import sample.connection.DBconnection;

import java.sql.*;


public class primaryController {


    @FXML
    private TextField Delivery_Amount_Truck;

    @FXML
    private TextField Delivery_Amount;

    @FXML
    private TextField Delivery_To;

    @FXML
    private Button Delivery_add;

    @FXML
    private TextField Delivery_Plate;

    @FXML
    private ComboBox NrContractBuy;

    @FXML
    private ComboBox NrContractSell;

    @FXML
    private ChoiceBox ChoiceContractBuy;

    @FXML
    private TextField NrBuyContract;

    @FXML
    private TextField AmountContractBuy;

    @FXML
    private Button addBuy;

    @FXML
    private ChoiceBox choiceMaterialBuyContract;

    @FXML
    private TextField TruckContractBuy;

    @FXML
    private ChoiceBox ChoiceContractBuyToSell;

    @FXML
    private ComboBox ComboBox;

    @FXML
    private Label MateriaID;

    @FXML
    private DatePicker DataPicker;

    @FXML
    private Label datapickererror;


    private String contractName;
    private String MaterialName;
    ObservableList ChoiceContractList = FXCollections.observableArrayList();


 public void initialize(){


     ComboCustomersName comboCustomersName = new ComboCustomersName(ComboBox);



 }

    public void addButton() {
        Object datapicker = DataPicker.getValue();
        String datapickerString = String.valueOf(datapicker);

        String deliveryAmountText = Delivery_Amount.getText();
        String deliveryToText = Delivery_To.getText();
        String deliveryPlateText = Delivery_Plate.getText();
        if (Delivery_Plate.getText().length() > 0) {
            if (Delivery_To.getText().length() > 0) {
                if (Delivery_Amount.getText().length() > 0) {
                    if (((datapickerString.length()) > 0) && (datapickerString != "null")) {
                        String datText = Delivery_Amount_Truck.getText();
                        System.out.println("sprawdzam środek" + datapickerString.length());

                        Object NameOfCompany = ComboBox.getValue();
                        String NameToString = String.valueOf(NameOfCompany);

                        Object NameOfMaterial = MateriaID.getText();
                        String MaterialToString = String.valueOf(NameOfMaterial);

                        Object NameOfContract = NrContractBuy.getValue();
                        String ContractToString = String.valueOf(NameOfContract);

                        Object NameOfContractSell = NrContractSell.getValue();
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
                                            "VALUES ('" + (datapickerString) + "','" + (MaterialToString) + "','" + (deliveryPlateText) + "','" + (deliveryAmountText) + "' , 10000,'" + (NameToString) + "' ,'" + (deliveryToText) + "','" + (datText) + "','12-E','" + (contractName) + "','" + (ContractToString) + "','nic' );";

                                    String sql1 = "UPDATE ContractsOpenBuy set NrTruckContract = (NrTruckContract + '" + (datText) + "')where ContractNAme = '" + (ContractToString) + "'";
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


                            Delivery_Plate.setText("");
                            Delivery_Amount_Truck.setText("");
                            Delivery_Amount.setText("");
                            Delivery_To.setText("");
                            MateriaID.setText("");
                            datapickererror.setText("");

                        } catch (NumberFormatException e) {
                            Delivery_Amount.setText("Tylko liczby");
                            System.out.println("nie integer");
                        }
                    } else {
                        datapickererror.setText("Wymagana Data");
                    }
                } else {
                    Delivery_Amount.setText("Wpisz ilość");
                }
            } else {
                Delivery_To.setText("Wpisz Odbiorce");
            }
        } else {
            Delivery_Plate.setText("Wpisz numer rejestracyjny");
        }


    }

    public void Materialprepare() {
        System.out.println("--------------");
        String NameOfContract = String.valueOf(NrContractBuy.getValue());
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
                MateriaID.setText((rs.getString("idName")));

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
        String comboBoxList = String.valueOf(ComboBox.getValue());
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
        String comboBoxList = String.valueOf(ComboBox.getValue());


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
        NrContractBuy.setItems(ChoiceContractList);
        System.out.println("wywołanie listy kontraktów "  + ChoiceContractList);
    }

    public ObservableList getChoiceContractList() {
        return ChoiceContractList;
    }
}
