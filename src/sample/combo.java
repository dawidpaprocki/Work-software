//package sample;
//
//import javafx.collections.ObservableList;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.ComboBox;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class combo extends Controller {
//
//    ComboBox comobox;
//    ChoiceBox ChoiceContractBuy;
//    ChoiceBox ChoiceContractSell;
//    ObservableList Combolist;
//
//    public combo(ComboBox comobox, ChoiceBox choiceContractBuy, ChoiceBox choiceContractSell, ObservableList combolist) {
//        this.comobox = comobox;
//        ChoiceContractBuy = choiceContractBuy;
//        ChoiceContractSell = choiceContractSell;
//        Combolist = combolist;
//    }
//
//    void combo1()
//    {
//        Connection c = null;
//        Statement stmt = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "SELECT NAME FROM Customers;" );
//
//            while ( rs.next() )
//            {
//                Combolist.add(rs.getString("Name"));
//            }
//
//
//            rs.close();
//            stmt.close();
//            c.close();
//        }
//        catch ( Exception e )
//        {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }
//        comobox.setItems(Combolist);
//        ChoiceContractBuy.setItems(Combolist);
//        ChoiceContractSell.setItems(Combolist);
//
//    }
//
//}
