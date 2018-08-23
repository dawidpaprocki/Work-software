package sample.tableOpenContractsSell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.connection.DBconnection;
import sample.tableOpenContracts.TableContractOpenBuy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTable {

private static ObservableList<TableContractOpenBuy>data;

public static void SelectAll(String from, ObservableList<TableContractOpenBuy> date , String what){

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    data = FXCollections.observableArrayList();

    String query = "SELECT "+ what + " FROM "+ from;

    try{
        //get connection
        conn = DBconnection.getConnection();

        //create preparedStatement
        preparedStatement = conn.prepareStatement(query);

        //execute query
        ResultSet rs = preparedStatement.executeQuery(query);
        while (rs.next()) {
            data.add(new TableContractOpenBuy
                    (

                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)


                    ));
        }

        //close connection
        preparedStatement.close();
        conn.close();
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
}


public ObservableList<TableContractOpenBuy> getData() {
    return data;
}

public void setData(ObservableList<TableContractOpenBuy> data) {
    this.data = data;
}
}