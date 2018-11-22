package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContractsOpenSell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idSell;
    private int idCustomer;
    private String CustomerName;
    private String idName;
    private int NrTruck;
    private int NrTruckContract;
    private int Amount;
    private String ContractName;
    private int OpenClose;


    public ContractsOpenSell() {
    }

    public ContractsOpenSell(int idSell, int idCustomer, String customerName, String idName, int nrTruck, int nrTruckContract, int amount, String contractName, int openClose) {
        this.idSell = idSell;
        this.idCustomer = idCustomer;
        CustomerName = customerName;
        this.idName = idName;
        NrTruck = nrTruck;
        NrTruckContract = nrTruckContract;
        Amount = amount;
        ContractName = contractName;
        OpenClose = openClose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSell() {
        return idSell;
    }

    public void setIdSell(int idSell) {
        this.idSell = idSell;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public int getNrTruck() {
        return NrTruck;
    }

    public void setNrTruck(int nrTruck) {
        NrTruck = nrTruck;
    }

    public int getNrTruckContract() {
        return NrTruckContract;
    }

    public void setNrTruckContract(int nrTruckContract) {
        NrTruckContract = nrTruckContract;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getContractName() {
        return ContractName;
    }

    public void setContractName(String contractName) {
        ContractName = contractName;
    }

    public int getOpenClose() {
        return OpenClose;
    }

    public void setOpenClose(int openClose) {
        OpenClose = openClose;
    }
}
