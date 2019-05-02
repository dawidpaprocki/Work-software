package main;

import crud.services.DefaultAccessPointService;
import fxControllers.SecurityPrivilegesSetup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource(value = "classpath:config.properties", encoding="UTF-8")
public class MainController {

    @Autowired
    private DefaultAccessPointService accessPointService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private SecurityPrivilegesSetup securityPrivilegesSetup;
    private ObservableList<String> userRoles = FXCollections.observableArrayList();
    private ObservableList<String> userAccessPoints = FXCollections.observableArrayList();
    @Autowired
    private Environment propertiesFile;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuItem menuItemQuit;
    @FXML
    private Button buttonLogin;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label userName;
    @FXML
    public ListView<String> accessPointsList;
    @FXML
    private ListView<String> roles;
    @FXML
    public void initialize() {
        afterLogAction();
        roles.setItems(userRoles);
        accessPointsList.setItems(userAccessPoints);
    }
    @FXML
    void handleLogin() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(userField.getText().trim(), passwordField.getText().trim());
            Authentication result = authManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            afterLogAction();
        } catch (AuthenticationException e) {
            Alerts.alertShowAndWait(propertiesFile);
        }
        userField.clear();
        passwordField.clear();
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

    private void afterLogAction(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        updateUserInfoRoles(auth);
        updateUserInfoAccessPoints(auth);
        securityPrivilegesSetup.accessProviderForTabs();
    }
    



    
    
}
