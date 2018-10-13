package purchase;

import combo.*;
import connection.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class purchaseContractController {

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
        String query = "SELECT NAME FROM Material";



        // Receiving material list
        new SelectListOfThings("SELECT NAME FROM Material","Name",materialList);
        // Adding material list to the choicebox
        choiceMaterialBuyContract.setItems(this.materialList);


    }

    public void addContractButton() { // do zmiany cały kod.
        // Receiving company name.
        Object CompanyName = choiceCustomerNameBuy.getValue();
        // Receiving material type.
        Object Material = choiceMaterialBuyContract.getValue();
        // Receiving name from company name.
        Object NameOfCompanyBuyToSell = choiceCustomerNameSell.getValue();

        SelectOneThing selectSellerId = new SelectOneThing("SELECT id FROM Customers Where NAME = '" + String.valueOf(CompanyName) + "'","id");
        int idBuyer = selectSellerId.getId();
        SelectOneThing slectBuyerId = new SelectOneThing("SELECT id FROM Customers Where NAME = '" + String.valueOf(NameOfCompanyBuyToSell) + "'","id");
        int idSeller = slectBuyerId.getId();

        new DataOperationAll( "INSERT INTO ContractsOpenBuy (idSell,idCustomer,CustomerName,idName,NrTruck,NrTruckContract,ContractName,Amount,OpenClose) " +
                "VALUES ('" + idSeller + "','" + idBuyer + "','" + (String.valueOf(CompanyName)) + "','" + (String.valueOf(Material)) + "','" + (truckContractBuy.getText()) + "' , '0' ,'" +
                (nrBuyContract.getText()) + "','" + (amountContractBuy.getText()) + "','0') ;");


        choiceMaterialBuyContract.setValue(null);
        choiceCustomerNameSell.setValue(null);
        choiceCustomerNameBuy.setValue(null);
        nrBuyContract.clear();
        amountContractBuy.clear();
        truckContractBuy.clear();



    }


}
