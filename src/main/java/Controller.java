import cuContract.SelectTable;
import cuContract.tableOpenContracts.OpenCuContractController;
import cuContract.tableOpenContracts.Table;
import cuContract.tableOpenContractsSell.OpenCuContractControllerSell;
import javafx.collections.ObservableList;
import javafx.event.Event;

public class Controller {

//Auto refreshing after made change at production.
    
    public void refresh(Event event) {

        OpenCuContractController openCuContractController = new OpenCuContractController();
        openCuContractController.refresh();


//        OpenCuContractControllerSell OpenCuContractControllerSell = new OpenCuContractControllerSell();
//        OpenCuContractControllerSell.refresh();

    }
}
