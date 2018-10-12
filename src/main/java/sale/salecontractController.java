package sale;

import combo.ComboCustomers;
import combo.DataOperationAll;
import combo.MaterialList;
import combo.SelectOneThing;
import connection.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class salecontractController {

    @FXML
    private ChoiceBox ChoiceContractSell;

    @FXML
    private TextField NrSellContract;

    @FXML
    private TextField AmountContractSell;

    @FXML
    private TextField TruckContractSell;


    @FXML
    private ChoiceBox choiceMaterialSellContract;
    private ObservableList MaterialList = FXCollections.observableArrayList();

    public void initialize() {
        // Receiving company names
        new ComboCustomers(ChoiceContractSell, "Select Name From Customers", "Name", "ChoiceBox");

        MaterialList materialList = new MaterialList(choiceMaterialSellContract);
        // Receiving material list
        MaterialList = materialList.getMaterialLIst();
        // Adding material list to the choicebox
        choiceMaterialSellContract.setItems(MaterialList);


    }

    int id = 0;

    public void addContractButton() {

        // Receiving company name.
        Object CompanyName = ChoiceContractSell.getValue();
        // Receiving material type.
        Object Material = choiceMaterialSellContract.getValue();
        // Receiving id from company name.
        SelectOneThing selectOneThing = new SelectOneThing("SELECT id FROM Customers Where NAME = '" + String.valueOf(CompanyName) + "'", "id");
        id = selectOneThing.getId();


        Connection conn = null;
        PreparedStatement preparedStatement = null;

//        String query = "INSERT INTO ContractsOpenSell (idCustomer,CustomerName,idName,NrTruck,NrTruckContract,ContractName,Amount,OpenClose) " +
//                "VALUES ('" + id + "','" + (String.valueOf(CompanyName)) + "','" + (String.valueOf(Material)) + "','" + (TruckContractSell.getText()) + "' , '0' ,'" +
//                (NrSellContract.getText()) + "','" + (AmountContractSell.getText()) + "','0') ;";
        new DataOperationAll( "INSERT INTO ContractsOpenSell (idCustomer,CustomerName,idName,NrTruck,NrTruckContract,ContractName,Amount,OpenClose) " +
                "VALUES ('" + id + "','" + (String.valueOf(CompanyName)) + "','" + (String.valueOf(Material)) + "','" + (TruckContractSell.getText()) + "' , '0' ,'" +
                (NrSellContract.getText()) + "','" + (AmountContractSell.getText()) + "','0') ;");
//
//        try {
//            //get connection
//            conn = DBconnection.getConnection();
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

        NrSellContract.clear();
        AmountContractSell.clear();
        TruckContractSell.clear();


    }


}
