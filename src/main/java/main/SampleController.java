package main;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class SampleController {
    @FXML
    public Tab cars;

    @FXML
    private void initialize() {

    }

    public void roleChecker(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().stream().anyMatch(r -> ((GrantedAuthority) r)
                .getAuthority()
                .equals("ROLE_NIE"))){
            cars.setDisable(true);
        }else {
            cars.setDisable(false);
        }

    }

}
