package combo;

import connection.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataOperationAll {
    public DataOperationAll(String what) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;



        try

        {
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(what);

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
