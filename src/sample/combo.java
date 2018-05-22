package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class combo extends Controller {

   /* public void combo()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT NAME FROM Customers;" );

            while ( rs.next() )
            {
                Combolist.add(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        comobox.setItems(Combolist);
        System.out.println(comobox.getValue());
        String s = String.valueOf(comobox.getValue());
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id FROM Customers WHERE Name = '" + s + "' ;" );

            while ( rs.next() )
            {
                System.out.println((rs.getInt("id")));
                Customerid = ((rs.getInt("id")));

            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println(Customerid);
        ListchoiceContract();



    }
    */
}
