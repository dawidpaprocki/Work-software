package table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class table
{
    private final StringProperty Id;
    private final StringProperty Data;
    private final StringProperty Material;
    private final StringProperty Truck;
    private final StringProperty Amount;
    private final StringProperty Final_Amount;
    private final StringProperty Froms;
    private final StringProperty Tos;
    private final StringProperty Truck_Nr;
    private final StringProperty Order;
    private final StringProperty Vk;
    private final StringProperty Ek;
    private final StringProperty Ams_doc;
    private final StringProperty Color;





    public table
    (String Id, String Data, String Material, String Truck, String Amount, String Final_Amount,
     String Froms, String Tos, String Truck_Nr, String Order, String Vk, String Ek, String Ams_doc, String Color)
    {
        this.Id = new SimpleStringProperty(Id);
        this.Data = new SimpleStringProperty(Data);
        this.Material = new SimpleStringProperty(Material);
        this.Truck = new SimpleStringProperty(Truck);
        this.Amount = new SimpleStringProperty(Amount);
        this.Final_Amount = new SimpleStringProperty(Final_Amount);
        this.Froms = new SimpleStringProperty(Froms);
        this.Tos = new SimpleStringProperty(Tos);
        this.Truck_Nr = new SimpleStringProperty(Truck_Nr);
        this.Order = new SimpleStringProperty(Order);
        this.Vk = new SimpleStringProperty(Vk);
        this.Ek = new SimpleStringProperty(Ek);
        this.Ams_doc = new SimpleStringProperty(Ams_doc);
        this.Color = new SimpleStringProperty(Color);



    }

    public  String Id(){return Id.get();}
    public  String Data(){return Data.get();}
    public  String Material(){return Material.get();}
    public  String Truck(){return Truck.get();}
    public  String Amount(){return Amount.get();}
    public  String Final_Amount(){return Final_Amount.get();}
    public  String Froms(){return Froms.get();}
    public  String Tos(){return Tos.get();}
    public  String Truck_Nr(){return Truck_Nr.get();}
    public  String Order(){return Order.get();}
    public  String Vk(){return Vk.get();}
    public  String Ek(){return Ek.get();}
    public  String Ams_doc(){return Ams_doc.get();}
    public  String Color(){return Color.get();}



    public  void setId(String value){Id.set(value);}
    public  void setData(String value){Data.set(value);}
    public  void setMaterial(String value){Material.set(value);}
    public  void setTruck(String value){
        Truck.set(value);}
    public  void setAmount(String value){Amount.set(value);}
    public  void setFinal_Amount(String value){Final_Amount.set(value);}
    public  void setFroms(String value){Froms.set(value);}
    public  void setTos(String value){Tos.set(value);}
    public  void setTruck_Nr(String value){
        Truck_Nr.set(value);}
    public  void setOrder(String value){Order.set(value);}
    public  void setVk(String value){Vk.set(value);}
    public  void setEk(String value){Ek.set(value);}
    public  void setAms_doc(String value){
        Ams_doc.set(value);}
    public  void setColor(String value){
        Color.set(value);}


    public StringProperty IdProperty() {
        return Id;
    }
    public StringProperty DataProperty() {
        return Data;
    }
    public StringProperty MaterialProperty() {
        return Material;
    }
    public StringProperty TruckProperty() {
        return Truck;
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
    public StringProperty Truck_NrProperty() {
        return Truck_Nr;
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
    public StringProperty Ams_docProperty() {
        return Ams_doc;
    }
    public StringProperty ColorProperty() {
        return Color;
    }







}