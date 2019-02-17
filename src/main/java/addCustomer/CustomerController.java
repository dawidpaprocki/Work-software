package addCustomer;

import crud.controller.DaoCustomerController;
import crud.model.GenericDaoImpl;
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
    private TextField CountryName;
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
                .Country(CountryName.getText())
                .build();

        daoCustomerController.add();
        CompanyName.clear();
        CountryName.clear();
    }
}
