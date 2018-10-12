package combo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class ComboCustomers {

    private ChoiceBox choiceBox;
    private ComboBox comboBox;


    private ObservableList Combolist = FXCollections.observableArrayList();


    public ComboCustomers(Object object, String toDo, String ColumnLabel, String typeOfObject) {

        new SelectListOfThings(toDo,ColumnLabel, Combolist);
        switch (typeOfObject) {
            case ("ChoiceBox"):
                ChoiceBox    choiceBox = (ChoiceBox) object;

                choiceBox.setItems(getCombolist());
                break;
            case ("ComboBox"):
                ComboBox    comboBox = (ComboBox) object;


                comboBox.setItems(getCombolist());
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

    public ObservableList getCombolist() {
        return Combolist;
    }

    public void setCombolist(ObservableList combolist) {
        Combolist = combolist;
    }
}
