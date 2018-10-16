import combo.ComboCustomers;
import combo.SelectListOfThings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ComboCustomersTest {

    private ChoiceBox choiceBox;
    private ComboBox comboBox;
    private ObservableList comboList = FXCollections.observableArrayList();
    List list = new ArrayList();
    @Mock
    private SelectListOfThings selectListOfThings;

    @InjectMocks
    private ComboCustomers comboCustomers;

    @Before
    public void Setup(){

        comboList.add("one");
        comboList.add("two");
    }

    @Test
    public void ComboCustomersTest(){

        when(selectListOfThings.getComboList())
                .thenReturn(comboList);
        comboCustomers = new ComboCustomers(new Object(),"nothing","nothing","ChoiceBox");
        assertNotNull(comboCustomers.getChoiceBox());



    }

}
