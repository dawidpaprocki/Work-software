package fxControllers.pbContracts;

import crud.controller.controllers.DaoContractsOpenSellController;
import crud.controller.controllers.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenSell;
import entity.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
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
    private TableColumn<ContractsOpenSell, String> columnIdName;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnAmount;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnContractName;


    private ObservableList<ContractsOpenSell> data;
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDao = new GenericDaoImpl(entityManager, ContractsOpenSell.class);
    private DaoContractsOpenSellController daoAllViewController =  DaoContractsOpenSellController.builder().dao(genericDao).build();

    private final EntityManager entityManagerMaterial = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDaoMaterial = new GenericDaoImpl(entityManagerMaterial, Material.class);
    private DaoMaterialController daoAllViewControllerMaterial =  DaoMaterialController.builder().dao(genericDaoMaterial).build();

    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenSell> contractsOpenSells = daoAllViewController
                .selectList()
                .stream()
                .filter(e->e.getIdName()
                        .equals(daoAllViewControllerMaterial.findById(2)    .getName()))
                .collect(Collectors.toList());;

        data.setAll(contractsOpenSells);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnIdName.setCellValueFactory(new PropertyValueFactory<>("idName"));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));

        openPbContractTable.setItems(null);
        openPbContractTable.setItems(data);


    }



}
