import cuContract.tableOpenContracts.OpenCuContractController;
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
