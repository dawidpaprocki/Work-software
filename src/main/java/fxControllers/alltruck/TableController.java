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

import java.util.List;

@Controller
public class TableController {


    /**
     * <h1> Table Controller Class</h1>
     *
     * Class with multiple responsibilities:
     * - Contain Method for making live change on Table by {@link #doChange(TableColumn.CellEditEvent)}
     * - Contain Method for refreshing Table by {@link #refresh(ActionEvent)}
     * - Contain Method for making background columnColor on row by {@link #color(ActionEvent)}}
     * refresh - crud.fxControllers.alltruck refreshing button
     * colorChoice - Simple columnColor picker to get columnColor value
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
    public TableColumn<AllTruck, Integer> columnId;
    @FXML
    private TableColumn<AllTruck, String> columnDate;

    @FXML
    private TableColumn<AllTruck, String> columnMaterial;

    @FXML
    private TableColumn<AllTruck, String> columnTruckNumber;

    @FXML
    private TableColumn<AllTruck, Integer> columnAmount;

    @FXML
    private TableColumn<AllTruck, Integer> columnFinalAmount;

    @FXML
    private TableColumn<AllTruck, String> columnSeller;

    @FXML
    private TableColumn<AllTruck, String> columnBuyer;

    @FXML
    private TableColumn<AllTruck, String> columnTruckNr;

    @FXML
    private TableColumn<AllTruck, String> columnTransportOrder;

    @FXML
    private TableColumn<AllTruck, String> columnSalesContractNumber;

    @FXML
    private TableColumn<AllTruck, String> columnPurchaseContractNumber;

    @FXML
    private TableColumn<AllTruck, String> columnDocumentName;

    @FXML
    private TableColumn<AllTruck, String> columnColor;

    private ObservableList<AllTruck> getData;


    /**
     * <h2> initialize Method</h2>
     * Method started with program start.
     * filling crud.fxControllers.alltruck View by Data from Data Base
     * setting background columnColor based on Value in Data Base.
     */
    private AllTruckService allTruckService;
    private AllTablesUpdateRecordService allTablesUpdateRecordService;
    private MaterialService materialService;

    public TableController(AllTruckService allTruckService, AllTablesUpdateRecordService allTablesUpdateRecordService, MaterialService materialService) {
        this.allTruckService = allTruckService;
        this.allTablesUpdateRecordService = allTablesUpdateRecordService;
        this.materialService = materialService;
    }

    public void initialize() {

        List<AllTruck> list = allTruckService.selectList();
        getData = FXCollections.observableArrayList();

        getData.addAll(list);

        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        columnMaterial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaterial().getName()));
        columnTruckNumber.setCellValueFactory(new PropertyValueFactory<>("TruckNumber"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        columnFinalAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        columnSeller.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSeller().getName()));
        columnBuyer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBuyer().getName()));
        columnTruckNr.setCellValueFactory(new PropertyValueFactory<>("TruckNr"));
        columnTransportOrder.setCellValueFactory(new PropertyValueFactory<>("TransportOrder"));
        columnSalesContractNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContractsOpenSell().getContractName()));
        columnPurchaseContractNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContractsOpenBuy().getContractName()));
        columnDocumentName.setCellValueFactory(new PropertyValueFactory<>("DocumentName"));
        columnColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
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

        columnDate.setCellFactory(TextFieldTableCell.forTableColumn());
        columnTruckNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnFinalAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnSeller.setCellFactory(TextFieldTableCell.forTableColumn());
        columnBuyer.setCellFactory(TextFieldTableCell.forTableColumn());
        columnTruckNr.setCellFactory(TextFieldTableCell.forTableColumn());
        columnTransportOrder.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDocumentName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnColor.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    /**
     * <h2> doChange Method </h2>
     * <p>
     * Getting parameter of wanted cell to Edit.
     * Updating cell by inserting new value
     * Updating Data Base with new value.
     */

    public void doChange(TableColumn.CellEditEvent<AllTruck, String> tableStringCellEditEvent) {
        String newValue = tableStringCellEditEvent.getNewValue();
        Long idOfRow = tableStringCellEditEvent.getRowValue().getId();
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        allTablesUpdateRecordService.updateRecord(AllTruck.class,idOfColumn,newValue,idOfRow);
    }

    public void refresh(ActionEvent actionEvent) {

    }

    /**
     * <h2> columnColor Method </h2>
     * <p>
     * Changing value of default columnColor by getting new value form {@link #colorChoice}
     * Updating in Data Base value of new columnColor.
     */
    public void color(ActionEvent actionEvent) {
        String newCellColor = "#" + String.valueOf(colorChoice.getValue()).substring(2);
        Long colorRowId = tables.getSelectionModel().getSelectedItem().getId();

        TablePosition tablePosition = tables.getFocusModel().getFocusedCell();
        allTablesUpdateRecordService.updateRecord(AllTruck.class,"columnColor", newCellColor, colorRowId);

        TableColor TableColor = new TableColor();

        tables = TableColor.color(tables, tablePosition, newCellColor);
        tables.refresh();
    }


}
