package sample;

import java.sql.*;
import java.sql.Connection;

public class SQLiteJDBC {

    public void run() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO All_View (Data,Truck,Amount,Final_Amount,Froms,Tos,Truck_Nr,Transport_Order,Vk,Ek,Ams_doc) " +
                    "VALUES ('15-05-2018','WGM 5rc3', 10150, 10000,'bum' ,'ams','1','12-E','ZMW/1/04/2018','ZZZ/01/04/2018','WNT/004/01/04' );";
            stmt.executeUpdate(sql);


            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
// <ComboBox fx:id="ChocieContractSell" layoutX="14.0" layoutY="292.0" onAction="#ContractBuyToSell" prefWidth="150.0" />