import crud.model.Role;
import crud.repository.RoleRepository;
import crud.services.DefaultRoleService;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tools.PropertiesReader;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class RoleServiceTest {

    @Autowired
    private PropertiesReader propertiesFile;
    @Autowired
    private DefaultRoleService defaultRoleService;
    @Autowired
    private RoleRepository roleRepository;
    private Optional<Role> testRole;

    @Before
    public void before() {
        roleRepository = mock(RoleRepository.class);
        testRole = Optional.of(Role.builder()
                .name("TestRole")
                .id(1L)
                .build());
        defaultRoleService = new DefaultRoleService(roleRepository,propertiesFile);
    }

    @Test
    public void findByNameTestTrue() {
        //given
        when(roleRepository.findByName(Mockito.any()))
                .thenReturn(testRole);
        //when
        Role foundRole = defaultRoleService.findRoleByName("TestRole");
        //then
        assertEquals(testRole.get().getName(),foundRole.getName());
        assertEquals("TestRole", foundRole.getName());
    }
    @Test
    public void findByNameTestFalse() {
        //given
        when(roleRepository.findByName(Mockito.any()))
                .thenReturn(Optional.empty());
        //when
        Role foundRole = defaultRoleService.findRoleByName("TestRole");
        //then
        assertNotEquals(testRole.get().getName(),foundRole.getName());
        assertNull(foundRole.getId());
    }

}
