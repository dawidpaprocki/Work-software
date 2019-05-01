package crud.services;

import crud.model.AccessPoint;
import crud.model.Role;
import crud.repository.AccessPointRepository;
import crud.services.interfaces.AccessPointService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class DefaultAccessPointService implements AccessPointService {

    private AccessPointRepository accessPointRepository;

    private AccessPoint accessPoint;

    public DefaultAccessPointService(AccessPointRepository accessPointRepository) {
        this.accessPointRepository = accessPointRepository;
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

    private AccessPoint lackOfAccessPoint(){
        return AccessPoint.builder()
                .pointName("Lack of accessPoints")
                .role(Role.builder().name("Lack of roles").build())
                .build();
    }


}
