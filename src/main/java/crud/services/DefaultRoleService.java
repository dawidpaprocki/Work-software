package crud.services;

import crud.model.Role;
import crud.repository.RoleRepository;
import crud.services.interfaces.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultRoleService implements RoleService {

    private RoleRepository roleRepository;

    private Role role;

    public DefaultRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void addToRole(String roleName) {
        role = Role.builder().name(roleName).build();
        roleRepository.save(role);
    }

    @Override
    public void removeFromRole(Long roleId) {
        Optional<Role> foundRole = roleRepository.findById(roleId);
        roleRepository.delete(foundRole.get());
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElse(Role.builder().name("Lack of Roles").build());
    }
}
