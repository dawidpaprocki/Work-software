package crud.services.interfaces;

import crud.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    void addToRole(String roleName);
    void removeFromRole(Role roleForDelete);
    Role findById(Long id);
    Role findRoleByName(String roleName);
}
