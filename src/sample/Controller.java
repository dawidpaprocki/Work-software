package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.sql.*;
import java.sql.Connection;

public class Controller
{


    public TextField Delivery_Material;
    public TextField Delivery_Vk;
    public TextField Delivery_Amount_Truck;
    public TextField Delivery_Amount;
    public TextField Delivery_To;
    public TextField Delivery_From;
    public TextField Delivery_Plate;

    public Button Delivery_add;
    public ChoiceBox choice;
    public Button choiceboxtest;
    public ChoiceBox choiceMaterial;
    public Button addBuy;
    public ChoiceBox choiceMaterialBuyContract;

    public TextField NrBuyContract;
    public TextField AmountContractBuy;
    public TextField TruckContractBuy;
    public ChoiceBox ChoiceContractBuy;
    public ChoiceBox choiceContract;
    public ComboBox comobox;


    @FXML
    private TableView<table> tables;
    @FXML
    private TableColumn<table, String> ColumnData;
    @FXML
    private TableColumn<table, String> ColumnMaterial;
    @FXML
    private TableColumn<table, String> ColumnPlate;
    @FXML
    private TableColumn<table, String> ColumnAmount;
    @FXML
    private TableColumn<table, String> ColumnFinal_Amount;
    @FXML
    private TableColumn<table, String> ColumnFroms;
    @FXML
    private TableColumn<table, String> ColumnTos;
    @FXML
    private TableColumn<table, String> ColumnTruck;
    @FXML
    private TableColumn<table, String> ColumnOrder;
    @FXML
    private TableColumn<table, String> ColumnVk;
    @FXML
    private TableColumn<table, String> ColumnEk;
    @FXML
    private TableColumn<table, String> ColumnDoc;

    private ObservableList<table>data;

    ObservableList checklist = FXCollections.observableArrayList();  ObservableList Combolist = FXCollections.observableArrayList();
    ObservableList Mchecklist = FXCollections.observableArrayList();
    ObservableList Cchecklist = FXCollections.observableArrayList();
    @FXML
    void initialize()
    {
     data = FXCollections.observableArrayList();



        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM All_View;" );

            while ( rs.next() )
            {
                data.add(new table
                (
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)
                ));
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        ColumnData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        ColumnMaterial.setCellValueFactory(new PropertyValueFactory<>("Material"));
        ColumnPlate.setCellValueFactory(new PropertyValueFactory<>("Plate"));
        ColumnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        ColumnFinal_Amount.setCellValueFactory(new PropertyValueFactory<>("Final_Amount"));
        ColumnFroms.setCellValueFactory(new PropertyValueFactory<>("Froms"));
        ColumnTos.setCellValueFactory(new PropertyValueFactory<>("Tos"));
        ColumnTruck.setCellValueFactory(new PropertyValueFactory<>("Truck"));
        ColumnOrder.setCellValueFactory(new PropertyValueFactory<>("Order"));
        ColumnVk.setCellValueFactory(new PropertyValueFactory<>("Vk"));
        ColumnEk.setCellValueFactory(new PropertyValueFactory<>("Ek"));
        ColumnDoc.setCellValueFactory(new PropertyValueFactory<>("Doc"));

        tables.setItems(null);
        tables.setItems(data);


    }
    int Customerid;

