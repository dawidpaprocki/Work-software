package fxControllers.customer;

import annotations.TextFieldNoEmpty;
import crud.services.interfaces.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import crud.model.Customer;
import fxControllers.validation.ValidatorGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AddCustomerController {

    @FXML
    @TextFieldNoEmpty(message = "Wpisz nazwę")
    public TextField companyName;
    @FXML
    @TextFieldNoEmpty(message = "Wpisz nazwę kraju")
    public TextField countryName;
    @FXML
    @TextFieldNoEmpty(message = "Wpisz adress")
    public TextField address;
    @FXML
    @TextFieldNoEmpty(message = "Wpisz Nip")
    public TextField nip;
    @FXML
    public Button addCustomer;

    @Autowired
    ValidatorGUI validateObject;

    private CustomerService customerService;
    private Customer customer;

    public AddCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void addCustomerButton(ActionEvent actionEvent) {
        if (validateObject.validateObject(this)) {
            customer = Customer.builder()
                    .name(companyName.getText())
                    .country(countryName.getText())
                    .address(address.getText())
                    .nip(countryName.getText())
                    .build();
            customerService.add(customer);
            companyName.clear();
            countryName.clear();
            address.clear();
            nip.clear();
        }
    }
}
