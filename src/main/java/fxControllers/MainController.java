package fxControllers;

import crud.model.AccessPoint;
import crud.model.Role;
import crud.repository.AccessPointRepository;
import crud.repository.RoleRepository;
import enums.Roles;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
public class MainController {

    public TabPane tabPane;
    public Tab mainPageTab;
    public Tab carsScheduleTab;
    public Tab copperTab;
    public Tab copperMessingTab;
    public Tab copperLeadTab;
    public Tab copperZincTab;
    public Tab contractCloseTab;
    public Tab loginTab;
    public Tab customerTab;

    @Autowired
    private AccessPointRepository accessPointRepository;
    @Autowired
    private RoleRepository roleRepository;
    @FXML
    private void initialize () {
        Role foundRole = roleRepository.findByName(Roles.ADMIN.toString()).get();
        List<Tab> listOfTabs = getListOfTabs();
        listOfTabs
                .forEach(element -> {
                   AccessPoint accessPoint =
                           AccessPoint.builder()
                           .pointName(element.getId())
                           .role(foundRole)
                           .build();
                            if(!accessPointRepository.findByRoleAndPointName(foundRole,element.getId()).isPresent())
                            accessPointRepository.save(accessPoint);
                });
        listOfTabs.forEach(tab -> tab.setDisable(true));
        loginTab.setDisable(false);
    }

    public List<Tab> getListOfTabs(){
        return tabPane.getTabs();
    }

}
