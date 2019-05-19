package fxControllers.loginPanel;

import crud.services.DefaultAccessPointService;
import fxControllers.SecurityPrivilegesSetup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tools.Alerts;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthenticationProcess {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private SecurityPrivilegesSetup securityPrivilegesSetup;
    @Autowired
    private DefaultAccessPointService accessPointService;
    @Getter
    String AuthenticationName;
    @Getter
    ObservableList<String> userRoles = FXCollections.observableArrayList();
    @Getter
    ObservableList<String> userAccessPoints = FXCollections.observableArrayList();


    public void authenticationChecker(String userName, String password) {

        try {
            Authentication request = new UsernamePasswordAuthenticationToken(userName, password);
            Authentication result = authManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            afterLogActionAuthentication();
        } catch (AuthenticationException e) {
            Alerts.alertShowAndWait();
        }

    }

    void afterLogActionAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        updateUserInfoRoles(auth);
        updateUserInfoAccessPoints(auth);
        securityPrivilegesSetup.accessProviderForTabs();
    }


    private void updateUserInfoRoles(Authentication auth) {
        AuthenticationName = auth.getName();
        List<String> grantedAuthorities = auth.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        if (Optional.ofNullable(userRoles).isPresent()) {
            userRoles.clear();
        }
        userRoles.addAll(grantedAuthorities);
    }

    private void updateUserInfoAccessPoints(Authentication auth) {
        List<String> accessPoints = accessPointService.getAccessPointsForLoggedUser(auth);
        if (Optional.ofNullable(userAccessPoints).isPresent()) {
            userAccessPoints.clear();
        }
        userAccessPoints.addAll(accessPoints);
    }

}
