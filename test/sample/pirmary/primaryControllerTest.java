package sample.pirmary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import pirmary.primaryController;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class primaryControllerTest {
    ObservableList ChoiceContractList1 = FXCollections.observableArrayList();
    @Test
    void test() {
        primaryController primaryController = new primaryController();


        assertNotNull( primaryController.getChoiceContractList());



    }
}