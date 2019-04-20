package crud.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public abstract class ContractsOpenAbstract {
    public int nrTruck;
    public int nrTruckContract;
    public int openClose;
}
