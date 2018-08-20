package sample.combo;

import sample.connection.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditTable {


    public EditTable(String newEditableText, String idWhereFindText,String whatColumn){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE all_view SET " + whatColumn + "  =  '" + newEditableText + "' Where ID = '" + idWhereFindText + "'";

        try {
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query);

            //execute query
            preparedStatement.execute(query);

            //close connection
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
