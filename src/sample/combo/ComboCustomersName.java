package sample.combo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import sample.connectionPackage.Select;

public class ComboCustomersName {
    private ComboBox comobox;
    private ChoiceBox ChoiceContractSell;
    private ChoiceBox ChoiceContractBuy;
    private ObservableList Combolist = FXCollections.observableArrayList();

    public ComboCustomersName(ChoiceBox ChoiceContractSell,ChoiceBox ChoiceContractBuy,ComboBox comobox )
    {

        new Select("Customers","NAME","Name",Combolist);

        comobox.setItems(getCombolist());
        ChoiceContractBuy.setItems(getCombolist());
        ChoiceContractSell.setItems(getCombolist());


    }



    public ComboBox getComobox() {
        return comobox;
    }

    public void setComobox(ComboBox comobox) {
        this.comobox = comobox;
    }

    public ChoiceBox getChoiceContractSell() {
        return ChoiceContractSell;
    }

    public void setChoiceContractSell(ChoiceBox choiceContractSell) {
        ChoiceContractSell = choiceContractSell;
    }

    public ChoiceBox getChoiceContractBuy() {
        return ChoiceContractBuy;
    }

    public void setChoiceContractBuy(ChoiceBox choiceContractBuy) {
        ChoiceContractBuy = choiceContractBuy;
    }

    public ObservableList getCombolist() {
        return Combolist;
    }

    public void setCombolist(ObservableList combolist) {
        Combolist = combolist;
    }
}
