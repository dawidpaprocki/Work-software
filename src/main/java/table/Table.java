package table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Table

{
    private final StringProperty id;
    private final StringProperty data;
    private final StringProperty material;
    private final StringProperty truck;
    private final StringProperty amount;
    private final StringProperty finalAmount;
    private final StringProperty froms;
    private final StringProperty tos;
    private final StringProperty truckNr;
    private final StringProperty order;
    private final StringProperty vk;
    private final StringProperty ek;
    private final StringProperty amsDoc;
    private final StringProperty color;





    public Table
    (String id, String data, String material, String truck, String amount, String finalAmount,
     String froms, String tos, String truckNr, String order, String vk, String ek, String amsDoc, String color)
    {
        this.id = new SimpleStringProperty(id);
        this.data = new SimpleStringProperty(data);
        this.material = new SimpleStringProperty(material);
        this.truck = new SimpleStringProperty(truck);
        this.amount = new SimpleStringProperty(amount);
        this.finalAmount = new SimpleStringProperty(finalAmount);
        this.froms = new SimpleStringProperty(froms);
        this.tos = new SimpleStringProperty(tos);
        this.truckNr = new SimpleStringProperty(truckNr);
        this.order = new SimpleStringProperty(order);
        this.vk = new SimpleStringProperty(vk);
        this.ek = new SimpleStringProperty(ek);
        this.amsDoc = new SimpleStringProperty(amsDoc);
        this.color = new SimpleStringProperty(color);



    }

    public  String getId(){return id.get();}
    public  String getData(){return data.get();}
    public  String getMaterial(){return material.get();}
    public  String getTruck(){return truck.get();}
    public  String getAmount(){return amount.get();}
    public  String getFinalAmount(){return finalAmount.get();}
    public  String getFroms(){return froms.get();}
    public  String getTos(){return tos.get();}
    public  String getTruckNr(){return truckNr.get();}
    public  String getOrder(){return order.get();}
    public  String getVk(){return vk.get();}
    public  String getEk(){return ek.get();}
    public  String getAmsDoc(){return amsDoc.get();}
    public  String getColor(){return color.get();}



    public  void setId(String value){
        id.set(value);}
    public  void setData(String value){
        data.set(value);}
    public  void setMaterial(String value){
        material.set(value);}
    public  void setTruck(String value){
        truck.set(value);}
    public  void setAmount(String value){
        amount.set(value);}
    public  void setFinalAmount(String value){
        finalAmount.set(value);}
    public  void setFroms(String value){
        froms.set(value);}
    public  void setTos(String value){
        tos.set(value);}
    public  void setTruckNr(String value){
        truckNr.set(value);}
    public  void setOrder(String value){
        order.set(value);}
    public  void setVk(String value){
        vk.set(value);}
    public  void setEk(String value){
        ek.set(value);}
    public  void setAmsDoc(String value){
        amsDoc.set(value);}
    public  void setColor(String value){
        color.set(value);}


    public StringProperty idProperty() {
        return id;
    }
    public StringProperty dataProperty() {
        return data;
    }
    public StringProperty materialProperty() {
        return material;
    }
    public StringProperty truckProperty() {
        return truck;
    }
    public StringProperty amountProperty() {
        return amount;
    }
    public StringProperty finalAmountProperty() {
        return finalAmount;
    }
    public StringProperty fromsProperty() {
        return froms;
    }
    public StringProperty tosProperty() {
        return tos;
    }
    public StringProperty truckNrProperty() {
        return truckNr;
    }
    public StringProperty orderProperty() {
        return order;
    }
    public StringProperty vkProperty() {
        return vk;
    }
    public StringProperty ekProperty() {
        return ek;
    }
    public StringProperty amsDocProperty() {
        return amsDoc;
    }
    public StringProperty colorProperty() {
        return color;
    }







}