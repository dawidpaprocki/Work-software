package sample.connectionPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.tablePackage.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * This class is used to select a list of records from DB table
 * using PreparedStatement.
 * @author codesjava
 */
public class SelectAll {

    private static ObservableList<table>data;

    public static void SelectAll(String what,ObservableList<table> date){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        data = FXCollections.observableArrayList();

        String query = "SELECT * FROM "+ what;

        try{
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(query);
            while (rs.next()) {
                data.add(new table
                        (
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getString(7),
                                rs.getString(8),
                                rs.getString(9),
                                rs.getString(10),
                                rs.getString(11),
                                rs.getString(12)
                        ));
            }

            //close connection
            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public ObservableList<table> getData() {
        return data;
    }

    public void setData(ObservableList<table> data) {
        this.data = data;
    }
}