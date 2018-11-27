package sale;

import combo.ComboCustomers;
import combo.DataOperationAll;
import combo.SelectListOfThings;
import combo.SelectOneThing;
import crud.controller.controllers.DaoContractsOpenBuyController;
import crud.controller.controllers.DaoContractsOpenSellController;
import crud.controller.controllers.DaoCustomerController;
import crud.controller.controllers.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.AllView;
import entity.ContractsOpenSell;
import entity.Customer;
import entity.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.List;


public class SaleContractController {
    /**
     *  <h1>The SaleContractController Class.</h1>
     *
     *  Controller for JavaFX panel responsible for adding sell contract to Data Base.
     *
     *  choiceContractSell - Choice Box where we choice name of company to make contract.
     *  nrSellContract - Text Field for set number of contract.
     *  amountContractSell - Text Field for set amount of sold material.
     *  choiceMaterialSellContract - Choice Box for select material.
     *  truckContractSell - Text Field for set amount of sold truck.
     *  materialList - temporary list made for fill  choiceMaterialSellContract.
     *
     */
    @FXML
    private ChoiceBox choiceContractSell;

    @FXML
    private TextField nrSellContract;

    @FXML
    private TextField amountContractSell;

    @FXML
    private TextField truckContractSell;

    @FXML
    private ChoiceBox choiceMaterialSellContract;

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManagerCustomer = sessionFactory.createEntityManager();
    private final EntityManager entityManagerMaterial = sessionFactory.createEntityManager();
    private final EntityManager entityManagerContractSell = sessionFactory.createEntityManager();

    private GenericDaoImpl genericDaoMaterial = new GenericDaoImpl(entityManagerMaterial, Material.class);
    private GenericDaoImpl genericDaoCustomer = new GenericDaoImpl(entityManagerCustomer, Customer.class);
    private GenericDaoImpl genericDaoContractSell = new GenericDaoImpl(entityManagerContractSell, ContractsOpenSell.class);

    DaoMaterialController daoMaterialController = new DaoMaterialController(genericDaoMaterial);
    DaoCustomerController daoCustomerController =  DaoCustomerController.builder()
            .dao(genericDaoCustomer)
            .build();
    private DaoContractsOpenSellController daoContractController ;




    private ObservableList materialList = FXCollections.observableArrayList();
    private ObservableList customersList = FXCollections.observableArrayList();

    public void initialize() {

        // Receiving company names


        customersList.setAll(daoCustomerController.selectList());
        choiceContractSell.setItems(customersList);


        // Receiving material list

        materialList.setAll( daoMaterialController.selectList());


        // Adding material list to the choicebox
        choiceMaterialSellContract.setItems(materialList);


    }

    private int id = 0;

    /**
     * Method responsible for insert full details of contract to Data Base.
     */
    public void addContractButton() {

        // Receiving company name.
        String companyName = choiceContractSell.getValue().toString();
        // Receiving material type.
        String material = choiceMaterialSellContract.getValue().toString();
        // Receiving id from company name.
        id =  daoCustomerController.findByName(companyName).get(0).getId();



        daoContractController = DaoContractsOpenSellController.builder()
                .idSell(0)
                .idCustomer(id)
                .customerName(companyName)
                .idName(material)
                .nrTruck(Integer.parseInt(truckContractSell.getText()))
                .nrTruckContract(0)
                .amount(Integer.parseInt(nrSellContract.getText()))
                .contractName(amountContractSell.getText())
                .dao(genericDaoContractSell)
                .build();
        daoContractController.add();
        // Clearing added and chosen type.
        nrSellContract.clear();
        amountContractSell.clear();
        truckContractSell.clear();


    }


}
