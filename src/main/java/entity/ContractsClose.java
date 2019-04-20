package entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Builder(toBuilder = true)
public class ContractsClose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idContractBuy")
    @Column(name = "contractsOpenBuy", nullable = false, columnDefinition = "int default 0")
    private ContractsOpenBuy contractsOpenBuy;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idContractSell")
    @Column(name = "contractsOpenBuy", nullable = false, columnDefinition = "int default 0")
    private ContractsOpenSell contractsOpenSell;
}
