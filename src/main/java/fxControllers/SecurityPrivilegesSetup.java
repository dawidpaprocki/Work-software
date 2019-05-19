package fxControllers;

import crud.services.interfaces.AccessPointService;
import javafx.scene.control.Tab;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SecurityPrivilegesSetup {

    private MainController mainController;
    private AccessPointService accessPointService;

    public SecurityPrivilegesSetup(MainController mainController, AccessPointService accessPointService) {
        this.mainController = mainController;
        this.accessPointService = accessPointService;
    }

    private void allowEnterTabs(List<Tab> listOfTabs){
        listOfTabs
                .forEach(tab -> tab.setDisable(false));
    }
    private void notAllowEnterTabs(List<Tab> listOfTabs){
        listOfTabs
                .forEach(tab -> tab.setDisable(true));
    }

    public void accessProviderForTabs(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> accessPointsForLoggedUser = accessPointService.getAccessPointsForLoggedUser(authentication);
        List<Tab> enterAllowTabs = new ArrayList<>();
        List<Tab> listOfTabs = mainController.getListOfTabs();
        accessPointsForLoggedUser.forEach(element ->
                mainController.getListOfTabs().forEach(tab -> {
                    if (tab.getId().equals(element)){
                        enterAllowTabs.add(tab);
                    }
                }
        ));
        listOfTabs = listOfTabs.stream()
                .filter(tab ->
                        !tab.getId().equals("loginTab"))
                .collect(Collectors.toList());
        notAllowEnterTabs(listOfTabs);
        allowEnterTabs(enterAllowTabs);
    }

}
