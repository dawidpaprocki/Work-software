package sample2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableContract
{
    private final StringProperty idCustomer;


    public TableContract(String idCustomer)
    {
        this.idCustomer = new SimpleStringProperty(idCustomer);
    }

    public  String idCustomer(){return idCustomer.get();}

    public  void idCustomer(String value){idCustomer.set(value);}

    public StringProperty idCustomer() {
        return idCustomer;
    }




}

