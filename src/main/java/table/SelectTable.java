package table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTable {

    private static ObservableList<Table> data;

    public static void SelectAll(String from, ObservableList<Table> date, String what) {

        data = FXCollections.observableArrayList();

        String query = "SELECT " + what + " FROM " + from;

        try {
            //get connection
            Connection conn = DBConnection.getConnection();

            //create preparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(query);
            while (rs.next()) {
                data.add(new Table
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
                                rs.getString(12),
                                rs.getString(13),
                                rs.getString(14)
                        ));
            }

            //close connection
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Table> getData() {
        return data;
    }

    public void setData(ObservableList<Table> data) {
        this.data = data;
    }
}