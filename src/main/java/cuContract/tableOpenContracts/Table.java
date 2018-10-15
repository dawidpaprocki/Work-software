package cuContract.tableOpenContracts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Table
{


    private final StringProperty customerName;
    private final StringProperty idName;
    private final StringProperty nrTruck;
    private final StringProperty nrTruckContract;
    private final StringProperty amount;
    private final StringProperty contractName;





    public Table
    (String customerName,String idName,String nrTruck,String nrTruckContract,String amount,
     String contractName)
    {
        this.customerName = new SimpleStringProperty(customerName);
        this.idName = new SimpleStringProperty(idName);
        this.nrTruck = new SimpleStringProperty(nrTruck);
        this.nrTruckContract = new SimpleStringProperty(nrTruckContract);
        this.amount = new SimpleStringProperty(amount);
        this.contractName = new SimpleStringProperty(contractName);



    }

    public  String getCustomerName(){return customerName.get();}
    public  String getIdName(){return idName.get();}
    public  String getNrTruck(){return nrTruck.get();}
    public  String getNrTruckContract(){return nrTruckContract.get();}
    public  String getAmount(){return amount.get();}
    public  String getContractName(){return contractName.get();}





    public  void setCustomerName(String value){
        customerName.set(value);}
    public  void setIdName(String value){idName.set(value);}
    public  void setNrTruck(String value){
        nrTruck.set(value);}
    public  void setNrTruckContract(String value){
        nrTruckContract.set(value);}
    public  void setAmount(String value){
        amount.set(value);}
    public  void setContractName(String value){
        contractName.set(value);}




    public StringProperty customerNameProperty() {
        return customerName;
    }
    public StringProperty idNameProperty() {
        return idName;
    }
    public StringProperty nrTruckProperty() {
        return nrTruck;
    }
    public StringProperty nrTruckContractProperty() {
        return nrTruckContract;
    }
    public StringProperty amountProperty() {
        return amount;
    }
    public StringProperty contractNameProperty() {
        return contractName;
    }






}
