package sample.purchase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.combo.ChoiceCustomersName;
import sample.combo.MaterialList;
import sample.combo.SelecCustomerId;
import sample.connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class purchaseContractController {

    @FXML
    private ChoiceBox ChoiceContractBuy;

    @FXML
    private TextField NrBuyContract;

    @FXML
    private TextField AmountContractBuy;

    @FXML
    private Button addBuy;

    @FXML
    private ChoiceBox choiceMaterialBuyContract;

    @FXML
    private TextField TruckContractBuy;

    @FXML
    private ChoiceBox ChoiceContractBuyToSell;

    private ObservableList MaterialList = FXCollections.observableArrayList();

    public void initialize() {
        // Receiving company names
        ChoiceCustomersName choiceContractSell = new ChoiceCustomersName(ChoiceContractBuy);

        MaterialList materialList = new MaterialList(choiceMaterialBuyContract);
        // Receiving material list
        MaterialList = materialList.getMaterialLIst();
        // Adding material list to the choicebox
        choiceMaterialBuyContract.setItems(MaterialList);


    }

    int id;
    int idBuyToSell;
    public void addContractButton() { // do zmiany cały kod.
        // Receiving company name.
        Object CompanyName = ChoiceContractBuy.getValue();
        // Receiving material type.
        Object Material = choiceMaterialBuyContract.getValue();
        // Receiving id from company name.
        Object NameOfCompanyBuyToSell = ChoiceContractBuyToSell.getValue();


        SelecCustomerId selecCustomerId = new SelecCustomerId(String.valueOf(CompanyName));
        idBuyToSell = selecCustomerId.getId();


        Connection conn = null;
        PreparedStatement preparedStatement = null;




        String query2 = " SELECT id FROM ContractsOpenSell WHERE ContractNAME = '" +  (String.valueOf(NameOfCompanyBuyToSell)) + "' ;";

        String query3 = "INSERT INTO ContractsOpenBuy (idCustomer,CustomerName,idName,NrTruck,NrTruckContract,ContractName,Amount,OpenClose) " +
                "VALUES ('" + id + "','" + (String.valueOf(CompanyName)) + "','" + (String.valueOf(Material)) + "','" + (TruckContractBuy.getText()) + "' , '0' ,'" +
                (NrBuyContract.getText()) + "','" + (AmountContractBuy.getText()) + "','0') ;";



        try {
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query2);
            //execute query


            ResultSet rs2 = preparedStatement.executeQuery(query2);

            while (rs2.next()) {
                System.out.println((rs2.getInt("id")));
                id = ((rs2.getInt("id")));
            }



            preparedStatement = conn.prepareStatement(query3);
            preparedStatement.execute(query3);
            //close connection
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        NrBuyContract.clear();
        AmountContractBuy.clear();
        TruckContractBuy.clear();



    }


}
