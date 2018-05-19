package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class s {

    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE All_View " +
                    "(Data             TEXT      NOT NULL," +
                    " View           TEXT    NOT NULL, " +
                    " Froms           TEXT     NOT NULL, " +
                    " Tos            TEXT, " +
                    " Vk            TEXT, " +
                    " Ek            TEXT, " +
                    " Amount            int, " +
                    " Final_Amount            int, " +
                    " Truck         TEXT)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}