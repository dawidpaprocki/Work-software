package entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class ContractsOpenBuy    {

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

    public ContractsOpenBuy() {
    }

}
