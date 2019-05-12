import crud.model.ContractsOpenSell;
import crud.repository.ContractOpenSellRepository;
import crud.services.DefaultContractsOpenSellService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DefaultContractsOpenSellServiceTest {
    @Autowired
    private PropertiesReader propertiesFile;
    @Autowired
    private DefaultContractsOpenSellService defaultContractsOpenSellService;
    @Autowired
    private ContractOpenSellRepository contractOpenSellRepository;
    private ContractsOpenSell contractsOpenSell;



    @Before
    public void before() {
        contractOpenSellRepository = mock(ContractOpenSellRepository.class);
        contractsOpenSell = ContractsOpenSell.builder()
                .id(1L)
                .contractName("Test Contract")
                .build();
        defaultContractsOpenSellService = new DefaultContractsOpenSellService(contractOpenSellRepository,propertiesFile);
    }


    @Test
    public void findByContractNumberTestTrue() {
        //given
        when(contractOpenSellRepository.findByContractName(Mockito.any()))
                .thenReturn(Optional.of(contractsOpenSell));
        //when
        ContractsOpenSell foundContract = defaultContractsOpenSellService.findByContractNumber("TestMaterial");
        //then
        assertEquals("Test Contract", foundContract.getContractName());
    }
    @Test
    public void findByContractNumberTestFalse() {
        //given
        when(contractOpenSellRepository.findByContractName(Mockito.any()))
                .thenReturn(Optional.empty());
        //when
        ContractsOpenSell foundContract =
                defaultContractsOpenSellService.findByContractNumber("No material");
        //then
        assertNotEquals("Test Contract", foundContract.getContractName());
        assertEquals(propertiesFile.getPropertiesFile().getProperty("lackOfOpenContract"),
                foundContract.getContractName());
    }

    @Test
    public void findByIdTestTrue() {
        //given
        when(contractOpenSellRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(contractsOpenSell));
        //when
        ContractsOpenSell foundContract = defaultContractsOpenSellService.findById(1L);
        //then
        assertEquals("Test Contract", foundContract.getContractName());
    }
    @Test
    public void findByIdTestFalse() {
        //given
        when(contractOpenSellRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());
        //when
        ContractsOpenSell foundContract = defaultContractsOpenSellService.findById(1L);
        //then
        assertNotEquals("Test Contract", foundContract.getContractName());
        assertEquals(propertiesFile.getPropertiesFile().getProperty("lackOfOpenContract"),
                foundContract.getContractName());
    }


}
