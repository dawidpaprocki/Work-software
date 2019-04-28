package main;

import crud.model.User;
import crud.services.interfaces.UserService;
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

@Component
public class MainController implements Initializable {


    private AuthenticationManager authManager;
    

    private UserService userService;

    private  SampleController sampleController;
    private ObservableList<User> users = FXCollections.observableArrayList();
    
    private ObservableList<String> userRoles = FXCollections.observableArrayList();
    
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
    private Label lblUserName;
    
    @FXML
    private ListView<String> lstVwRoles;

    public MainController(AuthenticationManager authManager, UserService userService, SampleController sampleController) {
        this.authManager = authManager;
        this.userService = userService;
        this.sampleController = sampleController;
    }

    @FXML
    void handleLogin(ActionEvent event) {
        
        final String userName = txtFldUser.getText().trim();
        final String userPassword = pswdFldPassword.getText().trim();
        
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(userName, userPassword);
            Authentication result = authManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);            
            
            updateUserInfo();
        
        } catch (AuthenticationException e) {
            
            Alert alertWrongCredentials = new Alert(AlertType.INFORMATION);
            alertWrongCredentials.setHeaderText("Ops!");
            alertWrongCredentials.setContentText("Usuário ou senha incorreta!");
            
        }
        txtFldUser.clear();
        pswdFldPassword.clear();
        sampleController.roleChecker();
    }

    
    private void setUserAndPassword(String user, String password){
        txtFldUser.setText(user);
        pswdFldPassword.setText(password);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        Main.logout();
        updateUserInfo();
    }

    private void updateUserInfo(){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();        
        lblUserName.setText(auth.getName());
        List<String> grantedAuthorities = auth.getAuthorities().stream().map( a -> a.toString()).collect(Collectors.toList());
        userRoles.clear();
        userRoles.addAll(grantedAuthorities);
        
    }

    
    @FXML
    private void handleExit(ActionEvent event){
        System.exit(0);
    }
    

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    
    
}
