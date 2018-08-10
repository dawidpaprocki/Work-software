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
public class SelectFromEqual extends selectMain {


    public SelectFromEqual(String from, String what,String columnLabel,ObservableList combolist) {
        super(from, what);


        String query = "SELECT "+ what + " FROM "+ from;

    }
}