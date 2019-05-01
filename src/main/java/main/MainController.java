package main;

import crud.model.User;
import crud.services.DefaultAccessPointService;
import fxControllers.SecurityPrivilegesSetup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
@Slf4j
@Component
public class MainController implements Initializable {

    private DefaultAccessPointService accessPointService;
    private AuthenticationManager authManager;
    private SecurityPrivilegesSetup securityPrivilegesSetup;
    private ObservableList<User> users = FXCollections.observableArrayList();
    
    private ObservableList<String> userRoles = FXCollections.observableArrayList();
    private ObservableList<String> userAccessPoints = FXCollections.observableArrayList();

    @FXML
    private MenuItem mnItmLoginAsUser;

    @FXML
    private MenuItem mnItmLoginAsAdmin;
    
    @FXML
    private MenuItem mnItmLogout;
    
    @FXML
    private MenuItem mnItmExit;
    
    @FXML
    private Menu mnMain;

    @FXML
    private AnchorPane ancPnMaster;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane ancPnView;

    @FXML
    private TableView<User> tblVwClients;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtFldName;

    @FXML
    private TextField txtFldSurname;

    @FXML
    private TextField txtFldUser;
    
    @FXML
    private PasswordField pswdFldPassword;
    
    @FXML
    private Button btnRemove;

    @FXML
    private AnchorPane ancPnDetails;

    @FXML
    private Font x3;

    @FXML
    private Color x4;
    
    @FXML
    private Label userName;
    @FXML
    public ListView<String> accessPointsList;

    @FXML
    private ListView<String> roles;

    public MainController(DefaultAccessPointService accessPointService, AuthenticationManager authManager, SecurityPrivilegesSetup securityPrivilegesSetup) {
        this.accessPointService = accessPointService;
        this.authManager = authManager;
        this.securityPrivilegesSetup = securityPrivilegesSetup;
    }

    @FXML
    void handleLogin(ActionEvent event) {
        final String userName = txtFldUser.getText().trim();
        final String userPassword = pswdFldPassword.getText().trim();
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(userName, userPassword);
            Authentication result = authManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            updateUserInfoRoles();
            updateUserInfoAccessPoints();
        } catch (AuthenticationException e) {
            Alert alertWrongCredentials = new Alert(AlertType.INFORMATION);
            alertWrongCredentials.setHeaderText("Ops!");
            
        }
        txtFldUser.clear();
        pswdFldPassword.clear();
        securityPrivilegesSetup.roleChecker();
    }

    
    private void setUserAndPassword(String user, String password){
        txtFldUser.setText(user);
        pswdFldPassword.setText(password);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        Main.logout();
        updateUserInfoRoles();
        updateUserInfoAccessPoints();
    }

    private void updateUserInfoRoles(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();        
        userName.setText(auth.getName());
        List<String> grantedAuthorities = auth.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        userRoles.clear();
        userRoles.addAll(grantedAuthorities);
    }

    private void updateUserInfoAccessPoints(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> accessPoints = accessPointService.getAccessPointsForLoggedUser(auth);
        userAccessPoints.clear();
        userAccessPoints.addAll(accessPoints);
    }



    @FXML
    private void handleExit(ActionEvent event){
        System.exit(0);
    }
    

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateUserInfoRoles();
        updateUserInfoAccessPoints();
        roles.setItems(userRoles);
        accessPointsList.setItems(userAccessPoints);
    }

    
    
}
