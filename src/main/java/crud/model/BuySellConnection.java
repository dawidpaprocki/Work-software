package crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BuySellConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idContractBuy", nullable = false, columnDefinition = "int default 0")
    private ContractsOpenBuy contractsOpenBuy;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idContractSell", nullable = false, columnDefinition = "int default 0")
    private ContractsOpenSell contractsOpenSell;
}
