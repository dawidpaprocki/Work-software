package entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
@Builder(toBuilder = true)
public class ContractsClose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idSell;
    private Long idCustomer;
    private String customerName;
    private String materialName;
    private int nrTruck;
    private int nrTruckContract;
    private int amount;
    private String contractName;
    private int openClose;
    private int idContractBuy;
    private int idContractSell;
}
