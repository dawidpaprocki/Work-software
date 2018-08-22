package sample.tableOpenContracts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableContractOpenBuy
{


    private final StringProperty CustomerName;
    private final StringProperty idName;
    private final StringProperty NrTruck;
    private final StringProperty NrTruckContract;
    private final StringProperty Amount;
    private final StringProperty ContractName;





    public TableContractOpenBuy
    (String CustomerName,String idName,String NrTruck,String NrTruckContract,String Amount,
     String ContractName)
    {
        this.CustomerName = new SimpleStringProperty(CustomerName);
        this.idName = new SimpleStringProperty(idName);
        this.NrTruck = new SimpleStringProperty(NrTruck);
        this.NrTruckContract = new SimpleStringProperty(NrTruckContract);
        this.Amount = new SimpleStringProperty(Amount);
        this.ContractName = new SimpleStringProperty(ContractName);



    }

    public  String CustomerName(){return CustomerName.get();}
    public  String idName(){return idName.get();}
    public  String NrTruck(){return NrTruck.get();}
    public  String NrTruckContract(){return NrTruckContract.get();}
    public  String Amount(){return Amount.get();}
    public  String ContractName(){return ContractName.get();}





    public  void CustomerName(String value){CustomerName.set(value);}
    public  void idName(String value){idName.set(value);}
    public  void NrTruck(String value){NrTruck.set(value);}
    public  void NrTruckContract(String value){NrTruckContract.set(value);}
    public  void Amount(String value){Amount.set(value);}
    public  void ContractName(String value){ContractName.set(value);}




    public StringProperty CustomerNameProperty() {
        return CustomerName;
    }
    public StringProperty idNameProperty() {
        return idName;
    }
    public StringProperty NrTruckProperty() {
        return NrTruck;
    }
    public StringProperty NrTruckContractProperty() {
        return NrTruckContract;
    }
    public StringProperty AmountProperty() {
        return Amount;
    }
    public StringProperty ContractNameProperty() {
        return ContractName;
    }






}
