package sample.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class tableController {

    @FXML
    private TableView<table> tables;

    @FXML
    private TableColumn<table, String> ColumnData;

    @FXML
    private TableColumn<table, String>  ColumnMaterial;

    @FXML
    private TableColumn<table, String> ColumnPlate;

    @FXML
    private TableColumn<table, String>  ColumnAmount;

    @FXML
    private TableColumn<table, String>  ColumnFinal_Amount;

    @FXML
    private TableColumn<table, String>  ColumnFroms;

    @FXML
    private TableColumn<table, String>  ColumnTos;

    @FXML
    private TableColumn<table, String>  ColumnTruck;

    @FXML
    private TableColumn<table, String>  ColumnOrder;

    @FXML
    private TableColumn<table, String> ColumnVk;

    @FXML
    private TableColumn<table, String>  ColumnEk;

    @FXML
    private TableColumn<table, String> ColumnDoc;

    private ObservableList<table> data;

    public void initialize(){

        data = FXCollections.observableArrayList();

        SelectTable selectAll = new SelectTable();
        SelectTable.SelectAll("All_view",data,"*");

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

}
