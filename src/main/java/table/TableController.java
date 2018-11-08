package table;

import combo.DataOperationAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableController {


    /**
     * <h1> Table Controller Class</h1>
     *
     * Class with multiple responsibilities:
     * - Filling table in JavaFX by {@link Table}
     * - Contain Method for making live change on Table by {@link #doChange(TableColumn.CellEditEvent)}
     * - Contain Method for refreshing Table by {@link #refresh(ActionEvent)}
     * - Contain Method for making background color on row by {@link #color(ActionEvent)}
     * refresh - table refreshing button
     * colorChoice - Simple color picker to get color value
     *
     *
     */

    @FXML
    public Button refresh;
    @FXML
    public ColorPicker colorChoice;
    @FXML
    private TableView<Table> tables;

    @FXML
    public TableColumn<Table, String> id;
    @FXML
    private TableColumn<Table, String> data;

    @FXML
    private TableColumn<Table, String> material;

    @FXML
    private TableColumn<Table, String> truck;

    @FXML
    private TableColumn<Table, String> amount;

    @FXML
    private TableColumn<Table, String> finalAmount;

    @FXML
    private TableColumn<Table, String> froms;

    @FXML
    private TableColumn<Table, String> tos;

    @FXML
    private TableColumn<Table, String> truckNr;

    @FXML
    private TableColumn<Table, String> order;

    @FXML
    private TableColumn<Table, String> vk;

    @FXML
    private TableColumn<Table, String> ek;

    @FXML
    private TableColumn<Table, String> amsDoc;

    @FXML
    private TableColumn<Table, String> color;

    private ObservableList<Table> getData;

    private SelectTable selectAll = new SelectTable();

    /**
     * <h2> initialize Method</h2>
     * Method started with program start.
     * filling table View by Data from Data Base
     * setting background color based on Value in Data Base.
     *
     */

    public void initialize() {

        getData = FXCollections.observableArrayList();

        SelectTable.SelectAll("All_view", getData, "*");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        material.setCellValueFactory(new PropertyValueFactory<>("material"));
        truck.setCellValueFactory(new PropertyValueFactory<>("truck"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        finalAmount.setCellValueFactory(new PropertyValueFactory<>("finalAmount"));
        froms.setCellValueFactory(new PropertyValueFactory<>("froms"));
        tos.setCellValueFactory(new PropertyValueFactory<>("tos"));
        truckNr.setCellValueFactory(new PropertyValueFactory<>("truckNr"));
        order.setCellValueFactory(new PropertyValueFactory<>("order"));
        vk.setCellValueFactory(new PropertyValueFactory<>("vk"));
        ek.setCellValueFactory(new PropertyValueFactory<>("ek"));
        amsDoc.setCellValueFactory(new PropertyValueFactory<>("amsDoc"));
        color.setCellValueFactory(new PropertyValueFactory<>("amsDoc"));

        tables.setItems(null);
        tables.setItems(selectAll.getData());

        tables.setRowFactory(row -> new TableRow<Table>() {
            @Override
            public void updateItem(Table item, boolean empty) {
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
        data.setCellFactory(TextFieldTableCell.forTableColumn());
        truck.setCellFactory(TextFieldTableCell.forTableColumn());
        amount.setCellFactory(TextFieldTableCell.forTableColumn());
        finalAmount.setCellFactory(TextFieldTableCell.forTableColumn());
        froms.setCellFactory(TextFieldTableCell.forTableColumn());
        tos.setCellFactory(TextFieldTableCell.forTableColumn());
        truckNr.setCellFactory(TextFieldTableCell.forTableColumn());
        order.setCellFactory(TextFieldTableCell.forTableColumn());
        amsDoc.setCellFactory(TextFieldTableCell.forTableColumn());
        color.setCellFactory(TextFieldTableCell.forTableColumn());


    }




    /**
     * <h2> doChange Method </h2>
     *
     * Getting parameter of wanted cell to edit.
     * Updating cell by inserting new value
     * Updating Data Base with new value.
     *
     */
    //temporary color
    private String temporaryColor = "#fd6c6cff";

    public void doChange(TableColumn.CellEditEvent<Table, String> tableStringCellEditEvent) {

        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().getId();
        //get id of column
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        //updating changes to getData base
        new DataOperationAll("UPDATE all_view SET " + idOfColumn + "  =  '" + newMaterial + "' Where ID = '" + idOfRow + "'");

    }

    public void refresh(ActionEvent actionEvent) {
        SelectTable.SelectAll("All_view", getData, "*");
    }
    /**
     * <h2> color Method </h2>
     *
     * Changing value of default color by getting new value form {@link #colorChoice}
     * Updating in Data Base value of new color.
     *
     */
    public void color(ActionEvent actionEvent) {


        temporaryColor = "#" + String.valueOf(colorChoice.getValue()).substring(2);

        String colorId = tables.getSelectionModel().getSelectedItem().getId();

        TablePosition tablePosition;
        tablePosition = tables.getFocusModel().getFocusedCell();


        new DataOperationAll("UPDATE all_view SET " + "color" + "  =  '" + temporaryColor + "' Where ID = '" + colorId + "'");
        TableColor TableColor = new TableColor();

        tables = TableColor.color(tables, tablePosition, temporaryColor);
        tables.refresh();


    }


}
