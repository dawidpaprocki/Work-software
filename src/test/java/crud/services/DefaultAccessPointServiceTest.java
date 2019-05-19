package crud.services;

import crud.model.AccessPoint;
import crud.model.Role;
import crud.repository.AccessPointRepository;
import crud.services.interfaces.AccessPointService;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DefaultAccessPointServiceTest {


    private List<AccessPoint> accessPointListForTest;
    private AccessPointRepository accessPointRepository;
    @Autowired
    private DefaultRoleService defaultRoleService;
    private AccessPointService accessPointService;
    private   Role admin;
    AccessPoint  accessPointAdmin;

    @Before
    public void before(){
        accessPointRepository = mock(AccessPointRepository.class);
        admin = Role.builder().name("Admin").build();
        accessPointAdmin = AccessPoint
                .builder()
                .pointName("mainTab")
                .role(admin)
                .id(1L)
                .build();
        AccessPoint  accessPointTwo = AccessPoint
                .builder()
                .pointName("main2Tab")
                .role(Role.builder().name("User").build())
                .id(1L)
                .build();
        accessPointListForTest = Arrays.asList(accessPointAdmin,accessPointTwo);
        accessPointService = new DefaultAccessPointService(accessPointRepository,defaultRoleService);
    }

    @Test
    public void findByRole() {
        //given
        when(accessPointRepository.findByRole(Mockito.any())).thenReturn(accessPointListForTest);
        //when
        List<AccessPoint> foundAccessPoints = accessPointService.findByRole(admin);
        //then
        assertEquals(foundAccessPoints.get(0).getPointName(),accessPointAdmin.getPointName());
    }

    @Test
    public void getAccessPointsForLoggedUser() {
    }
}