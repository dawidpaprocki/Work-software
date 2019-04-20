package fxControllers.pbContracts;

import crud.services.ContractsOpenService;
import crud.services.MaterialService;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;
@Controller
public class OpenPbContractControllerSell {

    @FXML
    private Button refreshButton;
    @FXML
    private TableView<ContractsOpenSell> openPbContractTable;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnCustomerName;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnMaterialName;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenBuy, Integer> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenBuy, Integer> columnAmount;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnContractName;


    private ObservableList<ContractsOpenSell> data;
    private ContractsOpenService<ContractsOpenSell> contractsOpenService;
    private MaterialService materialService;

    public OpenPbContractControllerSell(ContractsOpenService<ContractsOpenSell> contractsOpenService, MaterialService materialService) {
        this.contractsOpenService = contractsOpenService;
        this.materialService = materialService;
    }
    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenSell> contractsOpenSells = contractsOpenService
                .selectList()
                .stream()
                .filter(e->e.getMaterialName()
                        .equals(materialService.findById(2L).getName()))
                .collect(Collectors.toList());;

        data.setAll(contractsOpenSells);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnMaterialName.setCellValueFactory(new PropertyValueFactory<>("MaterialName"));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));

        openPbContractTable.setItems(null);
        openPbContractTable.setItems(data);

        columnAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnNrTruck.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnContractName.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    public void doChange(TableColumn.CellEditEvent<ContractsOpenSell, String> tableStringCellEditEvent) {
//
//        String newValue = tableStringCellEditEvent.getNewValue();
//
//        int idOfRow = tableStringCellEditEvent.getRowValue().getId();
//
//        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
//        idOfColumn = idOfColumn.substring(6);
//
//        contractsOpenService.updateRecord(idOfColumn,newValue,idOfRow);


    }


}
