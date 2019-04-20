package crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContractsOpenSell extends ContractsOpenAbstract   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idSell;
    private Long idCustomer;
    private String customerName;
    private String materialName;
    private int amount;
    private String contractName;
    private int nrTruck;
    private int nrTruckContract;
    private int openClose;


}
