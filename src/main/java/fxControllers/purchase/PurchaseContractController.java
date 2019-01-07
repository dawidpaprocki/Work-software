package fxControllers.purchase;

import crud.controller.controllers.DaoContractsOpenBuyController;
import crud.controller.controllers.DaoCustomerController;
import crud.controller.controllers.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenBuy;
import entity.Customer;
import entity.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;

public class PurchaseContractController {

    @FXML
    private ChoiceBox choiceCustomerNameBuy;

    @FXML
    private TextField nrBuyContract;

    @FXML
    private TextField amountContractBuy;

    @FXML
    private Button addBuy;

    @FXML
    private ChoiceBox choiceMaterialBuyContract;

    @FXML
    private TextField truckContractBuy;

    @FXML
    private ChoiceBox choiceCustomerNameSell;

    private ObservableList materialList = FXCollections.observableArrayList();
    private ObservableList customersList = FXCollections.observableArrayList();


    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManagerCustomer = sessionFactory.createEntityManager();
    private final EntityManager entityManagerMaterial = sessionFactory.createEntityManager();
    private final EntityManager entityManagerContractBuy = sessionFactory.createEntityManager();


    private GenericDaoImpl genericDaoMaterial = new GenericDaoImpl(entityManagerMaterial, Material.class);
    private GenericDaoImpl genericDaoCustomer = new GenericDaoImpl(entityManagerCustomer, Customer.class);
    private GenericDaoImpl genericDaoContractBuy = new GenericDaoImpl(entityManagerContractBuy, ContractsOpenBuy.class);


    DaoMaterialController daoMaterialController = DaoMaterialController.builder()
            .dao(genericDaoMaterial)
            .build();
    DaoCustomerController daoCustomerController = DaoCustomerController.builder()
            .dao(genericDaoCustomer)
            .build();
    DaoContractsOpenBuyController daoContractController;

    public void initialize() {

        customersList.setAll(daoCustomerController.selectList());
        choiceCustomerNameBuy.setItems(customersList);
        choiceCustomerNameSell.setItems(customersList);

        materialList.setAll(daoMaterialController.selectList());

        choiceMaterialBuyContract.setItems(materialList);

    }

    public void addContractButton() {


        String companyName = choiceCustomerNameBuy.getValue().toString();

        String materialName = choiceMaterialBuyContract.getValue().toString();

        String nameOfCompanyBuyToSell = choiceCustomerNameSell.getValue().toString();

        int idBuyer = daoCustomerController.findByName(companyName).get(0).getId();

        int idSeller = daoCustomerController.findByName(nameOfCompanyBuyToSell).get(0).getId();


        daoContractController = DaoContractsOpenBuyController.builder()
                .idSell(idSeller)
                .idCustomer(idBuyer)
                .customerName(companyName)
                .idName(materialName)
                .nrTruck(Integer.parseInt(truckContractBuy.getText()))
                .nrTruckContract(0)
                .amount(Integer.parseInt(nrBuyContract.getText()))
                .contractName(amountContractBuy.getText())
                .dao(genericDaoContractBuy)
                .build();

        daoContractController.add();


        choiceMaterialBuyContract.setValue(null);
        choiceCustomerNameSell.setValue(null);
        choiceCustomerNameBuy.setValue(null);
        nrBuyContract.clear();
        amountContractBuy.clear();
        truckContractBuy.clear();


    }


}
