package crud.services;

import crud.model.Role;
import crud.repository.RoleRepository;
import crud.services.interfaces.RoleService;
import org.springframework.stereotype.Service;
import tools.PropertiesReader;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultRoleService implements RoleService {

    private RoleRepository roleRepository;

    private PropertiesReader propertiesReader;


    public DefaultRoleService(RoleRepository roleRepository, PropertiesReader propertiesReader) {
        this.roleRepository = roleRepository;
        this.propertiesReader = propertiesReader;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void addToRole(String roleName) {
        Role role = Role.builder().name(roleName).build();
        roleRepository.save(role);
    }

    @Override
    public void removeFromRole(Role roleForDelete) {
        roleRepository.delete(roleForDelete);
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> foundRole = roleRepository.findById(id);
        return foundRole.orElseGet(() -> Role.builder()
                .name(propertiesReader.getPropertiesFile().getProperty("lackOfRole"))
                .build());
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElse(Role.builder()
                .name(propertiesReader.getPropertiesFile().getProperty("LackOfRole"))
                .build());
    }
}
