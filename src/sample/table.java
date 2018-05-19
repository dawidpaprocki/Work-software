package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import javax.swing.text.TabableView;

public class table




{

    private final StringProperty Data;
    private final StringProperty Plate;
    private final StringProperty Amount;
    private final StringProperty Final_Amount;
    private final StringProperty Froms;
    private final StringProperty Tos;
    private final StringProperty Truck;
    private final StringProperty Order;
    private final StringProperty Vk;
    private final StringProperty Ek;
    private final StringProperty Doc;





// String Froms, String Tos, String Vk, String Ek
    public table
    (String Data,String Plate,String Amount,String Final_Amount,
     String Froms,String Tos,String Truck,String Order,String Vk,String Ek,String Doc)
    {
        this.Data = new SimpleStringProperty(Data);
        this.Plate = new SimpleStringProperty(Plate);
        this.Amount = new SimpleStringProperty(Amount);
        this.Final_Amount = new SimpleStringProperty(Final_Amount);
        this.Froms = new SimpleStringProperty(Froms);
        this.Tos = new SimpleStringProperty(Tos);
        this.Truck = new SimpleStringProperty(Truck);
        this.Order = new SimpleStringProperty(Order);
        this.Vk = new SimpleStringProperty(Vk);
        this.Ek = new SimpleStringProperty(Ek);
        this.Doc = new SimpleStringProperty(Doc);



    }

    public  String Data(){return Data.get();}
    public  String Plate(){return Plate.get();}
    public  String Amount(){return Amount.get();}
    public  String Final_Amount(){return Final_Amount.get();}
    public  String Froms(){return Froms.get();}
    public  String Tos(){return Tos.get();}
    public  String Truck(){return Truck.get();}
    public  String Order(){return Order.get();}
    public  String Vk(){return Vk.get();}
    public  String Ek(){return Ek.get();}
    public  String Doc(){return Doc.get();}




    public  void Data(String value){Data.set(value);}
    public  void Plate(String value){Plate.set(value);}
    public  void Amount(String value){Amount.set(value);}
    public  void Final_Amount(String value){Final_Amount.set(value);}
    public  void Froms(String value){Froms.set(value);}
    public  void Tos(String value){Tos.set(value);}
    public  void Truck(String value){Truck.set(value);}
    public  void Order(String value){Order.set(value);}
    public  void Vk(String value){Vk.set(value);}
    public  void Ek(String value){Ek.set(value);}
    public  void Doc(String value){Doc.set(value);}



    public StringProperty DataProperty() {
        return Data;
    }
    public StringProperty PlateProperty() {
        return Plate;
    }
    public StringProperty AmountProperty() {
        return Amount;
    }
    public StringProperty Final_AmountProperty() {
        return Final_Amount;
    }
    public StringProperty FromsProperty() {
        return Froms;
    }
    public StringProperty TosProperty() {
        return Tos;
    }
    public StringProperty TruckProperty() {
        return Truck;
    }
    public StringProperty OrderProperty() {
        return Order;
    }
    public StringProperty VkProperty() {
        return Vk;
    }
    public StringProperty EkProperty() {
        return Ek;
    }
    public StringProperty DocProperty() {
        return Doc;
    }







}