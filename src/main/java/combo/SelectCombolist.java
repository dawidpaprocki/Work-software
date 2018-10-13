//package combo;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import connection.DBconnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class SelectCombolist {
//
//   private ObservableList combolist = FXCollections.observableArrayList();
//
//
//
//    public SelectCombolist(String from, String what, String columnLabel, ObservableList combolist){
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//
//
//        String query = "SELECT "+ what + " FROM "+ from;
//
//        try{
//            //get connection
//            conn = DBconnection.getConnection();
//
//            //create preparedStatement
//            preparedStatement = conn.prepareStatement(query);
//
//            //execute query
//            ResultSet rs = preparedStatement.executeQuery(query);
//            while ( rs.next() )
//            {
//                combolist.add(rs.getString(columnLabel));
//            }
//
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
////        System.out.println(combolist+"działa?");
//    }
//
//    public ObservableList getCombolist() {
//        return combolist;
//    }
//
//    public void setCombolist(ObservableList combolist) {
//        this.combolist = combolist;
//    }
//
//
//
//
//}