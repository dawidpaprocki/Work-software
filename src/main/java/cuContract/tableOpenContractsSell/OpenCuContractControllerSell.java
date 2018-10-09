package cuContract.tableOpenContractsSell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import cuContract.SelectTable;
import cuContract.tableOpenContracts.table;

public class OpenCuContractControllerSell {


    @FXML
    private TableView <table> OpenCuContactTable;

    @FXML
    private TableColumn<table, String> ColumnCustomerName;

    @FXML
    private TableColumn<table, String> ColumnIdName;

    @FXML
    private TableColumn<table, String> ColumnNrTruckContract;

    @FXML
    private TableColumn<table, String> ColumnNrTruck;

    @FXML
    private TableColumn<table, String> ColumnAmount;

    @FXML
    private TableColumn<table, String> ColumnContractName;

    private ObservableList<table> data;

    public void initialize(){
        data = FXCollections.observableArrayList();
        SelectTable selectTable = new SelectTable();
        SelectTable.SelectAll("contractsopensell",data,"CustomerName,idName,NrTruckContract,NrTruck,Amount,ContractName");


        ColumnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        ColumnIdName.setCellValueFactory(new PropertyValueFactory<>("idName"));
        ColumnNrTruck.setCellValueFactory(new PropertyValueFactory<>("NrTruck"));
        ColumnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("NrTruckContract"));
        ColumnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        ColumnContractName.setCellValueFactory(new PropertyValueFactory<>("ContractName"));
        OpenCuContactTable.setItems(null);
        OpenCuContactTable.setItems(selectTable.getData());





    }


}
