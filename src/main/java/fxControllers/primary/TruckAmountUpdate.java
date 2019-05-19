package fxControllers.primary;

import crud.model.AllTruck;
import crud.model.ContractsOpenAbstract;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;
import crud.services.interfaces.ContractsOpenService;
import org.springframework.stereotype.Component;

@Component
public class TruckAmountUpdate {

    public void updateTruckContract(int typeOfTransaction,
                                    ContractsOpenService<ContractsOpenBuy> contractsOpenBuyService,
                                    ContractsOpenService<ContractsOpenSell> contractsOpenSellService,
                                    ContractsOpenBuy contractsOpenBuy,
                                    ContractsOpenSell contractsOpenSell,
                                    AllTruck newTruckToSave) {
        if (typeOfTransaction == 0) {
            newTruckToSave.setContractsOpenBuy(contractsOpenBuy);
            setCloseContractIfTruckAmountCompleted(contractsOpenBuy);
            contractsOpenBuyService.addOrUpdate(contractsOpenBuy);
        } else {
            newTruckToSave.setContractsOpenSell(contractsOpenSell);
            setCloseContractIfTruckAmountCompleted(contractsOpenSell);
            contractsOpenSellService.addOrUpdate(contractsOpenSell);
        }
    }

    private void setCloseContractIfTruckAmountCompleted(ContractsOpenAbstract chosenContract) {
        chosenContract.setNrTruck(chosenContract.getNrTruck() + 1);
        if (chosenContract.getNrTruckContract() == chosenContract.getNrTruck()) {
            chosenContract.setOpenClose(1);
        }
    }
}
