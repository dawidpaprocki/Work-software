package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableContractOpenBuy
{


    private final StringProperty CustomerName;


    public TableContractOpenBuy
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
