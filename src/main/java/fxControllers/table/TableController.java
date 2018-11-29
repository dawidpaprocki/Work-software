package fxControllers.table;

import crud.controller.controllers.DaoAllViewController;
import crud.model.GenericDaoImpl;
import entity.AllView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;


import javax.persistence.EntityManager;
import java.util.List;

public class TableController {


//    /**
//     * <h1> Table Controller Class</h1>
//     *
//     * Class with multiple responsibilities:
//     * - Filling crud.fxControllers.table in JavaFX by {@link Table}
//     * - Contain Method for making live change on Table by {@link #doChange(TableColumn.CellEditEvent)}
//     * - Contain Method for refreshing Table by {@link #refresh(ActionEvent)}
//     * - Contain Method for making background color on row by {@link #color(ActionEvent)}
//     * refresh - crud.fxControllers.table refreshing button
//     * colorChoice - Simple color picker to get color value
//     *
//     *
//     */

    @FXML
    public Button refresh;
    @FXML
    public ColorPicker colorChoice;
    @FXML
    private TableView<AllView> tables;

    @FXML
    public TableColumn<AllView, Integer> id;
    @FXML
    private TableColumn<AllView, String> data;

    @FXML
    private TableColumn<AllView, String> material;

    @FXML
    private TableColumn<AllView, String> truck;

    @FXML
    private TableColumn<AllView, Integer> amount;

    @FXML
    private TableColumn<AllView, Integer> finalAmount;

    @FXML
    private TableColumn<AllView, String> froms;

    @FXML
    private TableColumn<AllView, String> tos;

    @FXML
    private TableColumn<AllView, String> truckNr;

    @FXML
    private TableColumn<AllView, String> TransportOrder;

    @FXML
    private TableColumn<AllView, String> vk;

    @FXML
    private TableColumn<AllView, String> ek;

    @FXML
    private TableColumn<AllView, String> amsDoc;

    @FXML
    private TableColumn<AllView, String> color;

    private ObservableList<AllView> getData;


    /**
     * <h2> initialize Method</h2>
     * Method started with program start.
     * filling crud.fxControllers.table View by Data from Data Base
     * setting background color based on Value in Data Base.
     *
     */
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDao = new GenericDaoImpl(entityManager, AllView.class);
    private DaoAllViewController daoAllViewController =  DaoAllViewController.builder().dao(genericDao).build();

    public void initialize() {





        List<AllView> list = daoAllViewController.selectList();


        getData = FXCollections.observableArrayList();


        getData.addAll(list);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        material.setCellValueFactory(new PropertyValueFactory<>("material"));
        truck.setCellValueFactory(new PropertyValueFactory<>("truck"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        finalAmount.setCellValueFactory(new PropertyValueFactory<>("finalAmount"));
        froms.setCellValueFactory(new PropertyValueFactory<>("froms"));
        tos.setCellValueFactory(new PropertyValueFactory<>("tos"));
        truckNr.setCellValueFactory(new PropertyValueFactory<>("truckNr"));
        TransportOrder.setCellValueFactory(new PropertyValueFactory<>("TransportOrder"));
        vk.setCellValueFactory(new PropertyValueFactory<>("vk"));
        ek.setCellValueFactory(new PropertyValueFactory<>("ek"));
        amsDoc.setCellValueFactory(new PropertyValueFactory<>("amsDoc"));
        color.setCellValueFactory(new PropertyValueFactory<>("amsDoc"));

        tables.setItems(null);
        tables.setItems(getData);

        tables.setRowFactory(row -> new TableRow<AllView>() {
            @Override
            public void updateItem(AllView item, boolean empty) {
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
        amount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        finalAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        froms.setCellFactory(TextFieldTableCell.forTableColumn());
        tos.setCellFactory(TextFieldTableCell.forTableColumn());
        truckNr.setCellFactory(TextFieldTableCell.forTableColumn());
        TransportOrder.setCellFactory(TextFieldTableCell.forTableColumn());
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

    public void doChange(TableColumn.CellEditEvent<AllView, String> tableStringCellEditEvent) {

        //get new value of cell
        String newMaterial = tableStringCellEditEvent.getNewValue();
        //get id of changing row
        int idOfRow = tableStringCellEditEvent.getRowValue().getId();
        //get id of column
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        //updating changes to getData base
        daoAllViewController.updateRecord(idOfColumn,newMaterial,idOfRow);


    }

    public void refresh(ActionEvent actionEvent) {

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



        int colorId = tables.getSelectionModel().getSelectedItem().getId();

        TablePosition tablePosition;
        tablePosition = tables.getFocusModel().getFocusedCell();



        daoAllViewController.updateRecord("color",temporaryColor,colorId);

        TableColor TableColor = new TableColor();

        tables = TableColor.color(tables, tablePosition, temporaryColor);
        tables.refresh();

    }


}
