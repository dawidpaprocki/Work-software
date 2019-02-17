package fxControllers.sale;

import crud.controller.DaoContractsOpenSellController;
import crud.controller.DaoCustomerController;
import crud.controller.DaoMaterialController;
import crud.model.GenericDaoImpl;
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

    DaoMaterialController daoMaterialController =  DaoMaterialController.builder()
            .dao(genericDaoMaterial)
            .build();
    DaoCustomerController daoCustomerController =  DaoCustomerController.builder()
            .dao(genericDaoCustomer)
            .build();
    private DaoContractsOpenSellController daoContractController ;

 private ObservableList materialList = FXCollections.observableArrayList();
    private ObservableList customersList = FXCollections.observableArrayList();

    public void initialize() {

        customersList.setAll(daoCustomerController.selectList());
        choiceContractSell.setItems(customersList);

        materialList.setAll( daoMaterialController.selectList());

        choiceMaterialSellContract.setItems(materialList);
    }


    /**
     * Method responsible for insert full details of contract to Data Base.
     */
    public void addContractButton() {
        int idSellCustomer = 0;

        String contractSellCompanyName = choiceContractSell.getValue().toString();

        String materialType = choiceMaterialSellContract.getValue().toString();

        idSellCustomer =  daoCustomerController.findByName(contractSellCompanyName).get(0).getId();

        daoContractController = DaoContractsOpenSellController.builder()
                .idSell(0)
                .idCustomer(idSellCustomer)
                .customerName(contractSellCompanyName)
                .materialName(materialType)
                .nrTruck(Integer.parseInt(truckContractSell.getText()))
                .nrTruckContract(0)
                .amount(Integer.parseInt(nrSellContract.getText()))
                .contractName(amountContractSell.getText())
                .dao(genericDaoContractSell)
                .build();
        daoContractController.add();

        nrSellContract.clear();
        amountContractSell.clear();
        truckContractSell.clear();


    }


}
