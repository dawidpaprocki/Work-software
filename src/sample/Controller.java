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
    public TextField Delivery_Ek;
    public TextField Delivery_Vk;
    public TextField Delivery_Amount_Truck;
    public TextField Delivery_Amount;
    public TextField Delivery_To;
    public TextField Delivery_From;
    public TextField Delivery_Plate;

    public Button Delivery_add;

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


    @FXML
    public void addButton()
    {
        String dnText = Delivery_Material.getText();
        String dpText = Delivery_Plate.getText();
        String datText = Delivery_Amount_Truck.getText();
        String daText = Delivery_Amount.getText();
        String dtText = Delivery_To.getText();
        String dfText = Delivery_From.getText();
        String dekText = Delivery_Ek.getText();
        String dvkText = Delivery_Vk.getText();





        System.out.println("działa button");

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO All_View (Data,Material,Truck,Amount,Final_Amount,Froms,Tos,Truck_Nr,Transport_Order,Vk,Ek,Ams_doc) " +
                    "VALUES ('15-05-2018','" + (dnText) + "','" + (dpText) + "','" + (daText) + "' , 10000,'" + (dfText) + "' ,'" + (dtText) + "','" + (datText) + "','12-E','" + (dvkText) + "','" + (dekText) + "','WNT/004/01/04' );";
            stmt.executeUpdate(sql);


            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

      // System.out.println(sql);
        Delivery_Material.setText("");
        Delivery_Plate.setText("");
        Delivery_Amount_Truck.setText("");
        Delivery_Amount.setText("");
        Delivery_To.setText("");
        Delivery_From.setText("");
        Delivery_Ek.setText("");
        Delivery_Vk.setText("");
    }



}
