package sample;

import java.sql.*;
import java.sql.Connection;

public class open {

    public static void main( String args[] ) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM All_View;" );

            while ( rs.next() ) {
                int Amount = rs.getInt("Amount");
                int Final_Amount  = rs.getInt("Final_Amount");
                String  Data = rs.getString("Data");
                String  View = rs.getString("View");
                String  Froms = rs.getString("Froms");
                String  Tos = rs.getString("Tos");
                String  Vk = rs.getString("Vk");
                String  Ek = rs.getString("Ek");
                String  Truck = rs.getString("Truck");





                System.out.println( "Amount = " + Amount );
                System.out.println( "Final_Amount = " + Final_Amount );
                System.out.println( "Data = " + Data );
                System.out.println( "View = " + View );
                System.out.println( "Froms = " + Froms );
                System.out.println( "Tos = " + Tos );
                System.out.println( "Vk = " + Vk );
                System.out.println( "Ek = " + Ek );
                System.out.println( "Truck = " + Truck );

                System.out.println();

            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
