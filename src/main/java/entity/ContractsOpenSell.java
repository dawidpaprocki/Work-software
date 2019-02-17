package entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class ContractsOpenSell    {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idSell;
    private int idCustomer;
    private String customerName;
    private String materialName;
    private int nrTruck;
    private int nrTruckContract;
    private int amount;
    private String contractName;
    private int openClose;

    public ContractsOpenSell() {
    }

    public int getId() {
        return id;
    }

    public int getIdSell() {
        return idSell;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public int getNrTruck() {
        return nrTruck;
    }

    public int getNrTruckContract() {
        return nrTruckContract;
    }

    public int getAmount() {
        return amount;
    }

    public String getContractName() {
        return contractName;
    }

    public int getOpenClose() {
        return openClose;
    }

    private ContractsOpenSell(ContractsOpenSellBuilder builder) {
        this.idSell = builder.idSell;
        this.idCustomer = builder.idCustomer;
        this.customerName = builder.customerName;
        this.materialName = builder.materialName;
        this.nrTruck = builder.nrTruck;
        this.nrTruckContract = builder.nrTruckContract;
        this.amount = builder.amount;
        this.contractName = builder.contractName;
        this.openClose = builder.openClose;
    }

    public static class ContractsOpenSellBuilder{

        private int idSell;
        private int idCustomer;
        private String customerName;
        private String materialName;
        private int nrTruck;
        private int nrTruckContract;
        private int amount;
        private String contractName;
        private int openClose;

        public ContractsOpenSellBuilder idSell(int idSell) {
            this.idSell = idSell;
            return this;
        }

        public ContractsOpenSellBuilder idCustomer(int idCustomer) {
            this.idCustomer = idCustomer;
            return this;
        }

        public ContractsOpenSellBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public ContractsOpenSellBuilder materialName(String materialName) {
            this.materialName = materialName;
            return this;
        }

        public ContractsOpenSellBuilder nrTruck(int nrTruck) {
            this.nrTruck = nrTruck;
            return this;
        }

        public ContractsOpenSellBuilder nrTruckContract(int nrTruckContract) {
            this.nrTruckContract = nrTruckContract;
            return this;
        }

        public ContractsOpenSellBuilder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public ContractsOpenSellBuilder contractName(String contractName) {
            this.contractName = contractName;
            return this;
        }

        public ContractsOpenSellBuilder openClose(int openClose) {
            this.openClose = openClose;
            return this;
        }

        public ContractsOpenSell build(){
            return  new ContractsOpenSell(this);
        }
    }
}
