package sample.tablePackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.connectionPackage.SelectAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainTable {
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


    public MainTable(ObservableList<table> date, TableView<table> tables, TableColumn<table, String> ColumnData, TableColumn<table, String> ColumnMaterial, TableColumn<table, String> ColumnPlate,
             TableColumn<table, String> ColumnAmount, TableColumn<table, String> ColumnFinal_Amount, TableColumn<table, String> ColumnFroms, TableColumn<table, String> ColumnTos,
             TableColumn<table, String> ColumnTruck, TableColumn<table, String> ColumnOrder, TableColumn<table, String> ColumnVk, TableColumn<table, String> ColumnEk, TableColumn<table, String> ColumnDoc) {

        data = FXCollections.observableArrayList();

        SelectAll selectAll = new SelectAll();
        SelectAll.SelectAll("All_View",data);

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
        tables.setItems(selectAll.getData());


    }

    public TableView<table> getTables() {
        return tables;
    }

    public void setTables(TableView<table> tables) {
        this.tables = tables;
    }

    public TableColumn<table, String> getColumnData() {
        return ColumnData;
    }

    public void setColumnData(TableColumn<table, String> columnData) {
        ColumnData = columnData;
    }

    public TableColumn<table, String> getColumnMaterial() {
        return ColumnMaterial;
    }

    public void setColumnMaterial(TableColumn<table, String> columnMaterial) {
        ColumnMaterial = columnMaterial;
    }

    public TableColumn<table, String> getColumnPlate() {
        return ColumnPlate;
    }

    public void setColumnPlate(TableColumn<table, String> columnPlate) {
        ColumnPlate = columnPlate;
    }

    public TableColumn<table, String> getColumnAmount() {
        return ColumnAmount;
    }

    public void setColumnAmount(TableColumn<table, String> columnAmount) {
        ColumnAmount = columnAmount;
    }

    public TableColumn<table, String> getColumnFinal_Amount() {
        return ColumnFinal_Amount;
    }

    public void setColumnFinal_Amount(TableColumn<table, String> columnFinal_Amount) {
        ColumnFinal_Amount = columnFinal_Amount;
    }

    public TableColumn<table, String> getColumnFroms() {
        return ColumnFroms;
    }

    public void setColumnFroms(TableColumn<table, String> columnFroms) {
        ColumnFroms = columnFroms;
    }

    public TableColumn<table, String> getColumnTos() {
        return ColumnTos;
    }

    public void setColumnTos(TableColumn<table, String> columnTos) {
        ColumnTos = columnTos;
    }

    public TableColumn<table, String> getColumnTruck() {
        return ColumnTruck;
    }

    public void setColumnTruck(TableColumn<table, String> columnTruck) {
        ColumnTruck = columnTruck;
    }

    public TableColumn<table, String> getColumnOrder() {
        return ColumnOrder;
    }

    public void setColumnOrder(TableColumn<table, String> columnOrder) {
        ColumnOrder = columnOrder;
    }

    public TableColumn<table, String> getColumnVk() {
        return ColumnVk;
    }

    public void setColumnVk(TableColumn<table, String> columnVk) {
        ColumnVk = columnVk;
    }

    public TableColumn<table, String> getColumnEk() {
        return ColumnEk;
    }

    public void setColumnEk(TableColumn<table, String> columnEk) {
        ColumnEk = columnEk;
    }

    public TableColumn<table, String> getColumnDoc() {
        return ColumnDoc;
    }

    public void setColumnDoc(TableColumn<table, String> columnDoc) {
        ColumnDoc = columnDoc;
    }

    public ObservableList<table> getData() {
        return data;
    }

    public void setData(ObservableList<table> data) {
        this.data = data;
    }
}