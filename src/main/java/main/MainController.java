package main;

import annotations.TextFieldNoEmpty;
import crud.services.DefaultAccessPointService;
import fxControllers.SecurityPrivilegesSetup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tools.Alerts;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MainController {

    @Autowired
    private DefaultAccessPointService accessPointService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private SecurityPrivilegesSetup securityPrivilegesSetup;
    private ObservableList<String> userRoles = FXCollections.observableArrayList();
    private ObservableList<String> userAccessPoints = FXCollections.observableArrayList();
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

    @FXML
    public void initialize() {
        afterLogAction();
        roles.setItems(userRoles);
        accessPointsList.setItems(userAccessPoints);
    }

    @FXML
    public void handleLogin() {
        if(validateObject.validateObject(this)) {
            authenticationChecker();
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

    private void authenticationChecker() {
            try {
                Authentication request = new UsernamePasswordAuthenticationToken(userField.getText().trim(), passwordField.getText().trim());
                Authentication result = authManager.authenticate(request);
                SecurityContextHolder.getContext().setAuthentication(result);
                afterLogAction();
            } catch (AuthenticationException e) {
                Alerts.alertShowAndWait();
            }
        userField.clear();
        passwordField.clear();
    }

    private void afterLogAction(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        updateUserInfoRoles(auth);
        updateUserInfoAccessPoints(auth);
        securityPrivilegesSetup.accessProviderForTabs();
    }


    private void updateUserInfoRoles(Authentication auth){
        userName.setText(auth.getName());
        List<String> grantedAuthorities = auth.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        userRoles.clear();
        userRoles.addAll(grantedAuthorities);
    }

    private void updateUserInfoAccessPoints(Authentication auth){
        List<String> accessPoints = accessPointService.getAccessPointsForLoggedUser(auth);
        userAccessPoints.clear();
        userAccessPoints.addAll(accessPoints);
    }



}
