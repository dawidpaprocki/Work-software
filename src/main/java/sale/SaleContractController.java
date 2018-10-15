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
        new SelectListOfThings("SELECT NAME FROM material","Name", materialList);
        // Adding material list to the choicebox
        choiceMaterialSellContract.setItems(materialList);


    }

    int id = 0;

    public void addContractButton() {

        // Receiving company name.
        Object companyName = choiceContractSell.getValue();
        // Receiving material type.
        Object material = choiceMaterialSellContract.getValue();
        // Receiving id from company name.
        SelectOneThing selectOneThing = new SelectOneThing("SELECT id FROM Customers Where NAME = '" + String.valueOf(companyName) + "'", "id");
        id = selectOneThing.getId();



//        String query = "INSERT INTO ContractsOpenSell (idCustomer,getCustomerName,idName,nrTruck,nrTruckContract,contractName,amount,OpenClose) " +
//                "VALUES ('" + id + "','" + (String.valueOf(CompanyName)) + "','" + (String.valueOf(material)) + "','" + (truckContractSell.getText()) + "' , '0' ,'" +
//                (nrSellContract.getText()) + "','" + (amountContractSell.getText()) + "','0') ;";
        new DataOperationAll( "INSERT INTO ContractsOpenSell (Customer,CustomerName,Name,Truck,TruckContract,contractName,amount,OpenClose) " +
                "VALUES ('" + id + "','" + (String.valueOf(companyName)) + "','" + (String.valueOf(material)) + "','" + (truckContractSell.getText()) + "' , '0' ,'" +
                (nrSellContract.getText()) + "','" + (amountContractSell.getText()) + "','0') ;");
//
//        try {
//            //get connection
//            conn = DBConnection.getConnection();
//
//            //create preparedStatement
//            preparedStatement = conn.prepareStatement(query);
//
//            //execute query
//            preparedStatement.execute(query);
//
//
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }

        nrSellContract.clear();
        amountContractSell.clear();
        truckContractSell.clear();


    }


}
