package entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class ContractsClose {
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
    private int idContractBuy;
    private int idContractSell;


    public ContractsClose() {
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

    public int getIdContractBuy() {
        return idContractBuy;
    }

    public int getIdContractSell() {
        return idContractSell;
    }

    private ContractsClose(ContractsCloseBuilder builder) {
        this.idSell=builder.idSell;
        this.idCustomer=builder.idCustomer;
        this.customerName=builder.customerName;
        this.materialName=builder.materialName;
        this.nrTruck=builder.nrTruck;
        this.nrTruckContract=builder.nrTruckContract;
        this.amount=builder.amount;
        this.contractName=builder.contractName;
        this.openClose=builder.openClose;
        this.idContractBuy=builder.idContractBuy;
        this.idContractSell=builder.idContractSell;
    }

    public static class ContractsCloseBuilder{
        private int idSell;
        private int idCustomer;
        private String customerName;
        private String materialName;
        private int nrTruck;
        private int nrTruckContract;
        private int amount;
        private String contractName;
        private int openClose;
        private int idContractBuy;
        private int idContractSell;

        public ContractsCloseBuilder idSell(int idSell){
            this.idSell = idSell;
            return this;
        }
        public ContractsCloseBuilder idCustomer(int idCustomer){
            this.idCustomer = idCustomer;
            return this;
        }
        public ContractsCloseBuilder customerName(String customerName){
            this.customerName = customerName;
            return this;
        }
        public ContractsCloseBuilder materialName(String materialName){
            this.materialName = materialName;
            return this;
        }
        public ContractsCloseBuilder nrTruck(int nrTruck){
            this.nrTruck = nrTruck;
            return this;
        }
        public ContractsCloseBuilder nrTruckContract(int nrTruckContract){
            this.nrTruckContract = nrTruckContract;
            return this;
        }
        public ContractsCloseBuilder amount(int amount){
            this.amount = amount;
            return this;
        }
        public ContractsCloseBuilder contractName(String contractName){
            this.contractName = contractName;
            return this;
        }
        public ContractsCloseBuilder idContractBuy(int idContractBuy){
            this.idContractBuy = idContractBuy;
            return this;
        }
        public ContractsCloseBuilder idContractSell(int idContractSell){
            this.idContractSell = idContractSell;
            return this;
        }
        public ContractsCloseBuilder openClose(int openClose){
            this.openClose = openClose;
            return this;
        }

        public ContractsClose build(){
            return new ContractsClose(this);
        }


    }
}
