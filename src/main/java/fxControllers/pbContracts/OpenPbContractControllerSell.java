package fxControllers.pbContracts;

import crud.controller.DaoContractsOpenSellController;
import crud.controller.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenBuy;
import entity.ContractsOpenSell;
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
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class OpenPbContractControllerSell {

    @FXML
    private Button refreshButton;
    @FXML
    private TableView<ContractsOpenSell> openPbContractTable;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnCustomerName;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnMaterialName;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenBuy, Integer> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenBuy, Integer> columnAmount;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnContractName;


    private ObservableList<ContractsOpenSell> data;
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDao = new GenericDaoImpl(entityManager, ContractsOpenSell.class);
    private DaoContractsOpenSellController daoContractsOpenSellController =  DaoContractsOpenSellController.builder().dao(genericDao).build();

    private final EntityManager entityManagerMaterial = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDaoMaterial = new GenericDaoImpl(entityManagerMaterial, Material.class);
    private DaoMaterialController daoMaterialController =  DaoMaterialController.builder().dao(genericDaoMaterial).build();

    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenSell> contractsOpenSells = daoContractsOpenSellController
                .selectList()
                .stream()
                .filter(e->e.getMaterialName()
                        .equals(daoMaterialController.findById(2)    .getName()))
                .collect(Collectors.toList());;

        data.setAll(contractsOpenSells);

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
        HashSet hashSet = new HashSet();
    }


    public void doChange(TableColumn.CellEditEvent<ContractsOpenSell, String> tableStringCellEditEvent) {

        String newValue = tableStringCellEditEvent.getNewValue();

        int idOfRow = tableStringCellEditEvent.getRowValue().getId();

        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        idOfColumn = idOfColumn.substring(6);

        daoContractsOpenSellController.updateRecord(idOfColumn,newValue,idOfRow);


    }


}
