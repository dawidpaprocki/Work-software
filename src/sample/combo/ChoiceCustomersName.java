package sample.combo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class ChoiceCustomersName {
    private ChoiceBox ChoiceBox;

    private ObservableList Combolist = FXCollections.observableArrayList();


    public ChoiceCustomersName(ChoiceBox ChoiceBox )
    {

        new SelectCombolist("Customers","NAME","Name",Combolist);

        //Gets the names of companies and adds to choice box(Contract buy add,Contract sell add,Car add)
        ChoiceBox.setItems(getCombolist());



    }


    public javafx.scene.control.ChoiceBox getChoiceBox() {
        return ChoiceBox;
    }

    public void setChoiceBox(javafx.scene.control.ChoiceBox choiceBox) {
        ChoiceBox = choiceBox;
    }

    public ObservableList getCombolist() {
        return Combolist;
    }

    public void setCombolist(ObservableList combolist) {
        Combolist = combolist;
    }

}
