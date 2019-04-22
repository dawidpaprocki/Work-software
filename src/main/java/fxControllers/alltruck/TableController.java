package fxControllers.alltruck;

import crud.services.AllTablesUpdateRecordService;
import crud.services.AllTruckService;
import crud.model.AllTruck;
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


//    /**
//     * <h1> Table Controller Class</h1>
//     *
//     * Class with multiple responsibilities:
//     * - Filling crud.fxControllers.alltruck in JavaFX by {@link Table}
//     * - Contain Method for making live change on Table by {@link #doChange(TableColumn.CellEditEvent)}
//     * - Contain Method for refreshing Table by {@link #refresh(ActionEvent)}
//     * - Contain Method for making background color on row by {@link #color(ActionEvent)}
//     * refresh - crud.fxControllers.alltruck refreshing button
//     * colorChoice - Simple color picker to get color value
//     *
//     *
//     */

    @FXML
    public Button refresh;
    @FXML
    public ColorPicker colorChoice;

    @FXML
    private TableView<AllTruck> tables;

    @FXML
    public TableColumn<AllTruck, Integer> id;
    @FXML
    private TableColumn<AllTruck, String> date;

    @FXML
    private TableColumn<AllTruck, String> material;

    @FXML
    private TableColumn<AllTruck, String> truckNumber;

    @FXML
    private TableColumn<AllTruck, Integer> amount;

    @FXML
    private TableColumn<AllTruck, Integer> finalAmount;

    @FXML
    private TableColumn<AllTruck, String> seller;

    @FXML
    private TableColumn<AllTruck, String> buyer;

    @FXML
    private TableColumn<AllTruck, String> truckNr;

    @FXML
    private TableColumn<AllTruck, String> transportOrder;

    @FXML
    private TableColumn<AllTruck, String> salesContractNumber;

    @FXML
    private TableColumn<AllTruck, String> purchaseContractNumber;

    @FXML
    private TableColumn<AllTruck, String> documentName;

    @FXML
    private TableColumn<AllTruck, String> color;

    private ObservableList<AllTruck> getData;


    /**
     * <h2> initialize Method</h2>
     * Method started with program start.
     * filling crud.fxControllers.alltruck View by Data from Data Base
     * setting background color based on Value in Data Base.
     */
    private AllTruckService allTruckService;
    private AllTablesUpdateRecordService allTablesUpdateRecordService;

    public TableController(AllTruckService allTruckService, AllTablesUpdateRecordService allTablesUpdateRecordService) {
        this.allTruckService = allTruckService;
        this.allTablesUpdateRecordService = allTablesUpdateRecordService;
    }

    public void initialize() {

        List<AllTruck> list = allTruckService.selectList();

        getData = FXCollections.observableArrayList();

        getData.addAll(list);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        material.setCellValueFactory(new PropertyValueFactory<>("material"));
        truckNumber.setCellValueFactory(new PropertyValueFactory<>("truckNumber"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        finalAmount.setCellValueFactory(new PropertyValueFactory<>("finalAmount"));
        seller.setCellValueFactory(new PropertyValueFactory<>("seller"));
        buyer.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        truckNr.setCellValueFactory(new PropertyValueFactory<>("truckNr"));
        transportOrder.setCellValueFactory(new PropertyValueFactory<>("transportOrder"));
        salesContractNumber.setCellValueFactory(new PropertyValueFactory<>("salesContractNumber"));
        purchaseContractNumber.setCellValueFactory(new PropertyValueFactory<>("purchaseContractNumber"));
        documentName.setCellValueFactory(new PropertyValueFactory<>("documentName"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));

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

        material.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        truckNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        amount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        finalAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        seller.setCellFactory(TextFieldTableCell.forTableColumn());
        buyer.setCellFactory(TextFieldTableCell.forTableColumn());
        truckNr.setCellFactory(TextFieldTableCell.forTableColumn());
        transportOrder.setCellFactory(TextFieldTableCell.forTableColumn());
        documentName.setCellFactory(TextFieldTableCell.forTableColumn());
        color.setCellFactory(TextFieldTableCell.forTableColumn());
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
     * <h2> color Method </h2>
     * <p>
     * Changing value of default color by getting new value form {@link #colorChoice}
     * Updating in Data Base value of new color.
     */
    public void color(ActionEvent actionEvent) {
        String newCellColor = "#" + String.valueOf(colorChoice.getValue()).substring(2);
        Long colorRowId = tables.getSelectionModel().getSelectedItem().getId();

        TablePosition tablePosition;
        tablePosition = tables.getFocusModel().getFocusedCell();

        allTablesUpdateRecordService.updateRecord(AllTruck.class,"color", newCellColor, colorRowId);

        TableColor TableColor = new TableColor();

        tables = TableColor.color(tables, tablePosition, newCellColor);
        tables.refresh();
    }


}
