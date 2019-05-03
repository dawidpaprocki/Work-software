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
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource(locations ="classpath:config.properties")
@SpringBootTest(classes = Main.class)
public class MaterialServiceTest {
    @Autowired
    private Environment propertiesFile;
    @Autowired
    private DefaultMaterialService defaultMaterialService;

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
    public void findByIdTestTrue() {
        //given
        when(materialRepository.findById(Mockito.any()))
                .thenReturn(testMaterial);
        //when
        String foundMaterial = defaultMaterialService.findById(1L).getName();
        //then
        assertEquals("TestMaterial",foundMaterial);
    }

    @Test
    public void findByIdTestFalse() {
        //given
        when(materialRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());
        //when
        String foundMaterial = defaultMaterialService.findById(2L).getName();
        //then
        assertEquals(propertiesFile.getProperty("lackOfMaterial"),foundMaterial);

    }

    @Test
    public void findByNameTestTrue() {
        //given
        when(materialRepository.findByName(Mockito.any()))
                .thenReturn( Arrays.asList(testMaterial.get()));
        //when
        List foundMaterial = defaultMaterialService.findByName("TestMaterial");
        //then
        assertTrue(foundMaterial.contains(testMaterial.get()));
        assertEquals(1, foundMaterial.size());
    }
    @Test
    public void findByNameTestFalse() {
        //given
        when(materialRepository.findByName(Mockito.any()))
                .thenReturn( new ArrayList<>());
        //when
        List foundMaterial = defaultMaterialService.findByName("TestMaterial");
        //then
        assertFalse(foundMaterial.contains(testMaterial.get()));
        assertEquals(0, foundMaterial.size());
    }


}