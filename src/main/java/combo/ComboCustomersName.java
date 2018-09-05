package combo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class ComboCustomersName {
    private ComboBox comobox;

    private ObservableList Combolist = FXCollections.observableArrayList();


    public ComboCustomersName(ComboBox comobox )
    {

        new SelectCombolist("Customers","NAME","Name",Combolist);

        //Gets the names of companies and adds tochoicebox(Contract buy add,Contract sell add,Car add)
        comobox.setItems(getCombolist());



    }



    public ComboBox getComobox() {
        return comobox;
    }

    public void setComobox(ComboBox comobox) {
        this.comobox = comobox;
    }



    public ObservableList getCombolist() {
        return Combolist;
    }

    public void setCombolist(ObservableList combolist) {
        Combolist = combolist;
    }
}
