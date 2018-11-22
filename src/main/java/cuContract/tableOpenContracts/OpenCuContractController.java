package cuContract.tableOpenContracts;

import crud.controller.controllers.DaoContractsOpenBuyController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenBuy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.List;


public class OpenCuContractController {


    @FXML
    private TableView<ContractsOpenBuy> openCuContactTable;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnCustomerName;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnIdName;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnAmount;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnContractName;


    private ObservableList<ContractsOpenBuy> data;

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDao = new GenericDaoImpl(entityManager, ContractsOpenBuy.class);
    private DaoContractsOpenBuyController daoContractsOpenBuyController = new DaoContractsOpenBuyController(genericDao);

    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenBuy> contractsOpenBuys = daoContractsOpenBuyController.selectList();
        data.setAll(contractsOpenBuys);

//        SelectTable selectTable = new SelectTable();
//        SelectTable.SelectAll("contractsopenbuy", data, "CustomerName,idName,nrTruckContract,nrTruck,amount,contractName");
//
//        TableRow<Table> row = new TableRow<>();
//        row.setStyle("-fx-background-color:red;");
//        openCuContactTable.setRowFactory(row.);


        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnIdName.setCellValueFactory(new PropertyValueFactory<>("idName"));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        openCuContactTable.setItems(null);
        openCuContactTable.setItems(data);

        openCuContactTable.setRowFactory(row -> new TableRow<ContractsOpenBuy>() {
            @Override
            public void updateItem(ContractsOpenBuy item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else {



                    // have to be added function color based on table row with color data lik in All_view


                    if (item.getCustomerName().equals("Ameba")) {
                        setStyle("-fx-background-color: red;");

                    } else {
                        setStyle("");
                    }
                }
            }
        });


    }

    public void refresh(){

    }


}
