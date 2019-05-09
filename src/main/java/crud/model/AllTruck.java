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
    @JoinColumn(name = "MaterialId", nullable = true, columnDefinition = "int default 0")
    private Material material;
    private String truckNumber;
    private int amount;
    private int finalAmount;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "SellerId", nullable = true)
    private Customer seller;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "BuyerId", nullable = true)
    private Customer buyer;
    private String transportOrder;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "SalesContractId", nullable = true)
    private ContractsOpenSell contractsOpenSell;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "PurchaseContractId", nullable = true)
    private ContractsOpenBuy contractsOpenBuy;
    private String documentName;
    private String color;
}
