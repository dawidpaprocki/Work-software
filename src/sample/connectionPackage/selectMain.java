package sample.connectionPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * This class is used to select a list of records from DB table
 * using PreparedStatement.
 * @author codesjava
 */
public class selectMain {

    public selectMain(String from, String what){

        Connection conn = null;
        PreparedStatement preparedStatement = null;


        String query = "SELECT "+ what + " FROM "+ from;

        try{
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(query);
            while ( rs.next() )
            {

            }

            //close connection
            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

//        System.out.println(combolist+"działa?");
    }



}