    @FXML
    public void addButton()
    {

        String dpText = Delivery_Plate.getText();
        String datText = Delivery_Amount_Truck.getText();
        String daText = Delivery_Amount.getText();
        String dtText = Delivery_To.getText();

        String dvkText = Delivery_Vk.getText();

        Object NameOfCompany = choice.getValue();
        String NameToString = String.valueOf(NameOfCompany);

        Object NameOfMaterial = choiceMaterial.getValue();
        String MaterialToString = String.valueOf(NameOfMaterial);

        Object NameOfContract = choiceContract.getValue();
        String ContractToString = String.valueOf(NameOfContract);




        System.out.println("działa button");

        Connection c = null;
        Statement stmt = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            try{
            stmt = c.createStatement();
            String sql = "INSERT INTO All_View (Data,Material,Truck,Amount,Final_Amount,Froms,Tos,Truck_Nr,Transport_Order,Vk,Ek,Ams_doc) " +
                    "VALUES ('15-05-2018','" + (MaterialToString) + "','" + (dpText) + "','" + (daText) + "' , 10000,'" + (NameToString) + "' ,'" + (dtText) + "','" + (datText) + "','12-E','" + (dvkText) + "','nic','" + (ContractToString) + "' );";
            String sql1 = "UPDATE ContractsOpen set NrTruckContract = (NrTruckContract + '" + (datText) + "')where ContractNAme = '" + (ContractToString) + "'";
            String sql2 = "update ContractsOpen set OpenClose = 1 WHERE  NrTruck = NrTruckContract";
            String sql3 = "insert into ContractsClose select * From ContractsOpen where openclose =1";
            String sql4 = "delete From ContractsOpen where openclose =1";

            stmt.execute(sql) ;
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);}
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
           /* try{
                stmt = c.createStatement();
                String sql = "UPDATE ContractsOpen set NrTruckContract = '" + (datText) + "'where ContractNAme = '" + (ContractToString) + "'";

                stmt.executeUpdate(sql);}
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }  /*      zjebane
            try{
                stmt = c.createStatement();
                String sql = "INSERT INTO ContractsClose SELECT * FROM ContractsOpen WHERE ";

                stmt.executeUpdate(sql);}
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }   */




            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }


        Delivery_Plate.setText("");
        Delivery_Amount_Truck.setText("");
        Delivery_Amount.setText("");
        Delivery_To.setText("");
        Delivery_Vk.setText("");
    }

    @FXML
    public void Listchoice()
    {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT NAME FROM Customers;" );

            while ( rs.next() )
            {
                checklist.add(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        choice.setItems(checklist);
        ChoiceContractBuy.setItems(checklist);


    }

    public void combo()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT NAME FROM Customers;" );

            while ( rs.next() )
            {
                Combolist.add(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        comobox.setItems(Combolist);
        System.out.println(comobox.getValue());
        String s = String.valueOf(comobox.getValue());
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id FROM Customers WHERE Name = '" + s + "' ;" );

            while ( rs.next() )
            {
                System.out.println((rs.getInt("id")));
                Customerid = ((rs.getInt("id")));

            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println(Customerid);
        ListchoiceContract();



    }

    @FXML
    public void ListchoiceMaterial()
    {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT NAME FROM Material;" );

            while ( rs.next() )
            {
                Mchecklist.add(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        choiceMaterial.setItems(Mchecklist);
        choiceMaterialBuyContract.setItems(Mchecklist);



    }

    @FXML
    public void ListchoiceContract()
    {

        Cchecklist.clear();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            System.out.println("bum"+ Customerid);
            ResultSet rs = stmt.executeQuery( "SELECT ContractName FROM ContractsOpen Where idCustomer = '" + Customerid + "';" );

            while ( rs.next() )
            {
                Cchecklist.add(rs.getString("ContractName"));
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        choiceContract.setItems(Cchecklist);
        System.out.println(Cchecklist);



    }

    int id;
    @FXML
    public void addButtonContractBuy()
    {

        String NBCText = NrBuyContract.getText();
        String ACBText = AmountContractBuy.getText();
        String TCBText = TruckContractBuy.getText();

        Object NameOfCompanyBuyContract = ChoiceContractBuy.getValue();
        String NameBuyToString = String.valueOf(NameOfCompanyBuyContract);



        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id FROM Customers WHERE NAME = '" + NameBuyToString + "' ;" );

            while ( rs.next() )
            {
                System.out.println((rs.getInt("id")));
               id = ((rs.getInt("id")));

            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }




        Object NameOfMaterialBuyContract = choiceMaterialBuyContract.getValue();
        String MaterialContractBuyToString = String.valueOf(NameOfMaterialBuyContract);


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO ContractsOpen (idCustomer,idName,NrTruck,NrTruckContract,ContractName,Amount,OpenClose) " +
                    "VALUES ('"+ id +"','" + (MaterialContractBuyToString) + "','" + (TCBText) + "' , '0' ,'" + (NBCText) + "','" + (ACBText) + "','0') ;";
            stmt.executeUpdate(sql);


            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }


        NrBuyContract.setText("");
        AmountContractBuy.setText("");
        TruckContractBuy.setText("");
        Delivery_To.setText("");
        Delivery_Vk.setText("");
    }


}
