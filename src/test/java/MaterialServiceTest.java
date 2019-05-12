import crud.model.Material;
import crud.repository.MaterialRepository;
import crud.services.DefaultMaterialService;
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
public class MaterialServiceTest {
    @Autowired
    private PropertiesReader propertiesFile;
    @Autowired
    private DefaultMaterialService defaultMaterialService;
    @Autowired
    private MaterialRepository materialRepository;
    private  Optional<Material>  testMaterial;

    @Before
    public void before() {
     materialRepository = mock(MaterialRepository.class);
     testMaterial = Optional.of(Material.builder()
            .name("TestMaterial")
            .id(1L).build());
        defaultMaterialService = new DefaultMaterialService(materialRepository,propertiesFile);
    }

    @Test
    public void findByNameTestTrue() {
        //given
        when(materialRepository.findByName(Mockito.any()))
                .thenReturn( testMaterial);
        //when
        Material foundMaterial = defaultMaterialService.findByName("TestMaterial");
        //then
        assertEquals(testMaterial.get().getName(),foundMaterial.getName());
        assertEquals("TestMaterial", foundMaterial.getName());
    }
    @Test
    public void findByNameTestFalse() {
        //given
        when(materialRepository.findByName(Mockito.any()))
                .thenReturn(Optional.empty());
        //when
        Material foundMaterial = defaultMaterialService.findByName("TestMaterial");
        //then
        assertNotEquals(testMaterial.get().getName(),foundMaterial.getName());
        assertNull(foundMaterial.getId());
    }


}