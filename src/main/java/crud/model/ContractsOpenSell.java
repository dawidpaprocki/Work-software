package crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "MaterialId", nullable = false, columnDefinition = "int default 0")
    private Material material;
    private int amount;
    private String contractName;
    private int nrTruck;
    private int nrTruckContract;
    private int openClose;

    public Long getMaterialId(){
        return material.getId();
    }
    @Override
    public String toString() {
        return contractName;
    }
}
