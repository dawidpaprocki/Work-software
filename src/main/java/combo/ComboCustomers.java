package combo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class ComboCustomers {

    private ChoiceBox choiceBox;
    private ComboBox comboBox;


    private ObservableList comboList = FXCollections.observableArrayList();


    public ComboCustomers(Object object, String toDo, String columnLabel, String typeOfObject) {

        new SelectListOfThings(toDo,columnLabel, comboList);
        switch (typeOfObject) {
            case ("ChoiceBox"):
                ChoiceBox    choiceBox = (ChoiceBox) object;

                choiceBox.setItems(getComboList());
                break;
            case ("ComboBox"):
                ComboBox    comboBox = (ComboBox) object;


                comboBox.setItems(getComboList());
                break;

        }

        //Gets the names of companies and adds tochoicebox(Contract buy add,Contract sell add,Car add)

    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

    public void setChoiceBox(ChoiceBox choiceBox) {
        this.choiceBox = choiceBox;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public ObservableList getComboList() {
        return comboList;
    }

    public void setComboList(ObservableList comboList) {
        this.comboList = comboList;
    }
}
