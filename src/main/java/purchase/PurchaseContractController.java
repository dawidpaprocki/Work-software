package purchase;

import combo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PurchaseContractController {

    @FXML
    private ChoiceBox choiceCustomerNameBuy;

    @FXML
    private TextField nrBuyContract;

    @FXML
    private TextField amountContractBuy;

    @FXML
    private Button addBuy;

    @FXML
    private ChoiceBox choiceMaterialBuyContract;

    @FXML
    private TextField truckContractBuy;

    @FXML
    private ChoiceBox choiceCustomerNameSell;

    private ObservableList materialList = FXCollections.observableArrayList();

    public void initialize() {
        // Receiving company names
        new ComboCustomers(choiceCustomerNameBuy,"Select Name From Customers","Name","ChoiceBox");
        new ComboCustomers(choiceCustomerNameSell,"Select Name From Customers","Name","ChoiceBox"); // need to be optimized

        // Receiving material list
        new SelectListOfThings("SELECT NAME FROM material","Name",materialList);
        // Adding material list to the choicebox
        choiceMaterialBuyContract.setItems(this.materialList);


    }

    public void addContractButton() { // do zmiany cały kod.
        // Receiving company name.
        Object companyName = choiceCustomerNameBuy.getValue();
        // Receiving material type.
        Object material = choiceMaterialBuyContract.getValue();
        // Receiving name from company name.
        Object nameOfCompanyBuyToSell = choiceCustomerNameSell.getValue();

        SelectOneThing selectSellerId = new SelectOneThing("SELECT id FROM Customers Where NAME = '" + String.valueOf(companyName) + "'","id");
        int idBuyer = selectSellerId.getId();
        SelectOneThing selectBuyerId = new SelectOneThing("SELECT id FROM Customers Where NAME = '" + String.valueOf(nameOfCompanyBuyToSell) + "'","id");
        int idSeller = selectBuyerId.getId();

        new DataOperationAll( "INSERT INTO ContractsOpenBuy (idSell,idCustomer,CustomerName,idName,nrTruck,nrTruckContract,contractName,amount,OpenClose) " +
                "VALUES ('" + idSeller + "','" + idBuyer + "','" + (String.valueOf(companyName)) + "','" + (String.valueOf(material)) + "','" + (truckContractBuy.getText()) + "' , '0' ,'" +
                (nrBuyContract.getText()) + "','" + (amountContractBuy.getText()) + "','0') ;");


        choiceMaterialBuyContract.setValue(null);
        choiceCustomerNameSell.setValue(null);
        choiceCustomerNameBuy.setValue(null);
        nrBuyContract.clear();
        amountContractBuy.clear();
        truckContractBuy.clear();



    }


}
