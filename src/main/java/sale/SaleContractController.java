package sale;

import combo.ComboCustomers;
import combo.DataOperationAll;
import combo.SelectListOfThings;
import combo.SelectOneThing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class SaleContractController {
    /**
     *  <h1>The SaleContractController Class.</h1>
     *
     *  Controller for JavaFX panel responsible for adding sell contract to Data Base.
     *
     *  choiceContractSell - Choice Box where we choice name of company to make contract.
     *  nrSellContract - Text Field for set number of contract.
     *  amountContractSell - Text Field for set amount of sold material.
     *  choiceMaterialSellContract - Choice Box for select material.
     *  truckContractSell - Text Field for set amount of sold truck.
     *  materialList - temporary list made for fill  choiceMaterialSellContract.
     *
     */
    @FXML
    private ChoiceBox choiceContractSell;

    @FXML
    private TextField nrSellContract;

    @FXML
    private TextField amountContractSell;

    @FXML
    private TextField truckContractSell;

    @FXML
    private ChoiceBox choiceMaterialSellContract;


    private ObservableList materialList = FXCollections.observableArrayList();

    public void initialize() {

        // Receiving company names
        new ComboCustomers(choiceContractSell, "Select Name From Customers", "Name", "ChoiceBox");


        // Receiving material list
        new SelectListOfThings("SELECT NAME FROM material", "Name", materialList);
        // Adding material list to the choicebox
        choiceMaterialSellContract.setItems(materialList);


    }

    private int id = 0;

    /**
     * Method responsible for insert full details of contract to Data Base.
     */
    public void addContractButton() {

        // Receiving company name.
        Object companyName = choiceContractSell.getValue();
        // Receiving material type.
        Object material = choiceMaterialSellContract.getValue();
        // Receiving id from company name.
        SelectOneThing selectOneThing = new SelectOneThing("SELECT id FROM Customers Where NAME = '" + String.valueOf(companyName) + "'", "id");
        id = selectOneThing.getId();


        new DataOperationAll("INSERT INTO ContractsOpenSell (idCustomer,CustomerName,idName,nrTruck,nrTruckContract,contractName,amount,OpenClose) " +
                "VALUES ('" + id + "','" + (String.valueOf(companyName)) + "','" + (String.valueOf(material)) + "','" + (truckContractSell.getText()) + "' , '0' ,'" +
                (nrSellContract.getText()) + "','" + (amountContractSell.getText()) + "','0') ;");


        // Clearing added and chosen type.
        nrSellContract.clear();
        amountContractSell.clear();
        truckContractSell.clear();


    }


}
