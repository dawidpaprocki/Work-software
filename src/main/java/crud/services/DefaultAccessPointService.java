package crud.services;

import crud.model.AccessPoint;
import crud.model.Role;
import crud.repository.AccessPointRepository;
import crud.services.interfaces.AccessPointService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultAccessPointService implements AccessPointService {

    private AccessPointRepository accessPointRepository;
    private AccessPoint accessPoint;
    private DefaultRoleService defaultRoleService;

    public DefaultAccessPointService(AccessPointRepository accessPointRepository, DefaultRoleService defaultRoleService) {
        this.accessPointRepository = accessPointRepository;
        this.defaultRoleService = defaultRoleService;
    }

    @Override
    public List<AccessPoint> findAll() {
        return accessPointRepository.findAll();
    }

    @Override
    public void addAccess(Role role, String accessPointName) {
        accessPoint = AccessPoint.builder()
                .pointName(accessPointName)
                .role(role).build();
        accessPointRepository.save(accessPoint);
    }

    @Override
    public void removeAccess(Role role, String removePoint) {
        accessPoint = accessPointRepository.findByRoleAndPointName(role, removePoint).get();
        accessPointRepository.delete(accessPoint);
    }
    @Override
    public List<AccessPoint> findByRole(Role role) {
        List<AccessPoint> foundAccessPoint;
        try{
         foundAccessPoint = accessPointRepository.findByRole(role);
        }catch (Exception e){
            return Arrays.asList(lackOfAccessPoint());
        }
        if (foundAccessPoint.isEmpty()) {
            return Arrays.asList(lackOfAccessPoint());
        }else {
        return foundAccessPoint;
        }
    }

    public List<String> getAccessPointsForLoggedUser(Authentication auth){
        List<Role> roleList = new ArrayList<>();
        List<String> accessPoints = new ArrayList<>();
        auth.getAuthorities().forEach(element
                -> roleList.add( defaultRoleService.findRoleByName(element.toString())));
        roleList.forEach(role -> accessPoints.addAll(
                        findByRole(role).stream()
                        .map(AccessPoint::getPointName)
                        .collect(Collectors.toList())));
        return accessPoints;
    }

    private AccessPoint lackOfAccessPoint(){
        return AccessPoint.builder()
                .pointName("Lack of accessPoints")
                .role(Role.builder().name("Lack of roles").build())
                .build();
    }


}
