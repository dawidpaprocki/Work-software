package crud.services.interfaces;

import crud.model.AccessPoint;
import crud.model.Role;

import java.util.List;

public interface AccessPointService {
    List<AccessPoint> findAll();
    void addAccess(Role role, String accessPointName);
    void removeAccess(Role role,String removePoint);
    List<AccessPoint>findByRole(Role role);
}
