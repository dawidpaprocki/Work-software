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
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "MaterialId", nullable = false, columnDefinition = "int default 0")
    private Material material;
    private String truckNumber;
    private int amount;
    private int finalAmount;
    private String seller;
    private String buyer;
    private String truckNr;
    private String transportOrder;
    private String salesContractNumber;
    private String purchaseContractNumber;
    private String documentName;
    private String color;
}
