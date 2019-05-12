package crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicUpdate
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AllTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "MaterialId", nullable = false, columnDefinition = "int default 0")
    private Material material;
    private String truckNumber;
    private int amount;
    private int finalAmount;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;
    private String transportOrder;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "BuyContractId", nullable = false, columnDefinition = "int default 0")
    private ContractsOpenBuy contractsOpenBuy;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "SellContractId", nullable = false, columnDefinition = "int default 0")
    private ContractsOpenSell contractsOpenSell;
    private String documentName;
    private String color;
}
