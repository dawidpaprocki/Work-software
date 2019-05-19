package fxControllers.loginPanel;

import annotations.TextFieldNoEmpty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import main.Main;
import fxControllers.validation.ValidatorGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class LoginPanelController {


    @FXML
    public MenuItem menuItemLogout;
    @FXML
    public MenuItem menuItemQuit;
    @FXML
    public Button buttonLogin;
    @FXML
    @TextFieldNoEmpty(message = "Please provide login")
    public TextField userField;
    @FXML
    @TextFieldNoEmpty(message = "Please provide passowrd")
    public PasswordField passwordField;
    @FXML
    public Label userName;
    @FXML
    public ListView<String> accessPointsList;
    @FXML
    public ListView<String> roles;

    @Autowired
    ValidatorGUI validateObject;
    @Autowired
    AuthenticationProcess authenticationChecker;

    @FXML
    public void initialize() {
        afterLogAction();
        userField.clear();
        passwordField.clear();
    }

    @FXML
    public void handleLogin() {
        if(validateObject.validateObject(this)) {
            authenticationChecker.authenticationChecker(userField.getText().trim(),passwordField.getText().trim());
            userField.clear();
            passwordField.clear();
        }
    }

    @FXML
    public void handleLogout() {
        Main.logout();
        afterLogAction();
    }

    @FXML
    private void handleExit(){
        System.exit(0);
    }


    private void afterLogAction(){

        authenticationChecker.afterLogActionAuthentication();
        ObservableList<String> userAccessPoints = authenticationChecker.getUserAccessPoints();
        ObservableList<String>  userRoles = authenticationChecker.getUserRoles();
        roles.setItems(userRoles);
        accessPointsList.setItems(userAccessPoints);
    }



}
