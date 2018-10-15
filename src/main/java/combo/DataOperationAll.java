package combo;

import connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataOperationAll {
    public DataOperationAll(String what) {

        try

        {
            //get connection
            Connection conn = DBConnection.getConnection();

            //create preparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(what);

            //execute query
            preparedStatement.execute(what);


            //close connection
            preparedStatement.close();
            conn.close();
        } catch (
                Exception e)

        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


}
