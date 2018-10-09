package cuContract.tableOpenContracts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import cuContract.SelectTable;


public class OpenCuContractController {


    @FXML
    private TableView <table> OpenCuContactTable;

    @FXML
    private TableColumn<table, String> ColumnCustomerName;

    @FXML
    private TableColumn<table, String> ColumnidName;

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
        SelectTable.SelectAll("contractsopenbuy",data,"CustomerName,idName,NrTruckContract,NrTruck,Amount,ContractName");
//
//        TableRow<table> row = new TableRow<>();
//        row.setStyle("-fx-background-color:red;");
//        OpenCuContactTable.setRowFactory(row.);




        ColumnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        ColumnidName.setCellValueFactory(new PropertyValueFactory<>("idName"));
        ColumnNrTruck.setCellValueFactory(new PropertyValueFactory<>("NrTruck"));
        ColumnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("NrTruckContract"));
        ColumnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        ColumnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        ColumnContractName.setCellValueFactory(new PropertyValueFactory<>("ContractName"));
        OpenCuContactTable.setItems(null);
        OpenCuContactTable.setItems(selectTable.getData());

        OpenCuContactTable.setRowFactory(row -> new TableRow<table>() {
            @Override
            public void updateItem(table item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else {

                    if (item.CustomerName().equals("Ameba")) {
                        setStyle("-fx-background-color: red;");

                    } else {
                        setStyle("");
                    }
                }
            }
        });



    }


}
