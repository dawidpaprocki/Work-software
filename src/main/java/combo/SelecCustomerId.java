package combo;

import connection.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelecCustomerId {
    int id = 0;
    public SelecCustomerId(String CompanyString) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT id FROM Customers Where NAME = '" + CompanyString + "'";

        try {
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(query);
            while (rs.next()) {

                id = rs.getInt("id");

            }
            //close connection
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getId() {
        return id;
    }
}
