package entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicUpdate
public class AllView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Data;
    private String Material;
    private String Truck;
    private int Amount;
    private int FinalAmount;
    private String Froms;
    private String Tos;
    private String TruckNr;
    private String TransportOrder;
    private String Vk;
    private String Ek;
    private String AmsDoc;
    private String Color;

    public AllView() {
    }


    public AllView(String data, String material, String truck, int amount, int finalAmount, String froms, String tos, String truckNr,
                   String transportOrder, String vk, String ek, String amsDoc, String color) {
        Data = data;
        Material = material;
        Truck = truck;
        Amount = amount;
        FinalAmount = finalAmount;
        Froms = froms;
        Tos = tos;
        TruckNr = truckNr;
        TransportOrder = transportOrder;
        Vk = vk;
        Ek = ek;
        AmsDoc = amsDoc;
        Color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getTruck() {
        return Truck;
    }

    public void setTruck(String truck) {
        Truck = truck;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getFinalAmount() {
        return FinalAmount;
    }

    public void setFinalAmount(int finalAmount) {
        FinalAmount = finalAmount;
    }

    public String getFroms() {
        return Froms;
    }

    public void setFroms(String froms) {
        Froms = froms;
    }

    public String getTos() {
        return Tos;
    }

    public void setTos(String tos) {
        Tos = tos;
    }

    public String getTruckNr() {
        return TruckNr;
    }

    public void setTruckNr(String truckNr) {
        TruckNr = truckNr;
    }

    public String getTransportOrder() {
        return TransportOrder;
    }

    public void setTransportOrder(String transportOrder) {
        TransportOrder = transportOrder;
    }

    public String getVk() {
        return Vk;
    }

    public void setVk(String vk) {
        Vk = vk;
    }

    public String getEk() {
        return Ek;
    }

    public void setEk(String ek) {
        Ek = ek;
    }

    public String getAmsDoc() {
        return AmsDoc;
    }

    public void setAmsDoc(String amsDoc) {
        AmsDoc = amsDoc;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
