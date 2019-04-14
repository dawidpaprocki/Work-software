package entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
@DynamicUpdate
@Builder(toBuilder = true)
public class AllTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String material;
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
