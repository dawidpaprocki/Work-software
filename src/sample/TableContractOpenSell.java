package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import javax.swing.text.TabableView;


public class TableContractOpenSell
{


    private final StringProperty CustomerName;


    public TableContractOpenSell
    (String CustomerName){

        this.CustomerName = new SimpleStringProperty(CustomerName);

    }


    public  String CustomerName(){
        return CustomerName.get();}


    public  void CustomerName(String value){
        CustomerName.set(value);
    }


    public StringProperty CustomerNameProperty() {
        return CustomerName;
    }




}
