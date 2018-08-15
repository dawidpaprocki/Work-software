package sample.pirmary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.*;

class primaryControllerTest {
    ObservableList ChoiceContractList1 = FXCollections.observableArrayList();
    @org.junit.jupiter.api.Test
    void test() {
        primaryController primaryController = new primaryController();

        assertNotNull( primaryController.getChoiceContractList());

    }
}