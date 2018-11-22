package purchase;

import combo.*;
import crud.controller.controllers.DaoContractsOpenBuyController;
import crud.controller.controllers.DaoContractsOpenSellController;
import crud.controller.controllers.DaoCustomerController;
import crud.controller.controllers.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenBuy;
import entity.ContractsOpenSell;
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

    DaoMaterialController daoMaterialController = new DaoMaterialController(genericDaoMaterial);
    DaoCustomerController daoCustomerController = new DaoCustomerController(genericDaoCustomer);
    DaoContractsOpenBuyController daoContractController = new DaoContractsOpenBuyController(genericDaoContractBuy);

    public void initialize() {

        customersList.setAll(daoCustomerController.selectList());
        choiceCustomerNameBuy.setItems(customersList);
        choiceCustomerNameSell.setItems(customersList);

        // Receiving material list
        materialList.setAll( daoMaterialController.selectList());

        // Adding material list to the choicebox
        choiceMaterialBuyContract.setItems(materialList);
    }

    public void addContractButton() {
        // Receiving company name.
        String companyName = choiceCustomerNameBuy.getValue().toString();
        // Receiving material type.
        String material = choiceMaterialBuyContract.getValue().toString();
        // Receiving name from company name.
        String nameOfCompanyBuyToSell = choiceCustomerNameSell.getValue().toString();

      int  idBuyer =  daoCustomerController.findByName(companyName).get(0).getId();

       int idSeller =  daoCustomerController.findByName(nameOfCompanyBuyToSell).get(0).getId();

       daoContractController.add(idSeller,idBuyer,companyName,material,Integer.parseInt(truckContractBuy.getText()),
               0,Integer.parseInt(nrBuyContract.getText()),amountContractBuy.getText());


        choiceMaterialBuyContract.setValue(null);
        choiceCustomerNameSell.setValue(null);
        choiceCustomerNameBuy.setValue(null);
        nrBuyContract.clear();
        amountContractBuy.clear();
        truckContractBuy.clear();



    }


}
