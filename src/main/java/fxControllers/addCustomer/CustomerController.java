package fxControllers.addCustomer;

import crud.services.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import crud.model.Customer;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {

    @FXML
    private TextField CompanyName;
    @FXML
    private TextField CountryName;
    @FXML
    private Button addCustomer;

    private CustomerService customerService;
    private Customer customer;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    public void addCustomerButton(ActionEvent actionEvent) {
        customer = Customer.builder()
                .name(CompanyName.getText())
                .country(CountryName.getText())
                .build();
        customerService.add(customer);
        CompanyName.clear();
        CountryName.clear();
    }
}
