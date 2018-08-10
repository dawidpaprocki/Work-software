package sample.connectionPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class insert {
    public static void insert(String where, String dataWhere, String dataWhat){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "insert into "+  where + "( "+dataWhere +" )" +
                "value( "+dataWhat +")";

        try{
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(query);

            //set values
//            preparedStatement.setInt(1, 1);
//            preparedStatement.setString(2, "Bharat");
//            preparedStatement.setInt(3, 62000);

            //execute query
            preparedStatement.executeUpdate();

            //close connection
            preparedStatement.close();
            conn.close();

            System.out.println("Record inserted successfully.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}