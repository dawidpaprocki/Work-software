package sample.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.combo.EditTable;

public class tableController {

    @FXML
    public Button refresh;
    @FXML
    public ColorPicker colorChoice;
    @FXML
    private TableView<table> tables;

    @FXML
    public TableColumn<table, String> ColumnId;
    @FXML
    private TableColumn<table, String> ColumnData;

    @FXML
    private TableColumn<table, String>  ColumnMaterial;

    @FXML
    private TableColumn<table, String> ColumnPlate;

    @FXML
    private TableColumn<table, String>  ColumnAmount;

    @FXML
    private TableColumn<table, String>  ColumnFinal_Amount;

    @FXML
    private TableColumn<table, String>  ColumnFroms;

    @FXML
    private TableColumn<table, String>  ColumnTos;

    @FXML
    private TableColumn<table, String>  ColumnTruck;

    @FXML
    private TableColumn<table, String>  ColumnOrder;

    @FXML
    private TableColumn<table, String> ColumnVk;

    @FXML
    private TableColumn<table, String>  ColumnEk;

    @FXML
    private TableColumn<table, String> ColumnDoc;

    private ObservableList<table> data;

    public void initialize(){

        data = FXCollections.observableArrayList();

        SelectTable selectAll = new SelectTable();
        SelectTable.SelectAll("All_view",data,"*");

        ColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        ColumnData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        ColumnMaterial.setCellValueFactory(new PropertyValueFactory<>("Material"));
        ColumnPlate.setCellValueFactory(new PropertyValueFactory<>("Plate"));
        ColumnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        ColumnFinal_Amount.setCellValueFactory(new PropertyValueFactory<>("Final_Amount"));
        ColumnFroms.setCellValueFactory(new PropertyValueFactory<>("Froms"));
        ColumnTos.setCellValueFactory(new PropertyValueFactory<>("Tos"));
        ColumnTruck.setCellValueFactory(new PropertyValueFactory<>("Truck"));
        ColumnOrder.setCellValueFactory(new PropertyValueFactory<>("Order"));
        ColumnVk.setCellValueFactory(new PropertyValueFactory<>("Vk"));
        ColumnEk.setCellValueFactory(new PropertyValueFactory<>("Ek"));
        ColumnDoc.setCellValueFactory(new PropertyValueFactory<>("Doc"));

        tables.setItems(null);
        tables.setItems(selectAll.getData());

        ColumnMaterial.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnData.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnPlate.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnAmount.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnFinal_Amount.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnFroms.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnTos.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnTruck.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnOrder.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnDoc.setCellFactory(TextFieldTableCell.forTableColumn());

    }
    EditTable editTable;
    String color="";

    public void materialChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base

        editTable = new EditTable(newMaterial,idOfRow,"Material");
    }

    public void dataChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,"data");

    }

    public void plateChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,"truck");
    }

    public void amountChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,"amount");
    }

    public void finalAmountChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,"final_amount");
    }

    public void fromsChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,"froms");
    }

    public void tosChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,"tos");
    }

    public void orderChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,"transport_order");
    }

    public void docChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {
        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,"Ams_doc");


    }

    public void refresh(ActionEvent actionEvent) {
        SelectTable.SelectAll("All_view",data,"*");
    }

    public void color(ActionEvent actionEvent) {


        color = String.valueOf(colorChoice.getValue());
        System.out.println(color);

    }
}
