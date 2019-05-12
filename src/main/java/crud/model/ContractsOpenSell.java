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
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "MaterialId", nullable = false, columnDefinition = "int default 0")
    private Material material;
    private String contractName;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;
    private int amount;
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
