package table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import combo.EditTable;

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

    private ObservableList<table> data;

    public void initialize(){

        data = FXCollections.observableArrayList();

        SelectTable selectAll = new SelectTable();
        SelectTable.SelectAll("All_view",data,"*");

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

        tables.setItems(null);
        tables.setItems(selectAll.getData());

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

    }
    private EditTable editTable;
    private String color="";

    public void doChange(TableColumn.CellEditEvent<table,String> tableStringCellEditEvent) {

        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        String idOfRow = tableStringCellEditEvent.getRowValue().Id();
        //get id of column
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        //updating changes to data base
        editTable = new EditTable(newMaterial,idOfRow,idOfColumn);


    }

    public void refresh(ActionEvent actionEvent) {
        SelectTable.SelectAll("All_view",data,"*");
    }

    public void color(ActionEvent actionEvent) {


        color = String.valueOf(colorChoice.getValue());
        System.out.println(color);

    }
}
