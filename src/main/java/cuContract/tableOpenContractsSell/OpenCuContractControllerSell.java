package cuContract.tableOpenContractsSell;

import crud.controller.controllers.DaoAllViewController;
import crud.controller.controllers.DaoContractsOpenSellController;
import crud.model.GenericDaoImpl;
import entity.AllView;
import entity.ContractsOpenSell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import cuContract.SelectTable;
import cuContract.tableOpenContracts.Table;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class OpenCuContractControllerSell {


    @FXML
    private TableView<ContractsOpenSell> openCuContactTable;

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
    private DaoContractsOpenSellController daoAllViewController = new DaoContractsOpenSellController(genericDao);

    public void initialize() {
        data = FXCollections.observableArrayList();
        List<ContractsOpenSell> contractsOpenSells = daoAllViewController.selectList();
        data.setAll(contractsOpenSells);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnIdName.setCellValueFactory(new PropertyValueFactory<>("idName"));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));

        openCuContactTable.setItems(null);
        openCuContactTable.setItems(data);


    }
//    public void refresh(){
//        openCuContactTable.refresh();
//    }


}
