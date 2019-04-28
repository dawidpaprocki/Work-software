package fxControllers.pbContracts;

import crud.model.ContractsOpenBuy;
import crud.services.interfaces.ContractsOpenService;
import enums.MaterialTypes;
import javafx.beans.property.SimpleStringProperty;
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
public class OpenPbContractControllerBuy {

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<ContractsOpenBuy> openPbContractTable;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnCustomerName;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnMaterialName;

    @FXML
    private TableColumn<ContractsOpenBuy, Integer> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenBuy, Integer> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenBuy, Integer> columnAmount;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnContractName;


    private ObservableList<ContractsOpenBuy> data;
    private ContractsOpenService<ContractsOpenBuy> contractsOpenService;

    public OpenPbContractControllerBuy(ContractsOpenService<ContractsOpenBuy> contractsOpenService) {
        this.contractsOpenService = contractsOpenService;
    }

    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenBuy> contractsOpenBuys = contractsOpenService.selectList()
                .stream()
                .filter(e -> e.getMaterial().getId()
                        .equals(MaterialTypes.LEAD.getId()))
                .collect(Collectors.toList());

        data.setAll(contractsOpenBuys);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnMaterialName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaterial().getName()));
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


    public void doChange(TableColumn.CellEditEvent<ContractsOpenBuy, String> tableStringCellEditEvent) {

        String newValue = tableStringCellEditEvent.getNewValue();
        Long idOfRow = tableStringCellEditEvent.getRowValue().getId();
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        idOfColumn = idOfColumn.substring(6);

//        contractsOpenService.updateRecord(idOfColumn, newValue, idOfRow);

    }


}
