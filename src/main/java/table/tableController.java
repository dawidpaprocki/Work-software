package table;

import com.mysql.cj.xdevapi.RowFactory;
import combo.EditTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import javax.sql.rowset.RowSetFactory;

public class tableController {

    @FXML
    public Button refresh;
    @FXML
    public ColorPicker colorChoice;
    @FXML
    private TableView<table> tables;

    @FXML
    public TableColumn<table, String> Id;
    @FXML
    private TableColumn<table, String> Data;

    @FXML
    private TableColumn<table, String> Material;

    @FXML
    private TableColumn<table, String> Truck;

    @FXML
    private TableColumn<table, String> Amount;

    @FXML
    private TableColumn<table, String> Final_Amount;

    @FXML
    private TableColumn<table, String> Froms;

    @FXML
    private TableColumn<table, String> Tos;

    @FXML
    private TableColumn<table, String> Truck_Nr;

    @FXML
    private TableColumn<table, String> Order;

    @FXML
    private TableColumn<table, String> Vk;

    @FXML
    private TableColumn<table, String> Ek;

    @FXML
    private TableColumn<table, String> Ams_doc;

    @FXML
    private TableColumn<table, String> Color;

    private ObservableList<table> data;
    //    boolean dateDownloaded = false;
    SelectTable selectAll = new SelectTable();

    public void initialize() {

        data = FXCollections.observableArrayList();
//        if(dateDownloaded == false) {

        SelectTable.SelectAll("All_view", data, "*");
//            dateDownloaded = true;
//        }

        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));

        Data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        Material.setCellValueFactory(new PropertyValueFactory<>("Material"));
        Truck.setCellValueFactory(new PropertyValueFactory<>("Truck"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Final_Amount.setCellValueFactory(new PropertyValueFactory<>("Final_Amount"));
        Froms.setCellValueFactory(new PropertyValueFactory<>("Froms"));
        Tos.setCellValueFactory(new PropertyValueFactory<>("Tos"));
        Truck_Nr.setCellValueFactory(new PropertyValueFactory<>("Truck_Nr"));
        Order.setCellValueFactory(new PropertyValueFactory<>("Order"));
        Vk.setCellValueFactory(new PropertyValueFactory<>("Vk"));
        Ek.setCellValueFactory(new PropertyValueFactory<>("Ek"));
        Ams_doc.setCellValueFactory(new PropertyValueFactory<>("Ams_doc"));
        Color.setCellValueFactory(new PropertyValueFactory<>("Ams_doc"));

        tables.setItems(null);
        tables.setItems(selectAll.getData());

        tables.setRowFactory(row -> new TableRow<table>() {
            @Override
            public void updateItem(table item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setStyle("");
                } else {
                    setStyle("-fx-control-inner-background: '" + item.Color() + "' ;" +
                            "  -fx-inner-border-color:  '" + item.Color() + "';" +
                            " -fx-table-cell-border-color:'" + item.Color() + "' ;" +
                            "   -fx-border-width: 0px;");

                }
            }
        });


        Material.setCellFactory(TextFieldTableCell.forTableColumn());
        Data.setCellFactory(TextFieldTableCell.forTableColumn());
        Truck.setCellFactory(TextFieldTableCell.forTableColumn());
        Amount.setCellFactory(TextFieldTableCell.forTableColumn());
        Final_Amount.setCellFactory(TextFieldTableCell.forTableColumn());
        Froms.setCellFactory(TextFieldTableCell.forTableColumn());
        Tos.setCellFactory(TextFieldTableCell.forTableColumn());
        Truck_Nr.setCellFactory(TextFieldTableCell.forTableColumn());
        Order.setCellFactory(TextFieldTableCell.forTableColumn());
        Ams_doc.setCellFactory(TextFieldTableCell.forTableColumn());
        Color.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    private EditTable editTable;
    private String color = "#fd6c6cff";


    public void doChange(TableColumn.CellEditEvent<table, String> tableStringCellEditEvent) {

        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //get id of column
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        System.out.println(idOfColumn);
        //updating changes to data base
        editTable = new EditTable(newMaterial, idOfRow, idOfColumn);


    }

    public void refresh(ActionEvent actionEvent) {
        SelectTable.SelectAll("All_view", data, "*");
        System.out.println("działa");
    }

    public void color(ActionEvent actionEvent) {


        color = "#" + String.valueOf(colorChoice.getValue()).substring(2);
        System.out.println(color);

        String colorId = tables.getSelectionModel().getSelectedItem().Id();
        System.out.println(colorId);


//
//
        TablePosition tablePosition;
        tablePosition = tables.getFocusModel().getFocusedCell();

//
//        System.out.println(tablePosition);
//        System.out.println(tablePosition.getRow());
//        System.out.println(tablePosition.getTableColumn());
//


        editTable = new EditTable(color, colorId, "Color");

        tableColor tableColor = new tableColor();

        tables = tableColor.color(tables,tablePosition,color);
        tables.refresh();



    }


}
