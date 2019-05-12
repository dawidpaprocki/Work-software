package fxControllers.alltruck;

import crud.model.AllTruck;
import crud.services.interfaces.AllTablesUpdateRecordService;
import crud.services.interfaces.AllTruckService;
import crud.services.interfaces.MaterialService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.stereotype.Controller;
import tools.PropertiesReader;

import java.util.List;
import java.util.Optional;

@Controller
public class TableController {


    /**
     * <h1> Table Controller Class</h1>
     *
     * Class with multiple responsibilities:
     * - Contain Method for making live change on Table by {@link #doChange(TableColumn.CellEditEvent)}
     * - Contain Method for refreshing Table by {@link #refresh(ActionEvent)}
     * - Contain Method for making background colorColumn on row by {@link #color(ActionEvent)}}
     * refresh - crud.fxControllers.alltruck refreshing button
     * colorChoice - Simple colorColumn picker to get colorColumn value
     *
     *
     */

    @FXML
    public Button refresh;
    @FXML
    public ColorPicker colorChoice;

    @FXML
    private TableView<AllTruck> tables;

    @FXML
    public TableColumn<AllTruck, Integer> idColumn;
    @FXML
    private TableColumn<AllTruck, String> dateColumn;

    @FXML
    private TableColumn<AllTruck, String> materialColumn;

    @FXML
    private TableColumn<AllTruck, String> truckNumberColumn;

    @FXML
    private TableColumn<AllTruck, Integer> amountColumn;

    @FXML
    private TableColumn<AllTruck, Integer> finalAmountColumn;

    @FXML
    private TableColumn<AllTruck, String> customerColumn;

    @FXML
    private TableColumn<AllTruck, String> truckNrColumn;

    @FXML
    private TableColumn<AllTruck, String> transportOrderColumn;

    @FXML
    private TableColumn<AllTruck, String> contractNumberColumn;

    @FXML
    private TableColumn<AllTruck, String> documentNameColumn;

    @FXML
    private TableColumn<AllTruck, String> colorColumn;

    private ObservableList<AllTruck> getData;


    /**
     * <h2> initialize Method</h2>
     * Method started with program start.
     * filling crud.fxControllers.alltruck View by Data from Data Base
     * setting background colorColumn based on Value in Data Base.
     */
    private AllTruckService allTruckService;
    private AllTablesUpdateRecordService allTablesUpdateRecordService;
    private MaterialService materialService;
    private PropertiesReader propertiesFile;

    public TableController(AllTruckService allTruckService, AllTablesUpdateRecordService allTablesUpdateRecordService, MaterialService materialService, PropertiesReader propertiesFile) {
        this.allTruckService = allTruckService;
        this.allTablesUpdateRecordService = allTablesUpdateRecordService;
        this.materialService = materialService;
        this.propertiesFile = propertiesFile;
    }

    public void initialize() {

        List<AllTruck> list = allTruckService.selectList();
        getData = FXCollections.observableArrayList();

        getData.addAll(list);

        getData.forEach(item -> {
            if(Optional.ofNullable(item.getContractsOpenBuy()).isPresent()){
                contractNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContractsOpenBuy().getContractName()));
            }else {
                contractNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContractsOpenSell().getContractName()));
            }
        });

        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        materialColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaterial().getName()));
        truckNumberColumn.setCellValueFactory(new PropertyValueFactory<>("TruckNumber"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        finalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        customerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer().getName()));
        truckNrColumn.setCellValueFactory(new PropertyValueFactory<>("TruckNr"));
        transportOrderColumn.setCellValueFactory(new PropertyValueFactory<>("TransportOrder"));
        documentNameColumn.setCellValueFactory(new PropertyValueFactory<>("DocumentName"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("Color"));
        tables.setItems(null);
        tables.setItems(getData);

        tables.setRowFactory(row -> new TableRow<AllTruck>() {
            @Override
            public void updateItem(AllTruck item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setStyle("");
                } else {
                    setStyle("-fx-control-inner-background: '" + item.getColor() + "' ;" +
                            "  -fx-inner-border-color:  '" + item.getColor() + "';" +
                            " -fx-table-cell-border-color:'" + item.getColor() + "' ;" +
                            "   -fx-border-width: 0px;");
                }
            }
        });

        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        truckNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        finalAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        customerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        truckNrColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        transportOrderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        documentNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        colorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    /**
     * <h2> doChange Method </h2>
     * <p>
     * Getting parameter of wanted cell to Edit.
     * Updating cell by inserting new value
     * Updating Data Base with new value.
     */

    public void doChange(TableColumn.CellEditEvent<AllTruck, String> tableStringCellEditEvent) {
        String newValue = String.valueOf(tableStringCellEditEvent.getNewValue());
        Long idOfRow = tableStringCellEditEvent.getRowValue().getId();
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        allTablesUpdateRecordService.updateRecord(AllTruck.class,idOfColumn,newValue,idOfRow);
    }

    public void refresh(ActionEvent actionEvent) {

    }

    /**
     * <h2> colorColumn Method </h2>
     * <p>
     * Changing value of default colorColumn by getting new value form {@link #colorChoice}
     * Updating in Data Base value of new colorColumn.
     */
    public void color(ActionEvent actionEvent) {
        String newCellColor = "#" + String.valueOf(colorChoice.getValue()).substring(2);
        Long colorRowId = tables.getSelectionModel().getSelectedItem().getId();

        TablePosition tablePosition = tables.getFocusModel().getFocusedCell();
        allTablesUpdateRecordService.updateRecord(AllTruck.class,"color", newCellColor, colorRowId);

        TableColor TableColor = new TableColor();

        tables = TableColor.color(tables, tablePosition, newCellColor);
        tables.refresh();
    }


}
