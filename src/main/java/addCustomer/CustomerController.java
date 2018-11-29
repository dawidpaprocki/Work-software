package addCustomer;

import crud.controller.controllers.DaoContractsOpenSellController;
import crud.controller.controllers.DaoCustomerController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenSell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;

public class CustomerController {

    @FXML
    private TextField CompanyName;
    @FXML
    private TextField Country;
    @FXML
    private Button addCustomer;

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();

    private GenericDaoImpl genericDao = new GenericDaoImpl(entityManager, CustomerController.class);
    private DaoCustomerController daoCustomerController ;

    public void addCustomerButton(ActionEvent actionEvent) {
        daoCustomerController = DaoCustomerController.builder()
                .dao(genericDao)
                .Name(CompanyName.getText())
                .Country(Country.getText())
                .build();

        daoCustomerController.add();
        CompanyName.clear();
        Country.clear();
    }
}
