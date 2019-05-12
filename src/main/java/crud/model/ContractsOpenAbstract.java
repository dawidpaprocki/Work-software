package crud.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@Data
@Component
public abstract class ContractsOpenAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "MaterialId", nullable = false, columnDefinition = "int default 0")
    private Material material;
    private String contractName;
    private Long id;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;
    private int amount;
    private int nrTruck;
    private int nrTruckContract;
    private int openClose;
}
