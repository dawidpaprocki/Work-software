package fxControllers.pbContracts;

import crud.controller.DaoContractsOpenBuyController;
import crud.controller.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenBuy;
import entity.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


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

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDao = new GenericDaoImpl(entityManager, ContractsOpenBuy.class);
    private DaoContractsOpenBuyController daoContractsOpenBuyController = DaoContractsOpenBuyController.builder().dao(genericDao).build();


    private final EntityManager entityManagerMaterial = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDaoMaterial = new GenericDaoImpl(entityManagerMaterial, Material.class);
    private DaoMaterialController daoMaterialController = DaoMaterialController.builder().dao(genericDaoMaterial).build();

    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenBuy> contractsOpenBuys = daoContractsOpenBuyController
                .selectList()
                .stream()
                .filter(e -> e.getMaterialName()
                        .equals(daoMaterialController.findById(2).getName()))
                .collect(Collectors.toList());

        data.setAll(contractsOpenBuys);

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


    public void doChange(TableColumn.CellEditEvent<ContractsOpenBuy, String> tableStringCellEditEvent) {

        String newValue = tableStringCellEditEvent.getNewValue();
        int idOfRow = tableStringCellEditEvent.getRowValue().getId();
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        idOfColumn = idOfColumn.substring(6);

        daoContractsOpenBuyController.updateRecord(idOfColumn, newValue, idOfRow);

    }


}
