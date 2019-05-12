package fxControllers.pirmary;

import crud.model.ContractsOpenAbstract;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;
import crud.model.Customer;
import crud.services.interfaces.*;
import javafx.scene.control.ComboBox;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class PrimaryControllerTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AllTruckService allTruckService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ContractsOpenService<ContractsOpenSell> contractsOpenSellService;
    private ContractsOpenService<ContractsOpenBuy> contractsOpenBuyService;
    private ContractsCloseService contractsCloseService;
    private ContractsOpenAbstract contractsOpenAbstract;
    @Mock
    private ComboBox sender;
    Customer TestCustomer;

    @Before
    public void setUp() {
        TestCustomer = Customer.builder()
                .country("Test country")
                .id(1L)
                .name("Test Customer")
                .build();

    }

    @Test
    public void addButton() {
    }

    @Test
    public void updateOpenCloseStatusTest() {

    }

    @Test
    public void materialPrepareSell() {
    }

    @Test
    public void selectComboBoxList() {
        //given
        Whitebox.setInternalState(TestCustomer, ComboBox.class, sender);
        //when


    }
}