package cuContract.tableOpenContracts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import cuContract.SelectTable;


public class OpenCuContractController {


    @FXML
    private TableView <Table> openCuContactTable;

    @FXML
    private TableColumn<Table, String> columnCustomerName;

    @FXML
    private TableColumn<Table, String> columnIdName;

    @FXML
    private TableColumn<Table, String> columnNrTruckContract;

    @FXML
    private TableColumn<Table, String> columnNrTruck;

    @FXML
    private TableColumn<Table, String> columnAmount;

    @FXML
    private TableColumn<Table, String> columnContractName;

    private ObservableList<Table> data;

    public void initialize(){
        data = FXCollections.observableArrayList();
        SelectTable selectTable = new SelectTable();
        SelectTable.SelectAll("contractsopenbuy",data,"CustomerName,idName,nrTruckContract,nrTruck,amount,contractName");
//
//        TableRow<Table> row = new TableRow<>();
//        row.setStyle("-fx-background-color:red;");
//        openCuContactTable.setRowFactory(row.);




        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnIdName.setCellValueFactory(new PropertyValueFactory<>("idName"));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        openCuContactTable.setItems(null);
        openCuContactTable.setItems(selectTable.getData());

        openCuContactTable.setRowFactory(row -> new TableRow<Table>() {
            @Override
            public void updateItem(Table item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else {

                    if (item.getCustomerName().equals("Ameba")) {
                        setStyle("-fx-background-color: red;");

                    } else {
                        setStyle("");
                    }
                }
            }
        });



    }


}
